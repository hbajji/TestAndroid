package com.tekkiescorne.testapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tekkiescorne.testapp.config.Apptest;

/**
 * Created by bajji on 15/03/16.
 */
public class UserPreferences {

    public static void removeKey(String key){
        SharedPreferences.Editor editor = UserPreferences.preferences().edit();
        editor.remove(key);
        editor.commit();
    }

    public static void setInt(int value, String key){
        SharedPreferences.Editor editor = UserPreferences.preferences().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key){
        SharedPreferences preferences = UserPreferences.preferences();
        return preferences.getInt(key, 0);
    }


    public static void setBoolean(Boolean value, String key){
        SharedPreferences.Editor editor = UserPreferences.preferences().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean getBoolean(String key){
        SharedPreferences preferences = UserPreferences.preferences();
        return preferences.getBoolean(key, false);
    }

    public static void setString(String value, String key){
        SharedPreferences.Editor editor = UserPreferences.preferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key){
        SharedPreferences preferences = UserPreferences.preferences();
        return preferences.getString(key, null);
    }

    private static SharedPreferences preferences(){
        return Apptest.getInstance().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
    }
}
