package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Requisition;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundRequisitionActivity extends AppCompatActivity {

    @BindView(R.id.etItsAcc)
    EditText etItsAcc;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.etDate)
    EditText etDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_requisition);
        ButterKnife.bind(this);
    }
    private void showToast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void submitRequest(View v)
    {
        String itsAccno = etItsAcc.getText().toString().trim();
        String amount = etAmount.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        if(TextUtils.isEmpty(itsAccno))
        {
            etItsAcc.setError("Its Account no can't be empty");
            return;
        }
        if(TextUtils.isEmpty(amount))
        {
            etItsAcc.setError("Amount can't be empty");
            return;
        }
        if(TextUtils.isEmpty(date))
        {
            etItsAcc.setError("Date can't be empty");
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
