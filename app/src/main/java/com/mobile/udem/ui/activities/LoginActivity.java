package com.mobile.udem.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.mobile.udem.R;
import com.mobile.udem.app.Prefs;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    CircularProgressButton signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
        // check empty fields
        isEmptyFields();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn.startAnimation();
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                                                  Prefs.with(LoginActivity.this).setSignUp(true);
                                                  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                  startActivity(intent);
                                                  finish();
                                                  signIn.revertAnimation();
                                              }
                                          }, 5000);

            }
        });
        setStatusBarTranslucent(true);


    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (makeTranslucent) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }
    private void initializeViews() {
        user = (EditText) findViewById(R.id.loginUser);
        password = (EditText) findViewById(R.id.loginPassword);
        signIn = (CircularProgressButton) findViewById(R.id.loginSubmit);
    }
    private void isEmptyFields() {
        TextWatcher mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // check Fields For Empty Values
                checkFieldsForEmptyValues();
            }
        };

        user.addTextChangedListener(mTextWatcher);
        password.addTextChangedListener(mTextWatcher);
    }

    void checkFieldsForEmptyValues(){
        String stringUser = user.getText().toString();
        String stringpassword = password.getText().toString();

        if(TextUtils.isEmpty(stringUser)|| TextUtils.isEmpty(stringpassword)){
            signIn.setEnabled(false);
            signIn.setBackgroundResource(R.drawable.rounded_button_disabled);
            signIn.setTextColor(ContextCompat.getColor(this, R.color.black_overlay));
        } else {
            signIn.setEnabled(true);
            signIn.setBackgroundResource(R.drawable.rounded_button_primary);
            signIn.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }
}
