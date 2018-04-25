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
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OrderStatus extends AppCompatActivity {

    @BindView(R.id.spnrItsAccounts)
    Spinner spnrItsAccounts;
    @BindView(R.id.etFromDate)
    EditText etFromDate;
    @BindView(R.id.etToDate)
    EditText etTodate;

    int which;
    ProgressDialog dialog;

    private FirebaseAnalytics mFirebaseAnalytics;

    List<ITSAccount> itsAccounts;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        ButterKnife.bind(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please Wait...");

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        TopBar.attach(this, "ORDER STATUS");

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
                new DatePickerDialog(OrderStatus.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        etTodate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                which = 1;
                new DatePickerDialog(OrderStatus.this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        dialog.show();

        getItsAccounts();

    }


    private void getItsAccounts() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        MasterAccount acc = DBHelper.getMasterAccount(this);
        params.put("masterid", acc.getMasterId());
        params.put("masterpass", acc.getMasterPass());
        client.post(AppURLS.GET_ITS_ACCOUNTS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
                Logger.d("Getting its");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                itsAccounts = Arrays.asList(Geson.g().fromJson(response, ITSAccount[].class));
                List<String> accountNos = new ArrayList<>();
                for (int i = 0; i < itsAccounts.size(); i++) {
                    accountNos.add(itsAccounts.get(i).getItsAccountNo());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(OrderStatus.this, android.R.layout.simple_spinner_item, accountNos);
                spnrItsAccounts.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                showToast("Error! Refresh to try again");
                dialog.dismiss();
                Logger.d(error.getMessage());
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void submitOrderStatus(View v) {
        String itsAccount = spnrItsAccounts.getSelectedItem().toString();
        String fromDate = etFromDate.getText().toString();
        String toDate = etTodate.getText().toString();


        Intent i = new Intent(this, OrderStatusResult.class);

        i.putExtra("itsaccount", itsAccount);

        i.putExtra("itsaccountpass", itsAccounts.get(spnrItsAccounts.getSelectedItemPosition()).getItsAccountPass());
        i.putExtra("fromdate", fromDate);
        i.putExtra("todate", toDate);

        startActivity(i);

    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
