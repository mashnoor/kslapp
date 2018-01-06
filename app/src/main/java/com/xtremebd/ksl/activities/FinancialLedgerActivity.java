package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinancialLedgerActivity extends AppCompatActivity {

    @BindView(R.id.spnrClientIds)
    Spinner spnrClientIds;
    @BindView(R.id.etFromDate)
    EditText etFromDate;
    @BindView(R.id.etToDate)
    EditText etTodate;

    int which;
    SpotsDialog dialog;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_ledger);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);



        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                String month = String.valueOf(monthOfYear+1);
                String day = String.valueOf(dayOfMonth);
                if(month.length() == 1)
                    month = "0" + month;
                // TODO Auto-generated method stub
                if(day.length() == 1)
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
        dialog.show();

        ApiInterfaceGetter.getDynamicInterface().getClientIds(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.d("-------", response.body().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FinancialLedgerActivity.this, android.R.layout.simple_spinner_item, response.body());
                spnrClientIds.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("--------", t.getMessage());
                dialog.dismiss();

            }
        });
    }

    public void viewFinancialLedger(View v)
    {
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
