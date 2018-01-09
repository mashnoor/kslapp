package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.ITSAccountAdapter;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
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

    SpotsDialog progressDialog;
    ITSAccountAdapter adapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itsaccounts);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        progressDialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        rvitsAccounts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getItsAccounts();


    }

    private void getItsAccounts() {
        progressDialog.show();
        ApiInterfaceGetter.getDynamicInterface().getItsAccounts(DBHelper.getMasterAccount(ITSAccountsActivity.this)).enqueue(new Callback<List<ITSAccount>>() {
            @Override
            public void onResponse(Call<List<ITSAccount>> call, Response<List<ITSAccount>> response) {
                adapter = new ITSAccountAdapter(response.body());
                final List<ITSAccount> itsAccs = response.body();
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        askForMasterPassword(itsAccs.get(position).getItsAccountNo());
                        Toast.makeText(ITSAccountsActivity.this, "" + position, Toast.LENGTH_LONG).show();
                    }
                });
                rvitsAccounts.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<ITSAccount>> call, Throwable t) {
                showToast("Couldn't connect KSL network");
                progressDialog.dismiss();

            }
        });
    }

    private void askForMasterPassword(final String itsAccNo) {
        AlertDialog.Builder portfolioAddDialouge = new AlertDialog.Builder(
                this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialougeView = inflater.inflate(
                R.layout.dialog_masterpass, null);
        portfolioAddDialouge.setView(dialougeView);
        portfolioAddDialouge.setPositiveButton("Confirm Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText etMasterPassword = dialougeView.findViewById(R.id.etMasterPass);
                String masterPassword = etMasterPassword.getText().toString();
                if (masterPassword.isEmpty()) {
                    Toast.makeText(ITSAccountsActivity.this, "Please enter your master password", Toast.LENGTH_LONG).show();
                    return;

                }
                if(!masterPassword.equals(DBHelper.getMasterAccount(ITSAccountsActivity.this)))
                {
                    showToast("Master password didn't match");
                    dialog.dismiss();
                    return;
                }

                String masterId = DBHelper.getMasterAccount(ITSAccountsActivity.this).getMasterId();
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("masterid", masterId);
                params.put("masterpass",masterPassword);
                params.put("itsid", itsAccNo);
                client.post(AppURLS.DELETE_ITS_ACCOUNT, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        progressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        progressDialog.dismiss();
                        recreate();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        showToast("Something went wrong");
                        progressDialog.dismiss();
                    }
                });


            }
        }).show();

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
        progressDialog.show();
        ApiInterfaceGetter.getDynamicInterface().addItsAccount(masterAccount.getMasterId(), account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("success")) {
                    showToast("Account added successfully");
                    getItsAccounts();
                    progressDialog.dismiss();
                    etItsaccountPass.setText("");
                    etItsAccountNo.setText("");
                } else {
                    showToast("Couldn't connect KSL network");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
                showToast("Couldn't connect KSL network");
            }
        });

    }
}
