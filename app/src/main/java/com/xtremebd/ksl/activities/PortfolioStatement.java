package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Resp;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.Calendar;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortfolioStatement extends AppCompatActivity {


    @BindView(R.id.spnrClientIds)
    Spinner spnrClientIDs;

    @BindView(R.id.tvPortfolioStatement)
    TextView tvPortfolioStatement;

    @BindView(R.id.etPsDate)
    EditText etPsDate;

    AsyncHttpClient client;
    SpotsDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_statement);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        dialog.show();

        ApiInterfaceGetter.getDynamicInterface().getClientIds(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.d("-------", response.body().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PortfolioStatement.this, android.R.layout.simple_spinner_item, response.body());
                spnrClientIDs.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("--------", t.getMessage());
                dialog.dismiss();

            }
        });

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