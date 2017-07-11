package com.tekkiescorne.testapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tekkiescorne.testapp.R;
import com.tekkiescorne.testapp.adapter.StarListAdapter;
import com.tekkiescorne.testapp.beans.Star;
import com.tekkiescorne.testapp.main.MainActivity;
import com.tekkiescorne.testapp.manager.StarManager;
import com.tekkiescorne.testapp.manager.StarManagerListener;

import java.util.ArrayList;

/**
 * Created by bajji on 7/5/17.
 */
public class ListFragment extends Fragment implements StarManagerListener {

    private View mView;
    private Context mContext;
    private StarManager manager;
    private ListView mListView;
    private ArrayList<Star> mStars = new ArrayList<>();


    private StarListAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_list, container, false);

        mContext = getActivity();
        init();

        return mView;
    }

    private void init() {

        MainActivity.enabledMenu = true;
        Toolbar toolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        toolbar.setTitle(R.string.app_name);
//        toolbar.setNavigationIcon();
        mListView = (ListView) mView.findViewById(R.id.list);

        manager = new StarManager(this);
        mStars = manager.getStarsList();

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Chargement...");
        progressDialog.setCancelable(false);

        if (mStars.size() == 0 && !progressDialog.isShowing())
            progressDialog.show();

        adapter = new StarListAdapter(mContext, mStars);
        mListView.setAdapter(adapter);

    }

    @Override
    public void StarManagerDidSuccess(ArrayList<Star> list) {
        mStars.clear();
        mStars.addAll(list);
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    @Override
    public void StarManagerDidFail() {

    }

    public ListView getmListView() {
        return mListView;
    }

    public ArrayList<Star> getmStars() {
        return mStars;
    }

    public StarManager getManager() {
        return manager;
    }
}
