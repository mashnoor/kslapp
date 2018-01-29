package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.NewsAdapter;
import com.xtremebd.ksl.models.News;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.Sidebar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CseNewsActivity extends AppCompatActivity {

    @BindView(R.id.rvNewsList)
    RecyclerView rvNewsList;
    NewsAdapter adapter;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse_news);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ButterKnife.bind(this);
        Sidebar.attach(this, "CSE NEWS");
        rvNewsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new ProgressDialog(this);
        dialog.setMessage("Getting data from server...");

        ApiInterfaceGetter.getStaticInterface().getLatestNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                adapter = new NewsAdapter(response.body());
                rvNewsList.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.d("--------", t.getMessage());
                dialog.dismiss();

            }
        });
    }
}
