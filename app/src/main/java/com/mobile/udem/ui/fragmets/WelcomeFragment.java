package com.mobile.udem.ui.fragmets;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.udem.R;
import com.stephentuso.welcome.WelcomePage;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment implements WelcomePage.OnChangeListener {

    View rootView;
    int mImage;
    String mTitle, mDescription;
    public WelcomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public WelcomeFragment(int image, String title, String description) {
        this.mImage = image;
        this.mTitle = title;
        this.mDescription = description;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_welcome, container, false);
        ImageView image = (ImageView) rootView.findViewById(R.id.welcomeImage);
        TextView title = (TextView) rootView.findViewById(R.id.welcomeTitle);
        TextView description = (TextView) rootView.findViewById(R.id.welcomeDescription);

        image.setImageResource(mImage);
        title.setText(mTitle);
        description.setText(mDescription);

        return rootView;
    }

    @Override
    public void onWelcomeScreenPageScrolled(int pageIndex, float offset, int offsetPixels) {

    }

    @Override
    public void onWelcomeScreenPageSelected(int pageIndex, int selectedPageIndex) {

    }

    @Override
    public void onWelcomeScreenPageScrollStateChanged(int pageIndex, int state) {

    }
}
