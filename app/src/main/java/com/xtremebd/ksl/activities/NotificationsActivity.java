package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.NotificationAdapter;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.models.Notification;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.rvNotifications)
    RecyclerView rvNotifications;
    NotificationAdapter adapter;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ButterKnife.bind(this);
        TopBar.attach(this, "NOTIFICATIONS");
        rvNotifications.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please wait...");
        dialog.setCancelable(false);
        showNotifications();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showNotifications() {

        AsyncHttpClient client = new AsyncHttpClient();
        MasterAccount account = DBHelper.getMasterAccount(this);
        RequestParams params = new RequestParams();
        params.put("masterid", account.getMasterId());
        client.post(AppURLS.GET_NOTIFICATIONS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String reasponse = new String(responseBody);
                List<Notification> notifcations = Arrays.asList(Geson.g().fromJson(reasponse, Notification[].class));
                adapter = new NotificationAdapter(notifcations);
                rvNotifications.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(NotificationsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                finish();

            }
        });


    }


}

