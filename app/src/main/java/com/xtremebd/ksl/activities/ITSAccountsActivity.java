package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.ITSAccountAdapter;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ITSAccountsActivity extends AppCompatActivity {

    @BindView(R.id.etItsAccountNo)
    EditText etItsAccountNo;
    @BindView(R.id.etItsAccountPass)
    EditText etItsaccountPass;
    @BindView(R.id.rvItsAccounts)
    RecyclerView rvitsAccounts;

    ProgressDialog progressDialog;
    ITSAccountAdapter adapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itsaccounts);
        ButterKnife.bind(this);
        TopBar.attach(this, "ITS ACCOUNTS");
        Logger.addLogAdapter(new AndroidLogAdapter());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading. Please Wait...");
        progressDialog.setCancelable(false);
        rvitsAccounts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Logger.addLogAdapter(new AndroidLogAdapter());
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
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                final List<ITSAccount> itsAccounts = Arrays.asList(Geson.g().fromJson(response, ITSAccount[].class));
                adapter = new ITSAccountAdapter(itsAccounts);

                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.btnDelete) {
                            trydeletingaccout(itsAccounts.get(position).getItsAccountNo());
                        } else if (view.getId() == R.id.btnEdit) {
                            updateItsPassword(itsAccounts.get(position).getItsAccountNo());

                        }
                    }
                });

                rvitsAccounts.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                showToast("Error! Refresh to try again");
                progressDialog.dismiss();
                Logger.d(error.getMessage());
                finish();
            }
        });


    }

    private void updateItsPassword(final String itsAccountNo) {
        final AlertDialog.Builder newItsPassDialog = new AlertDialog.Builder(
                this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialougeView = inflater.inflate(
                R.layout.dialog_newitspass, null);

        newItsPassDialog.setView(dialougeView);
        final EditText etNewPass = dialougeView.findViewById(R.id.etNewPassword);

        newItsPassDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String newPassword = etNewPass.getText().toString();
                if (newPassword.isEmpty()) {
                    showToast("Password can't be empty");
                    return;
                }

                RequestParams params = new RequestParams();
                params.put("masterid", DBHelper.getMasterAccount(ITSAccountsActivity.this).getMasterId());
                params.put("itsid", itsAccountNo);
                params.put("newitspass", newPassword);


                AsyncHttpClient client = new AsyncHttpClient();
                client.post(AppURLS.UPDATE_ITS_PASSWORD, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        progressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        progressDialog.dismiss();
                        if (response.equals("success")) {
                            showToast("Password updated successfully");
                            recreate();
                        } else {
                            showToast("Something went wrong. Try again");

                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Logger.d(new String(responseBody));
                        showToast("Something went wrong. Try again");
                        progressDialog.dismiss();

                    }
                });


            }
        });
        newItsPassDialog.show();


    }


    private void trydeletingaccout(final String itsAccNo) {

        String masterId = DBHelper.getMasterAccount(ITSAccountsActivity.this).getMasterId();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("masterid", masterId);
        params.put("itsid", itsAccNo);
        client.post(AppURLS.DELETE_ITS_ACCOUNT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showToast("Account deleted successfully");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

        MasterAccount masterAccount = DBHelper.getMasterAccount(ITSAccountsActivity.this);


        showToast("Account added successfully");
        getItsAccounts();
        progressDialog.dismiss();
        etItsaccountPass.setText("");
        etItsAccountNo.setText("");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("masterid", masterAccount.getMasterId());
        params.put("itsaccno", itsaccno);
        params.put("itsaccpass", itsaccpass);
        client.post(AppURLS.ADD_ITS_ACCOUNT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progressDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showToast("Account added successfully");
                progressDialog.dismiss();
                getItsAccounts();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("Something went wrong. Try again");
                progressDialog.dismiss();

            }
        });


    }
}
