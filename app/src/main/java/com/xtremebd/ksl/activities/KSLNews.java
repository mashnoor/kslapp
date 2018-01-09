package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.NewsAdapter;
import com.xtremebd.ksl.models.News;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

public class KSLNews extends AppCompatActivity {

    AsyncHttpClient client;
    @BindView(R.id.rvKslNewsList)
    RecyclerView rvKslNewsList;
    SpotsDialog dialog;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kslnews);
        ButterKnife.bind(this);
        client = new AsyncHttpClient();
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        rvKslNewsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getKslNews();
    }

    private void getKslNews() {
        client.get(AppURLS.GET_KSL_NEWS, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                News kslNews[] = Geson.g().fromJson(new String(responseBody), News[].class);
                List<News> kslNewsList = Arrays.asList(kslNews);
                Log.d("----------",   "" + kslNews.length);
                adapter = new NewsAdapter(kslNewsList);
                rvKslNewsList.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Toast.makeText(KSLNews.this, "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });

    }
}
