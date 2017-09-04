package com.mobile.udem.ui.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

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

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView approved, pending;
    private HistoryAdapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initializeView();
        getData();
    }

    private void initializeView(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setTitle(getString(R.string.title_activity_history));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);

        approved = (TextView) findViewById(R.id.user_profile_approved);
        pending = (TextView) findViewById(R.id.user_profile_pending);

        //Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.history_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


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
                    historyAdapter = new HistoryAdapter(response.body(), HistoryActivity.this);
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
