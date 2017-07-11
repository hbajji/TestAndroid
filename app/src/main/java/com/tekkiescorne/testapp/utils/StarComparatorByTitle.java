package com.tekkiescorne.testapp.utils;

import com.tekkiescorne.testapp.beans.Star;

import java.util.Comparator;

/**
 * Created by bajji on 7/5/17.
 */
public class StarComparatorByTitle implements Comparator<Star> {

    @Override
    public int compare(Star obj1, Star obj2) {
      return   obj1.getTitle().compareTo(obj2.getTitle());
    }
}
