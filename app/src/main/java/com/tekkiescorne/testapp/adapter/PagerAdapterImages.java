package com.tekkiescorne.testapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tekkiescorne.testapp.R;
import com.tekkiescorne.testapp.beans.Star;
import com.tekkiescorne.testapp.utils.Utils;

import java.util.ArrayList;

/**
 * Created by bajji on 7/5/17.
 */
public class PagerAdapterImages extends android.support.v4.view.PagerAdapter {
    protected static final String TAG = null;
    int previousPosition = 0;
    private Context mContext;
    private Star mStar;

    public PagerAdapterImages(Context mContext,Star star) {
        super();
        this.mContext = mContext;
        this.mStar=star;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mStar.getImages().size();
    }

    @Override
    public Object instantiateItem(View collection, int pos) {
        previousPosition = pos;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         RelativeLayout view = null;
         view = (RelativeLayout) inflater.inflate(R.layout.fragment_pager,
                    null);
            view.setTag(pos);
        ImageView image= (ImageView) view.findViewById(R.id.imageView1);
        Utils.displayImage(mContext,mStar.getImages().get(pos),image);
try {

    ((ViewPager) collection).addView(view, pos);
}catch (Exception e){

}

        return view;
    }

    @Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((RelativeLayout) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }


}
