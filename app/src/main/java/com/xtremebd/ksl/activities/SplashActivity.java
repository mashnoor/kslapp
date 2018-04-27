package com.xtremebd.ksl.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.services.PriceAlertService;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.HelperFunctions;

import cz.msebera.android.httpclient.Header;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void sendToken() {

        MasterAccount account = DBHelper.getMasterAccount(this);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("masterid", account.getMasterId());
        params.put("token", account.getToken());
        client.post(AppURLS.SET_TOKEN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                finish();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(SplashActivity.this, "Couldn't connect to server!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        if (!HelperFunctions.isNetworkAvailable(this)) {
            Toast.makeText(this, "Please connect to the internet first!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!isMyServiceRunning(PriceAlertService.class)) {
            startService(new Intent(this, PriceAlertService.class));
            Logger.d("Called Service Starter!");
        }

        if (DBHelper.getMasterAccount(this) != null) {
            sendToken();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 2000);
        }


    }


}


