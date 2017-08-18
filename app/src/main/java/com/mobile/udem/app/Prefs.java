package com.mobile.udem.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by osmar on 07-23-17.
 */

public class Prefs {

    private static final String SIGN_UP = "sign_up";
    private static final String IS_LOGIN = "is_login";
    private static final String USER = "user";
    private static final String NAME = "user";
    private static final String GENDER = "user";
    private static final String PHOTO = "user";
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
    public void  setIsLogin(boolean login){

        sharedPreferences
                .edit()
                .putBoolean(IS_LOGIN, login)
                .apply();
    }

    public boolean getIsLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
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

    public void store (String user, String name, String gender, String photo){
        sharedPreferences
                .edit()
                .putString(USER,user)
                .putString(NAME, name)
                .putString(GENDER, gender)
                .putString(PHOTO, photo)
                .apply();
    }
}
