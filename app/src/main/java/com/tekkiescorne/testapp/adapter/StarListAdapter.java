package com.tekkiescorne.testapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekkiescorne.testapp.R;
import com.tekkiescorne.testapp.beans.Star;
import com.tekkiescorne.testapp.fragment.DetailFragment;
import com.tekkiescorne.testapp.utils.Utils;

import java.util.ArrayList;


/**
 * Created by bajji on 7/5/17.
 */

public class StarListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Star> mStars;
    private LayoutInflater inflater;
    private int selected = -1;

    public StarListAdapter(Context mContext, ArrayList<Star> stars) {
        this.mStars = stars;
        this.mContext = mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mStars.size();
    }

    @Override
    public Object getItem(int position) {
        return mStars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_list, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title = (TextView) convertView.findViewById(R.id.title);
        holder.year = (TextView) convertView.findViewById(R.id.year);
        holder.image = (ImageView) convertView.findViewById(R.id.image);

        holder.title.setText(mStars.get(position).getTitle());
        holder.year.setText(mStars.get(position).getYear());
        if (mStars.get(position).getImages().size() != 0)
            Utils.displayImage(mContext, mStars.get(position).getImages().get(0), holder.image);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailFragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("star", mStars.get(position));
                fragment.setArguments(bundle);

                Utils.switchFragment((FragmentActivity) mContext, fragment, DetailFragment.class.getName(), R.id.content, true, true, true);

            }
        });


        return convertView;
    }

    static class ViewHolder {
        TextView title, year;
        ImageView image;
    }

}
