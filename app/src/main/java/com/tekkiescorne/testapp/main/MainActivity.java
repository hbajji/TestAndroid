package com.tekkiescorne.testapp.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tekkiescorne.testapp.R;
import com.tekkiescorne.testapp.adapter.StarListAdapter;
import com.tekkiescorne.testapp.beans.Star;
import com.tekkiescorne.testapp.fragment.ListFragment;
import com.tekkiescorne.testapp.utils.StarComparatorByDate;
import com.tekkiescorne.testapp.utils.StarComparatorByTitle;
import com.tekkiescorne.testapp.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by bajji on 7/5/17.
 */
public class MainActivity extends AppCompatActivity {
    ListFragment listFragment;
    public static Boolean enabledMenu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFragment = new ListFragment();
        Utils.addFragment(this, listFragment, R.id.content, false);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return enabledMenu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.initial: {
                this.sorted("default");
                return true;
            }
            case R.id.sorted_by_title: {

                this.sorted("title");
                return true;

            }
            case R.id.sorted_by_date: {

                this.sorted("date");
                return true;

            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sorted(String sortedBy) {
        ArrayList<Star> mStarsTemp = listFragment.getmStars();
        if (sortedBy.equalsIgnoreCase("date"))
            Collections.sort(mStarsTemp, new StarComparatorByDate());
        else if (sortedBy.equalsIgnoreCase("title"))
            Collections.sort(mStarsTemp, new StarComparatorByTitle());
        else if ((sortedBy.equalsIgnoreCase("default")))
            mStarsTemp = listFragment.getManager().getStarsList();

        listFragment.getmListView().setAdapter(new StarListAdapter(this, mStarsTemp));
//
    }


}
