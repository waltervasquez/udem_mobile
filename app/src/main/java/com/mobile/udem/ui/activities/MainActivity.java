package com.mobile.udem.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mobile.udem.R;
import com.mobile.udem.app.Prefs;
import com.mobile.udem.models.User;
import com.stephentuso.welcome.WelcomeHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView iconButtonMessages;
    private TextView itemMessagesBadgeTextView;
    private RelativeLayout badgeLayout;
    WelcomeHelper welcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        welcomeScreen = new WelcomeHelper(this, WalkthroughActivity.class);
        welcomeScreen.show(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);



        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadProfile(navigationView);
        checkAuth();
    }
    private void checkAuth(){
        if (!Prefs.with(this).getSignUp()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    private void loadProfile(NavigationView navigationView){
        View view = navigationView.getHeaderView(0);

        User user = User.getTestUser();
        Uri photo = Uri.parse(user.getPhoto());
        //ImageView profile = (ImageView) view.findViewById(R.id.drawer_background);
        SimpleDraweeView avatar = (SimpleDraweeView) view.findViewById(R.id.drawer_avatar);
        TextView name = (TextView) view.findViewById(R.id.drawer_name);

        avatar.setImageURI(photo);
        name.setText(user.getName());

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });


    }
    public void openProfile(){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
    private void setNotificationCount(){
        itemMessagesBadgeTextView.setText("2");
        itemMessagesBadgeTextView.setVisibility(View.VISIBLE);
        //iconButtonMessages.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification, menu);
        MenuItem item = menu.findItem(R.id.action_notification);
        MenuItemCompat.setActionView(item, R.layout.badge_layout);
        View v = MenuItemCompat.getActionView(item);
        itemMessagesBadgeTextView = (TextView) v.findViewById(R.id.badge_textView);
        itemMessagesBadgeTextView.setVisibility(View.GONE); // initially hidden
        iconButtonMessages = (ImageView) v.findViewById(R.id.badge_icon_button);

        setNotificationCount();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                itemMessagesBadgeTextView.setVisibility(View.GONE); // initially hidden
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void openActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
            openActivity(NoteActivity.class);
        } else if (id == R.id.nav_class) {

        } else if (id == R.id.nav_teacher) {

        } else if (id == R.id.nav_map_location) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_sign_out) {
            Prefs.with(this).setSignUp(false);
            recreate();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
