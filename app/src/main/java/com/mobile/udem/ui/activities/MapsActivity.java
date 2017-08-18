package com.mobile.udem.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.mobile.udem.R;
import com.mobile.udem.ui.fragmets.MapsFragment;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Fragment fragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment).commit();
    }
}
