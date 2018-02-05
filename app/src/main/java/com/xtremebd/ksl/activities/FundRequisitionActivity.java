package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Requisition;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.TopBar;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundRequisitionActivity extends AppCompatActivity {

    @BindView(R.id.spnrItsAccounts)
    Spinner spnrItsAcc;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.etDate)
    EditText etDate;
    SpotsDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_requisition);
        ButterKnife.bind(this);
        TopBar.attach(this, "FUND REQUISITION");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
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
        ApiInterfaceGetter.getDynamicInterface().getClientIds(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FundRequisitionActivity.this, android.R.layout.simple_spinner_item, response.body());
                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(FundRequisitionActivity.this, android.R.layout.simple_spinner_item, accountNos);
                spnrItsAcc.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                dialog.dismiss();

            }
        });
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void submitRequest(View v) {
        String itsAccno = spnrItsAcc.getSelectedItem().toString();
        String amount = etAmount.getText().toString().trim();
        String date = etDate.getText().toString().trim();

        if (TextUtils.isEmpty(amount)) {
            etAmount.setError("Amount can't be empty");
            return;
        }
        if (TextUtils.isEmpty(date)) {
            etDate.setError("Date can't be empty");
            return;
        }
        Requisition requisition = new Requisition(itsAccno, amount, date);
        ApiInterfaceGetter.getDynamicInterface().submitFundRequisitionRequest(requisition).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                showToast("Successfully requested");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showToast("Some error occured");
            }
        });
    }
}
