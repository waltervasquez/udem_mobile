package com.mobile.udem.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by osmar on 07-23-17.
 */

public class Prefs {

    private static final String SIGN_UP = "sign_up";
    private static final String USER = "user";
    private static final String PREFS_NAME = "prefs";
    private static Prefs instance;
    private final SharedPreferences sharedPreferences;

    public Prefs(Context context) {

        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static Prefs with(Context context) {

        if (instance == null) {
            instance = new Prefs(context);
        }
        return instance;
    }

    public void setSignUp(boolean loggin) {

        sharedPreferences
                .edit()
                .putBoolean(SIGN_UP, loggin)
                .apply();
    }
    public void setUser(String user){
        sharedPreferences
                .edit()
                .putString(USER, user)
                .apply();
    }

    public boolean getSignUp(){
        return sharedPreferences.getBoolean(SIGN_UP, false);
    }

    public String getUser(){
        return sharedPreferences.getString(USER,"");
    }
}
