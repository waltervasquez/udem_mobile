package com.mobile.udem.ui.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mobile.udem.R;
import com.mobile.udem.adapters.ScheduleAdapter;
import com.mobile.udem.api.Api;
import com.mobile.udem.app.Prefs;
import com.mobile.udem.models.ApiErrorResponse;
import com.mobile.udem.models.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initializeView();
        getData();
    }
    private void initializeView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setTitle(getString(R.string.title_activity_schedule));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.class_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void getData() {
        Call<List<Schedule>> call = Api.instance().getSchedule(Prefs.with(this).getUser());
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("Call_api", String.valueOf(response.body()));
                    ScheduleAdapter scheduleAdapter = new ScheduleAdapter(response.body(), ScheduleActivity.this);
                    recyclerView.setAdapter(scheduleAdapter);

                } else {
                    // Invalid response
                    ApiErrorResponse apiErrorResponse = Api.parseError(response.errorBody());
                    Log.i("Api error", apiErrorResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                Log.i("Api failed", " failed " + t.getMessage());
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
