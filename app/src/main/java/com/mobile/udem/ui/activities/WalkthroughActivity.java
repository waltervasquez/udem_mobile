package com.mobile.udem.ui.activities;

import android.support.v4.app.Fragment;

import com.mobile.udem.R;
import com.mobile.udem.ui.fragmets.WelcomeFragment;
import com.stephentuso.welcome.FragmentWelcomePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * Created by osmar on 07-23-17.
 */

public class WalkthroughActivity extends WelcomeActivity {
    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.colorPrimary)
                .bottomLayout(WelcomeConfiguration.BottomLayout.STANDARD_DONE_IMAGE)
                .page(new FragmentWelcomePage() {
                          @Override
                          protected Fragment fragment() {
                              return new WelcomeFragment(R.drawable.ic_welcome_map,
                                      getString(R.string.welcomeTitleFirst),
                                      getString(R.string.welcomeDescriptionFirst));
                          }
                      }
                )
                .page(new FragmentWelcomePage() {
                          @Override
                          protected Fragment fragment() {
                              return new WelcomeFragment(R.drawable.ic_welcome_notepad,
                                      getString(R.string.welcomeTitleSecond),
                                      getString(R.string.welcomeDescriptionSecond));
                          }
                      }
                )
                .page(new FragmentWelcomePage() {
                          @Override
                          protected Fragment fragment() {
                              return new WelcomeFragment(R.drawable.ic_welcome_user,
                                      getString(R.string.welcomeTitleThird),
                                      getString(R.string.welcomeDescriptionThird));
                          }
                      }
                )
                .exitAnimation(android.R.anim.fade_out)
                .swipeToDismiss(true)
                .backButtonSkips(false)
                .build();
    }
}
