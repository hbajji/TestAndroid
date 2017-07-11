package com.tekkiescorne.testapp.manager;

import com.tekkiescorne.testapp.beans.Star;

import java.util.ArrayList;

/**
 * Created by bajji on 7/5/17.
 */
public interface StarManagerListener {

    public void StarManagerDidSuccess(ArrayList<Star> mStras);
    public void StarManagerDidFail();
}
