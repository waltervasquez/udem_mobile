package com.mobile.udem.ui.activities;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mobile.udem.R;
import com.mobile.udem.app.Prefs;

public class ProfileActivity extends AppCompatActivity {
    private SimpleDraweeView mUserProfileImageBackground;
    private SimpleDraweeView mUserProfileImage;
    private TextView names,career, shift;
    private EditText phone, email;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        setSupportActionBar(toolbar);
        initializeView();
        loadProfile();
    }
    private void initializeView(){
        CollapsingToolbarLayout mCollapsingView = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        mCollapsingView.setExpandedTitleColor(Color.TRANSPARENT);
        mCollapsingView.setTitle(getString(R.string.title_activity_profile));

        mUserProfileImageBackground = (SimpleDraweeView) findViewById(R.id.user_profile_image_background);
        mUserProfileImage = (SimpleDraweeView) findViewById(R.id.user_profile_image);
        names = (TextView) findViewById(R.id.user_profile_name);
        career = (TextView) findViewById(R.id.user_profile_career);
        shift = (TextView) findViewById(R.id.user_profile_shift);
        phone = (EditText) findViewById(R.id.user_profile_phone);
        email = (EditText) findViewById(R.id.user_profile_email);
        save = (Button) findViewById(R.id.user_save);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            phone.setEnabled(true);
            email.setEnabled(true);
            save.setVisibility(View.VISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadProfile(){
        Uri photo = Uri.parse(Prefs.with(this).getPhoto());
        mUserProfileImageBackground.setImageURI(photo);
        mUserProfileImage.setImageURI(photo);
        names.setText(MainActivity.names);
        career.setText(MainActivity.career);
        shift.setText(MainActivity.shift);
        phone.setText(MainActivity.phone);
        email.setText(MainActivity.email);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
