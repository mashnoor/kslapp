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
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.LedgerAdapter;
import com.xtremebd.ksl.models.Ledger;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class FinancialLedgerResults extends AppCompatActivity {


    @BindView(R.id.rvFinancialLedger)
    RecyclerView rvFinancialLedger;
    ProgressDialog dialog;

    AsyncHttpClient client;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_ledger_results);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ButterKnife.bind(this);
        TopBar.attach(this, "FINANCIAL LEDGER RESULTS");

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please Wait...");
        dialog.setCancelable(false);
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
                Toast.makeText(FinancialLedgerResults.this, "Somethins went wrong", Toast.LENGTH_LONG).show();

                dialog.dismiss();
                finish();

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
