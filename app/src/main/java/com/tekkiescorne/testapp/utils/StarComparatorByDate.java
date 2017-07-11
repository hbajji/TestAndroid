package com.tekkiescorne.testapp.utils;

import com.tekkiescorne.testapp.beans.Star;

import java.util.Comparator;

/**
 * Created by bajji on 7/5/17.
 */
public class StarComparatorByDate implements Comparator<Star> {
    @Override
    public int compare(Star lhs, Star rhs) {
        if (lhs == null || rhs == null) {
            return 0;
        }

        return Integer.compare(Integer.parseInt(lhs.getYear()), Integer.parseInt(rhs.getYear()));
    }
}
