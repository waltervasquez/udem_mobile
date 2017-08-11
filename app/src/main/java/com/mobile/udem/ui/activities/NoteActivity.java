package com.mobile.udem.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mobile.udem.R;
import com.mobile.udem.adapters.NoteAdapter;
import com.mobile.udem.api.Api;
import com.mobile.udem.app.Prefs;
import com.mobile.udem.models.ApiErrorResponse;
import com.mobile.udem.models.Notes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initializeViews();
        getData();
    }
    
    private void initializeViews(){
        //Recycler View
        recyclerView = (RecyclerView) findViewById(R.id.notes_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void getData() {
        Call<List<Notes>> call = Api.instance().getNotes(Prefs.with(this).getUser());
        call.enqueue(new Callback<List<Notes>>() {
            @Override
            public void onResponse(Call<List<Notes>> call, Response<List<Notes>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("Call_api", String.valueOf(response.body()));
                    NoteAdapter notesAdapter = new NoteAdapter(response.body(), NoteActivity.this);
                    recyclerView.setAdapter(notesAdapter);

                } else {
                    // Invalid response
                    ApiErrorResponse apiErrorResponse = Api.parseError(response.errorBody());
                    Log.i("Api error", apiErrorResponse.getError().getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Notes>> call, Throwable t) {
                Log.i("Api failed", " failed " + t.getMessage());
            }
        });
    }
}
