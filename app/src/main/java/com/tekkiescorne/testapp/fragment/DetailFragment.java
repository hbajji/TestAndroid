package com.tekkiescorne.testapp.fragment;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tekkiescorne.testapp.R;
import com.tekkiescorne.testapp.adapter.PagerAdapterImages;
import com.tekkiescorne.testapp.beans.Star;
import com.tekkiescorne.testapp.main.MainActivity;
import com.tekkiescorne.testapp.view.LKCarouselTransformer;
import com.tekkiescorne.testapp.view.ParallaxViewPager;


/**
 * Created by bajji on 7/5/17.
 */
public class DetailFragment extends Fragment {
    ValueAnimator mColorAnimation;
    private View mView;
    private Context mContext;
    private Star mStar = null;
    int currentPage = 0;
    private ParallaxViewPager mPager;
//     ViewPager mPager;
     PagerAdapter mPagerAdapter;
    private TextView intro, text;
    private Toolbar toolbar;

    SparseArray<int[]> mLayoutViewIdsMap = new SparseArray<int[]>();
    final float PARALLAX_COEFFICIENT = 1.2f;
    final float DISTANCE_COEFFICIENT = 0.5f;



    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detail, container, false);
        mContext = getActivity();
        init();
        return mView;
    }

    private void init() {
        MainActivity.enabledMenu = false;
        toolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(0
            );

        }


        toolbar.setNavigationIcon(R.mipmap.retour);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        mPager = (ParallaxViewPager) mView.findViewById(R.id.view_pager);
        intro = (TextView) mView.findViewById(R.id.intro);
        text = (TextView) mView.findViewById(R.id.text);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mStar = (Star) bundle.getSerializable("star");
        }

        if (mStar != null)
            setupView();


    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setupView() {
        mPagerAdapter = new PagerAdapterImages(getActivity(), mStar);
        toolbar.setTitle(mStar.getTitle() + "(" + mStar.getYear() + ")");
        // Affectation de l'adapter au ViewPager
        mPager.setAdapter(mPagerAdapter);
        mColorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.YELLOW, Color.RED, Color.BLUE);
        mPager.setCurrentItem(currentPage, true);
        mPager.setPageTransformer(true, new ParallaxTransformer(PARALLAX_COEFFICIENT, DISTANCE_COEFFICIENT,mLayoutViewIdsMap));
        mPager.setPageTransformer(true, new LKCarouselTransformer());
        intro.setText(mStar.getIntro());
        text.setText(mStar.getText());
        setUpColors();


    }

    private void setUpColors(){

        Integer color1 = getResources().getColor(R.color.color1);
        Integer color2 = getResources().getColor(R.color.color2);
        Integer color3 = getResources().getColor(R.color.color3);

        Integer[] colors_temp = {color1, color2, color3};
        colors = colors_temp;

    }

}


class ParallaxTransformer implements ViewPager.PageTransformer {

    float parallaxCoefficient;
    float distanceCoefficient;
    SparseArray<int[]> mLayoutViewIdsMap;
    public ParallaxTransformer(float parallaxCoefficient, float distanceCoefficient,SparseArray<int[]> mLayoutViewIdsMap) {
        this.parallaxCoefficient = parallaxCoefficient;
        this.distanceCoefficient = distanceCoefficient;
        this.mLayoutViewIdsMap = mLayoutViewIdsMap;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void transformPage(View page, float position) {
        float scrollXOffset = page.getWidth() * parallaxCoefficient;

        ViewGroup pageViewWrapper = (ViewGroup) page;
        @SuppressWarnings("SuspiciousMethodCalls")
        int[] layer = mLayoutViewIdsMap.get(pageViewWrapper.getChildAt(0).getId());
        for (int id : layer) {
            View view = page.findViewById(id);
            if (view != null) {
                view.setTranslationX(scrollXOffset * position);
            }
            scrollXOffset *= distanceCoefficient;
        }
    }


}
