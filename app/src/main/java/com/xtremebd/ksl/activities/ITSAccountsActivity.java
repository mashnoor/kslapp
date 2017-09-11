package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.snappydb.DB;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.ITSAccountAdapter;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.util.TextUtils;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ITSAccountsActivity extends AppCompatActivity {

    @BindView(R.id.etItsAccountNo)
    EditText etItsAccountNo;
    @BindView(R.id.etItsAccountPass)
    EditText etItsaccountPass;
    @BindView(R.id.rvItsAccounts)
    RecyclerView rvitsAccounts;

    SpotsDialog dialog;
    ITSAccountAdapter adapter;

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itsaccounts);
        ButterKnife.bind(this);
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        rvitsAccounts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getItsAccounts();


    }

    private void getItsAccounts() {
        dialog.show();
        ApiInterfaceGetter.getDynamicInterface().getItsAccounts(DBHelper.getMasterAccount(ITSAccountsActivity.this)).enqueue(new Callback<List<ITSAccount>>() {
            @Override
            public void onResponse(Call<List<ITSAccount>> call, Response<List<ITSAccount>> response) {
                adapter = new ITSAccountAdapter(response.body());
                rvitsAccounts.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<ITSAccount>> call, Throwable t) {
                showToast("Couldn't connect KSL network");
                dialog.dismiss();

            }
        });
    }

    public void addItsAccount(View v) {
        String itsaccno = etItsAccountNo.getText().toString().trim();
        String itsaccpass = etItsaccountPass.getText().toString().trim();
        if (TextUtils.isEmpty(itsaccno)) {
            etItsAccountNo.setError("Account No can't be empty");
            return;
        }
        if (TextUtils.isEmpty(itsaccpass)) {
            etItsaccountPass.setError("Password can't be empty");
            return;
        }
        ITSAccount account = new ITSAccount(itsaccno, itsaccpass);
        MasterAccount masterAccount = DBHelper.getMasterAccount(ITSAccountsActivity.this);
        dialog.show();
        ApiInterfaceGetter.getDynamicInterface().addItsAccount(masterAccount.getMasterId(), account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("success")) {
                    showToast("Account added successfully");
                    getItsAccounts();
                    dialog.dismiss();
                    etItsaccountPass.setText("");
                    etItsAccountNo.setText("");
                } else {
                    showToast("Couldn't connect KSL network");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                showToast("Couldn't connect KSL network");
            }
        });

    }
}
