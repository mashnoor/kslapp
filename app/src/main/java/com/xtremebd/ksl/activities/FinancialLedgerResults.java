package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.LedgerAdapter;
import com.xtremebd.ksl.models.Ledger;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;

import java.util.Arrays;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

public class FinancialLedgerResults extends AppCompatActivity {


    @BindView(R.id.rvFinancialLedger)
    RecyclerView rvFinancialLedger;
    SpotsDialog dialog;

    AsyncHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_ledger_results);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        rvFinancialLedger.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getFinancialLedger();
    }

    private void getFinancialLedger()
    {
        Intent i = getIntent();
        String clientid= i.getStringExtra("client_id");
        String fromDate = i.getStringExtra("from_date");
        String todate = i.getStringExtra("to_date");
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter());
        client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("client_id", clientid);
        params.put("from_date", fromDate);
        params.put("to_date", todate);
        client.post(AppURLS.GET_FINANCIAL_LEDGER, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Ledger[] ledgers = Geson.g().fromJson(response, Ledger[].class);
                LedgerAdapter adapter = new LedgerAdapter(Arrays.asList(ledgers));
                rvFinancialLedger.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                com.orhanobut.logger.Logger.d(new String(responseBody));
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
