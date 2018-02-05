package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapDropDown;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.TopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatus extends AppCompatActivity {

    @BindView(R.id.spnrItsAccounts)
    Spinner spnrItsAccounts;
    @BindView(R.id.etFromDate)
    EditText etFromDate;
    @BindView(R.id.etToDate)
    EditText etTodate;
    @BindView(R.id.etItsAccountPass)
    EditText etitsAccountPass;

    int which;
    SpotsDialog dialog;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);

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

        ApiInterfaceGetter.getDynamicInterface().getItsAccounts(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<ITSAccount>>() {
            @Override
            public void onResponse(Call<List<ITSAccount>> call, Response<List<ITSAccount>> response) {
                List<ITSAccount> itsAccounts = response.body();
                List<String> accountNos = new ArrayList<String>();
                for (int i = 0; i < itsAccounts.size(); i++) {
                    accountNos.add(itsAccounts.get(i).getItsAccountNo());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OrderStatus.this, android.R.layout.simple_spinner_item, accountNos);
                spnrItsAccounts.setAdapter(adapter);

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<ITSAccount>> call, Throwable t) {
                dialog.dismiss();

            }
        });


    }

    public void submitOrderStatus(View v) {
        String itsAccount = spnrItsAccounts.getSelectedItem().toString();
        String fromDate = etFromDate.getText().toString();
        String toDate = etTodate.getText().toString();
        String itsAccountPass = etitsAccountPass.getText().toString();

        Intent i = new Intent(this, OrderSearcResult.class);

        i.putExtra("itsaccount", itsAccount);

        i.putExtra("itsaccountpass", itsAccountPass);
        i.putExtra("fromdate", fromDate);
        i.putExtra("todate", toDate);

        startActivity(i);

    }
}
