package com.mobile.udem.ui.activities;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mobile.udem.R;
import com.mobile.udem.models.User;

public class ProfileActivity extends AppCompatActivity {
    private SimpleDraweeView mUserProfileImageBackground;
    private SimpleDraweeView mUserProfileImage;

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
    }

    private void loadProfile(){
        User user = User.getTestUser();
        Uri photo = Uri.parse(user.getPhoto());
        mUserProfileImageBackground.setImageURI(photo);
        mUserProfileImage.setImageURI(photo);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
