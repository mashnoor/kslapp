package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.TopGainerLoserAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Constants;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

public class TopGainerLoserActivity extends AppCompatActivity {

    AsyncHttpClient client;
    ProgressDialog dialog;

    @BindView(R.id.rvTopGainerLoser)
    RecyclerView rvTopGainerLoser;
    TopGainerLoserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_gainer_loser);
        ButterKnife.bind(this);
        TopBar.attach(this, "TOP GAINER/LOSER");
        Logger.addLogAdapter(new AndroidLogAdapter());
        rvTopGainerLoser.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        String which = getIntent().getStringExtra("which");
        client = new AsyncHttpClient();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Connecting to server...");
        if (which.equals(Constants.TOP_GAINER)) {
            getTopGainerLoser(AppURLS.GET_TOP_GAINERS);
        } else {
            getTopGainerLoser(AppURLS.GET_TOP_LOSERS);
        }
    }

    private void getTopGainerLoser(String url) {
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                List<Item> topGainerLoserItems = Arrays.asList(Geson.g().fromJson(response, Item[].class));
                Logger.d(topGainerLoserItems.size());
                adapter = new TopGainerLoserAdapter(topGainerLoserItems);
                rvTopGainerLoser.setAdapter(adapter);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(error.getMessage());
                Logger.d(responseBody);
            }
        });

    }
}
