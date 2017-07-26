package com.mobile.udem.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.udem.R;
import com.mobile.udem.app.Prefs;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    Button signIn;

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
                Prefs.with(LoginActivity.this).setSignUp(true);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initializeViews() {
        user = (EditText) findViewById(R.id.loginUser);
        password = (EditText) findViewById(R.id.loginPassword);
        signIn = (Button) findViewById(R.id.loginSignIn);
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
        } else {
            signIn.setEnabled(true);
            signIn.setBackgroundResource(R.drawable.rounded_button_primary);
        }
    }
}
