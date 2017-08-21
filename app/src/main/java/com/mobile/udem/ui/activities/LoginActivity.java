package com.mobile.udem.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;

import com.mobile.udem.R;
import com.mobile.udem.api.Api;
import com.mobile.udem.app.Prefs;
import com.mobile.udem.models.Auth;
import com.mobile.udem.models.User;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    CircularProgressButton signIn;
    ScrollView rootView;

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
                trySignIn();

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
    private void trySignIn(){

        signIn.startAnimation();
        Auth auth = new Auth();
        auth.setUsuario(user.getText().toString());
        auth.setPassword(password.getText().toString());
        Call<User> call = Api.instance().signIn(auth);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
              if(response.isSuccessful() && response.body() != null) {

                  Log.i("api_login", response.body().getNombres());

                  Prefs.with(LoginActivity.this).setIsLogin(true);
                  Prefs.with(LoginActivity.this).setUser(response.body().getUsuario());
                  Prefs.with(LoginActivity.this).setPhoto(response.body().getFoto());
                  User user = new User();
                  user.setNombres(response.body().getNombres());


                  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                  startActivity(intent);
                  finish();
                  signIn.revertAnimation();
              }
              else {
                  showError(Api.parseError(response.errorBody()).toString());
              }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
              showError(t.getMessage());
                signIn.revertAnimation();
            }
        });
    }
    private void showError(String error){
        Snackbar snackbar = Snackbar
                .make(rootView, error, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    private void initializeViews() {
        user = (EditText) findViewById(R.id.loginUser);
        password = (EditText) findViewById(R.id.loginPassword);
        signIn = (CircularProgressButton) findViewById(R.id.loginSubmit);
        rootView = (ScrollView) findViewById(R.id.scollLogin);
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
