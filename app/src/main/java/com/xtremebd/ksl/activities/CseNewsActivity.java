package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.NewsAdapter;
import com.xtremebd.ksl.models.News;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CseNewsActivity extends AppCompatActivity {

    @BindView(R.id.rvNewsList)
    RecyclerView rvNewsList;
    NewsAdapter adapter;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse_news);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ButterKnife.bind(this);
        TopBar.attach(this, "CSE NEWS");
        rvNewsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new ProgressDialog(this);
        dialog.setMessage("Getting data from server...");
        dialog.setCancelable(false);

        getNews();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getNews() {
        /***
         *  In which, the activity is requested for shwoing whihc type of news. If ALL,
         *  then the latest news, if A item, then the item news :D
         */

        AsyncHttpClient client = new AsyncHttpClient();
        Intent i = getIntent();
        String which = i.getStringExtra("which");
        String requestUrl;
        if (which.equals("all")) {
            requestUrl = AppURLS.GET_CSE_NEWS;
        } else {
            requestUrl = AppURLS.GET_ITEM_NEWS + which;
        }
        client.get(requestUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                List<News> news = Arrays.asList(Geson.g().fromJson(response, News[].class));
                adapter = new NewsAdapter(news);
                rvNewsList.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(CseNewsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                finish();

            }
        });


    }
}
