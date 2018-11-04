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
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.OrderStatusAdapter;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;


import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class OrderStatusResult extends AppCompatActivity {

    AsyncHttpClient client;

    @BindView(R.id.rvOrderStatus)
    RecyclerView rvOrderStatus;

    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_search_result);
        ButterKnife.bind(this);
        TopBar.attach(this, "ORDER STATUS RESULT");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        client = new AsyncHttpClient();
        rvOrderStatus.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Logger.addLogAdapter(new AndroidLogAdapter());
        getOrderStatus();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please Wait...");
        dialog.setCancelable(false);

    }

    private void getOrderStatus() {
        Intent i = getIntent();
        String itsaccoutnt = i.getStringExtra("itsaccount");
        String itspass = i.getStringExtra("itsaccountpass");
        String fromdate = i.getStringExtra("fromdate");
        String todate = i.getStringExtra("todate");
        RequestParams params = new RequestParams();
        params.put("itsaccountno", itsaccoutnt);
        params.put("itsaccountpass", itspass);
        params.put("startdate", fromdate);
        params.put("enddate", todate);
        client.post(AppURLS.GET_ORDER_STATUS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                com.xtremebd.ksl.models.OrderStatus[] statuses = Geson.g().fromJson(response, com.xtremebd.ksl.models.OrderStatus[].class);
                OrderStatusAdapter adapter = new OrderStatusAdapter(Arrays.asList(statuses));
                rvOrderStatus.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(OrderStatusResult.this, "Something went wrong", Toast.LENGTH_LONG).show();
                finish();

                dialog.dismiss();

            }
        });
    }
}
