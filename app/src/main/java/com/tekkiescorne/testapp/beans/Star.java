package com.tekkiescorne.testapp.beans;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bajji on 7/5/17.
 */

public class Star implements Serializable {
    private ArrayList<String> images;
    private String title;
    private String intro ;
    private String year;
    private String text;

    public Star() {
        this.images=new ArrayList<String>();
        this.title="";
        this.intro="";
        this.year="";
        this.text="";
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    // Parse Star
    public static Star parseModel(JSONObject mResponseObject) {
        try {
            Star mStar = new Star();

           JSONArray images= mResponseObject.optJSONArray("images");
            for (int i=0 ;i<images.length() ;i++) {
                mStar.getImages().add(images.getString(i));
            }
            mStar.setTitle(mResponseObject.optString("title"));
            mStar.setIntro(new String(mResponseObject.optString("intro").getBytes("ISO-8859-15"), "UTF-8").replace("�", ""));
            mStar.setYear(mResponseObject.optString("year"));
            mStar.setText(new String(mResponseObject.optString("text").getBytes("ISO-8859-15"), "UTF-8").replace("�", ""));

            return mStar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Star> parseStars(JSONArray mResultsArray) {
        if(mResultsArray == null)
            return null;
        try {
            ArrayList<Star> mStars  = new ArrayList<Star>();
            for (int i = 0; i < mResultsArray.length(); i++) {
                mStars.add(parseModel(mResultsArray.getJSONObject(i)));
            }
            return mStars;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
