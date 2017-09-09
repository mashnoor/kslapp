package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.models.Requisition;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_requisition);
        ButterKnife.bind(this);
        ApiInterfaceGetter.getDynamicInterface().getItsAccounts(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<ITSAccount>>() {
            @Override
            public void onResponse(Call<List<ITSAccount>> call, Response<List<ITSAccount>> response) {
                List<ITSAccount> itsAccounts = response.body();
                List<String> accountNos = new ArrayList<String>();
                for(int  i = 0; i<itsAccounts.size(); i++)
                {
                    accountNos.add(itsAccounts.get(i).getItsAccountNo());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FundRequisitionActivity.this, android.R.layout.simple_spinner_item, accountNos);
                spnrItsAcc.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ITSAccount>> call, Throwable t) {

            }
        });
    }
    private void showToast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void submitRequest(View v)
    {
        String itsAccno = spnrItsAcc.getSelectedItem().toString();
        String amount = etAmount.getText().toString().trim();
        String date = etDate.getText().toString().trim();

        if(TextUtils.isEmpty(amount))
        {
            etAmount.setError("Amount can't be empty");
            return;
        }
        if(TextUtils.isEmpty(date))
        {
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
