package com.tekkiescorne.testapp.manager;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tekkiescorne.testapp.beans.Star;
import com.tekkiescorne.testapp.utils.Constants;
import com.tekkiescorne.testapp.utils.UserPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import com.tekkiescorne.testapp.config.Apptest;


/**
 * Created by bajji on 7/5/17.
 */
public class StarManager {

    private StarManagerListener listener;
    private final String TAG = "StarManager";
    private final String starsCache = "stars_cache";

    public StarManager(StarManagerListener listener) {
        this.listener = listener;
    }

    public ArrayList<Star> getStarsList(){
        ArrayList<Star> mStars = new ArrayList<>();

        String cache = UserPreferences.getString(starsCache);

        if (cache != null && !cache.isEmpty()) {
            try {
                JSONArray jsonArray = new JSONArray(cache);
                mStars.addAll(Star.parseStars(jsonArray));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        getMarvelsListFromServer();

        return mStars;
    }

    private void getMarvelsListFromServer(){
        String tag_string_req = "marvels_list";

        StringRequest response = new StringRequest(Request.Method.GET, Constants.URL_STAR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);

                ArrayList<Star> mStars = new ArrayList<>();

                if (response != null && !response.isEmpty()) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        mStars.addAll(Star.parseStars(jsonArray));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                UserPreferences.setString(response,starsCache);
                listener.StarManagerDidSuccess(mStars);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
                listener.StarManagerDidFail();
            }
        });

        // Adding request to request queue
        Apptest.getInstance().addToRequestQueue(response, tag_string_req);
    }
}
