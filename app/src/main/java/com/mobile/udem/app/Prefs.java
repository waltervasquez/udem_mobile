package com.mobile.udem.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by osmar on 07-23-17.
 */

public class Prefs {

    private static final String SIGN_UP = "sign_up";
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

    public void setSignUp(boolean totalTime) {

        sharedPreferences
                .edit()
                .putBoolean(SIGN_UP, totalTime)
                .apply();
    }

    public boolean getSignUp(){
        return sharedPreferences.getBoolean(SIGN_UP, false);
    }
}
