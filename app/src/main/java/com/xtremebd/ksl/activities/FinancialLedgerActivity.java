package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
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

public class FinancialLedgerActivity extends AppCompatActivity {

    @BindView(R.id.spnrClientIds)
    Spinner spnrClientIds;
    @BindView(R.id.etFromDate)
    EditText etFromDate;
    @BindView(R.id.etToDate)
    EditText etTodate;

    int which;
    ProgressDialog dialog;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_ledger);
        TopBar.attach(this, "FINANCIAL LEDGER");
        ButterKnife.bind(this);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please wait...");


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
                if (which == 0)
                    etFromDate.setText(year + "-" + month + "-" + day);

                else
                    etTodate.setText(year + "-" + month + "-" + day);
            }

        };

        etFromDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                which = 0;
                new DatePickerDialog(FinancialLedgerActivity.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        etTodate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                which = 1;
                new DatePickerDialog(FinancialLedgerActivity.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        getClientIds();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(FinancialLedgerActivity.this, android.R.layout.simple_spinner_item, clientIds);
                spnrClientIds.setAdapter(adapter);
                dialog.dismiss();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(FinancialLedgerActivity.this, "Something went wrong. Refresh", Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });


    }

    public void viewFinancialLedger(View v) {
        String clientID = spnrClientIds.getSelectedItem().toString();
        String fromDate = etFromDate.getText().toString();
        String todate = etTodate.getText().toString();
        Intent i = new Intent(this, FinancialLedgerResults.class);
        i.putExtra("client_id", clientID);
        i.putExtra("from_date", fromDate);
        i.putExtra("to_date", todate);
        startActivity(i);
    }
}
