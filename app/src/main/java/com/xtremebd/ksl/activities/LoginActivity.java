package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tvMasterId)
    EditText tvMasterId;

    @BindView(R.id.tvMasterPassword)
    EditText tvMasterPassword;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Connecting. Please wait... ");
        if (DBHelper.getMasterAccount(this) != null) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }

    }

    private void sendToken() {
        MasterAccount account = DBHelper.getMasterAccount(this);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("masterid", account.getMasterId());
        params.put("token", account.getToken());
        client.post(AppURLS.SET_TOKEN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    public void masterLogin(View v) {
        String masterId = tvMasterId.getText().toString().trim();
        String masterPassword = tvMasterPassword.getText().toString().trim();

        if (TextUtils.isEmpty(masterId)) {
            tvMasterId.setError("Master ID can't be empty");
            return;
        }
        if (TextUtils.isEmpty(masterPassword)) {
            tvMasterPassword.setError("Master Password can't be empty");
            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("masterid", masterId);
        params.put("masterpass", masterPassword);

        client.post(AppURLS.MASTER_LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);

                if (!response.equals("failed")) {
                    MasterAccount account = Geson.g().fromJson(response, MasterAccount.class);

                    account.setToken(FirebaseInstanceId.getInstance().getToken());


                    DBHelper.setMasterAccount(LoginActivity.this, account);
                    sendToken();

                    dialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));

                    finish();
                } else {
                    showToast("Check login credentials");
                }
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("Something went wrong");
                dialog.dismiss();

            }
        });


    }

    public void goOpenAccount(View v) {
        startActivity(new Intent(this, OpenaccountActivity.class));
    }

    private void showToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    public void goRecover(View v) {
        startActivity(new Intent(this, PasswordRecoveryActivity.class));
    }
}
