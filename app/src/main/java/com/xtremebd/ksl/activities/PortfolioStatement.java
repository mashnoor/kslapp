package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_statement);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        ApiInterfaceGetter.getDynamicInterface().getClientIds(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.d("-------", response.body().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PortfolioStatement.this, android.R.layout.simple_spinner_item, response.body());
                spnrClientIDs.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("--------", t.getMessage());

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
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Logger.d(new String(responseBody));
                tvPortfolioStatement.setText(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(new String(responseBody));
            }
        });


    }
}
