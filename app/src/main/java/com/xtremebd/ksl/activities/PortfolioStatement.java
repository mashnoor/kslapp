package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PortfolioStatement extends AppCompatActivity {


    @BindView(R.id.spnrClientIds)
    Spinner spnrClientIDs;

    @BindView(R.id.tvPortfolioStatement)
    TextView tvPortfolioStatement;

    @BindView(R.id.etPsDate)
    EditText etPsDate;

    AsyncHttpClient client;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_statement);
        ButterKnife.bind(this);
        TopBar.attach(this, "PORTFOLIO STATEMENT");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please wait...");
        dialog.show();



        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                String month = String.valueOf(monthOfYear + 1);
                String day = String.valueOf(dayOfMonth);
                if (month.length() == 1)
                    month = "0" + month;
                // TODO Auto-generated method stub
                if (day.length() == 1)
                    day = "0" + day;

                etPsDate.setText(year + "-" + month + "-" + day);
            }

        };


        etPsDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                new DatePickerDialog(PortfolioStatement.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        getClientIds();
    }

    private void getClientIds() {
        AsyncHttpClient client = new AsyncHttpClient();
        MasterAccount account = DBHelper.getMasterAccount(this);
        RequestParams params = new RequestParams();
        params.put("masterid", account.getMasterId());
        params.put("masterpass", account.getMasterPass());
        client.post(AppURLS.GET_CLIENT_ID, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                List<String> clientIds = Arrays.asList(Geson.g().fromJson(response, String[].class));
                ArrayAdapter<String> adapter = new ArrayAdapter<>(PortfolioStatement.this, android.R.layout.simple_spinner_item, clientIds);
                spnrClientIDs.setAdapter(adapter);
                dialog.dismiss();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(PortfolioStatement.this, "Something went wrong. Refresh", Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void viewPortfolioStatement(View v) {
        String clientId = spnrClientIDs.getSelectedItem().toString();
        String psDate = etPsDate.getText().toString();
        com.xtremebd.ksl.models.PortfolioStatement statement = new com.xtremebd.ksl.models.PortfolioStatement(clientId, psDate);
        Logger.d(statement.toString());
        client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("client_id", clientId);
        params.put("portfolio_date", psDate);
        client.post(AppURLS.GET_PORTFOLIO_STATEMENT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Logger.d(new String(responseBody));
                tvPortfolioStatement.setText(new String(responseBody));
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(new String(responseBody));
                dialog.dismiss();
            }
        });


    }
}
