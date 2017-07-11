package com.tekkiescorne.testapp.view;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.tekkiescorne.testapp.R;

/**
 * Created by bajji on 4/26/16.
 */
public class LKCarouselTransformer implements ViewPager.PageTransformer {

    public void transformPage(View view, float position) {

        if (position < -1 || position > 1) {
            return;
        }

        int pageWidth = view.getWidth();

        View carouselBgImgView = view.findViewById(R.id.imageView1);

        float fgSpeed = position * pageWidth / 4;
        float bgSpeed = 0;

        if (position > 0 && position <= 1) {
            bgSpeed = position * (100 - pageWidth);
        }

        if (carouselBgImgView != null) {
            carouselBgImgView.setTranslationX(bgSpeed);
        }

    }
}