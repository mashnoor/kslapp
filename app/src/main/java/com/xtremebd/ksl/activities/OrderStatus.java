package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(OrderStatus.this, android.R.layout.simple_spinner_item, accountNos);
                spnrItsAccounts.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ITSAccount>> call, Throwable t) {

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
