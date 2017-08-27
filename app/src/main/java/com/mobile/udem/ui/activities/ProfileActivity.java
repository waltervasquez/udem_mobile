package com.mobile.udem.ui.activities;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mobile.udem.R;
import com.mobile.udem.adapters.HistoryAdapter;
import com.mobile.udem.api.Api;
import com.mobile.udem.app.Prefs;
import com.mobile.udem.models.ApiErrorResponse;
import com.mobile.udem.models.History;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private SimpleDraweeView mUserProfileImageBackground;
    private SimpleDraweeView mUserProfileImage;
    private RecyclerView recyclerView;
    private TextView names,career,approved, pending, shift;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        setSupportActionBar(toolbar);
        initializeView();
        getData();
        loadProfile();
    }
    private void initializeView(){
        CollapsingToolbarLayout mCollapsingView = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        mCollapsingView.setExpandedTitleColor(Color.TRANSPARENT);
        mCollapsingView.setTitle(getString(R.string.title_activity_profile));

        mUserProfileImageBackground = (SimpleDraweeView) findViewById(R.id.user_profile_image_background);
        mUserProfileImage = (SimpleDraweeView) findViewById(R.id.user_profile_image);
        approved = (TextView) findViewById(R.id.user_profile_approved);
        pending = (TextView) findViewById(R.id.user_profile_pending);
        names = (TextView) findViewById(R.id.user_profile_name);
        career = (TextView) findViewById(R.id.user_profile_career);
        shift = (TextView) findViewById(R.id.user_profile_shift);

        //Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.history_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void loadProfile(){
        Uri photo = Uri.parse(Prefs.with(this).getPhoto());
        mUserProfileImageBackground.setImageURI(photo);
        mUserProfileImage.setImageURI(photo);
        names.setText(MainActivity.names);
        career.setText(MainActivity.career);
        shift.setText(MainActivity.shift);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Make http request
     */
    private void getData() {
        Call<List<History>> call = Api.instance().getHistory(Prefs.with(this).getUser());
        call.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("Call_api", String.valueOf(response.body()));
                    historyAdapter = new HistoryAdapter(response.body(), ProfileActivity.this);
                    recyclerView.setAdapter(historyAdapter);


                } else {
                    // Invalid response
                    ApiErrorResponse apiErrorResponse = Api.parseError(response.errorBody());
                    Log.i("Api error", apiErrorResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                Log.i("Api failed", " failed " + t.getMessage());
            }
        });
        getTotal();
    }
    public void getTotal(){
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                approved.setText(String.valueOf(HistoryAdapter.totalApproved));
                pending.setText(String.valueOf(60 - HistoryAdapter.totalApproved));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HistoryAdapter.totalApproved = 0;
    }
}
