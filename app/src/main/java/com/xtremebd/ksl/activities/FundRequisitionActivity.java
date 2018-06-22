package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class FundRequisitionActivity extends AppCompatActivity {

    @BindView(R.id.spnrClientIds)
    Spinner spnrClientIds;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.etDate)
    EditText etDate;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_requisition);
        ButterKnife.bind(this);
        TopBar.attach(this, "FUND REQUISITION");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please wait...");
        dialog.setCancelable(false);
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

                etDate.setText(year + "-" + month + "-" + day);


            }

        };

        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                new DatePickerDialog(FundRequisitionActivity.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
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
                ArrayAdapter<String> adapter = new ArrayAdapter<>(FundRequisitionActivity.this, android.R.layout.simple_spinner_dropdown_item, clientIds);
                spnrClientIds.setAdapter(adapter);
                dialog.dismiss();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(FundRequisitionActivity.this, "Something went wrong. Refresh", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                finish();

            }
        });


    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void submitRequest(View v) {
        String itsAccno = spnrClientIds.getSelectedItem().toString();
        final String amount = etAmount.getText().toString().trim();
        String date = etDate.getText().toString().trim();

        if (TextUtils.isEmpty(amount)) {
            etAmount.setError("Amount can't be empty");
            return;
        }
        if (TextUtils.isEmpty(date)) {
            etDate.setError("Date can't be empty");
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("itsaccno", itsAccno);
        params.put("amount", amount);
        params.put("date", date);
        client.post(AppURLS.REQUEST_FUND_REQUISITION, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                etAmount.setText("");
                etDate.setText("");
                showToast("Requisition requested successfully");
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("Something went wrong. Please try again");
                dialog.show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
