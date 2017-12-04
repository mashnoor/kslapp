package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.OrderStatusAdapter;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;


import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class OrderSearcResult extends AppCompatActivity {

    AsyncHttpClient client;

    @BindView(R.id.rvOrderStatus)
    RecyclerView rvOrderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_searc_result);
        ButterKnife.bind(this);
        client = new AsyncHttpClient();
        rvOrderStatus.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Logger.addLogAdapter(new AndroidLogAdapter());
        getOrderStatus();

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
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                com.xtremebd.ksl.models.OrderStatus[] statuses = Geson.g().fromJson(response, com.xtremebd.ksl.models.OrderStatus[].class);
                OrderStatusAdapter adapter = new OrderStatusAdapter(Arrays.asList(statuses));
                rvOrderStatus.setAdapter(adapter);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(new String(responseBody));

            }
        });
    }
}
