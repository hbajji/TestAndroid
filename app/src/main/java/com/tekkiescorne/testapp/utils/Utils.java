package com.tekkiescorne.testapp.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tekkiescorne.testapp.R;


/**
 * Created by bajji on 7/5/17.
 */
public class Utils {
    public static void addFragment(FragmentActivity activity,
                                   Fragment fragment, int content, boolean addToBackStack) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (!addToBackStack)
            transaction.disallowAddToBackStack();
        else
            transaction.addToBackStack(null);

        transaction.replace(content, fragment);
        transaction.commit();

    }

    public static void displayImage(Context context, String url, ImageView imageView){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .build();

        ImageLoader.getInstance().init(config);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(url, imageView,options);
    }

    public static void switchFragment(FragmentActivity mContext, Fragment mFragment, String mTag, int layoutID, boolean replace, boolean addToBackStack, boolean animate) {
        FragmentManager fragmentManager = mContext.getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag(mTag);
        if (myFragment != null && myFragment.isVisible()) {
            fragmentManager.beginTransaction().show(myFragment).commit();
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mTag != null) {
            Fragment lastFragment = fragmentManager.findFragmentByTag(mTag);
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
        }

        if (fragmentManager.findFragmentByTag(mTag) != null) {
            transaction.remove(mFragment);
        }

        if (animate)
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,R.anim.slide_out_left, R.anim.slide_out_right);

        if (replace)
            transaction.replace(layoutID, mFragment, mTag);
        else
            transaction.add(layoutID, mFragment, mTag);

        if (!addToBackStack) {
            transaction.disallowAddToBackStack();
        } else {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

}
