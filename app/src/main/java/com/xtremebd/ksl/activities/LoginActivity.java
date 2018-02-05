package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.interfaces.DynamicApiInterface;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tvMasterId)
    EditText tvMasterId;

    @BindView(R.id.tvMasterPassword)
    EditText tvMasterPassword;
    SpotsDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        if (DBHelper.getMasterAccount(this) != null) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }

    }

    private void sendToken() {
        Log.d("-------", "Called tolem");
        ApiInterfaceGetter.getDynamicInterface().setToken(DBHelper.getMasterAccount(this)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("------", response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();

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
        dialog.show();
        final MasterAccount account = new MasterAccount(masterId, masterPassword);
        ApiInterfaceGetter.getDynamicInterface().masterLogin(account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("--------", response.body());
                if (response.body().equals("success")) {
                    account.setToken(FirebaseInstanceId.getInstance().getToken());


                    DBHelper.setMasterAccount(LoginActivity.this, account);
                    sendToken();

                    dialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));

                    finish();
                } else {
                    showToast("Login info failed");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("--------", t.getMessage());
                dialog.dismiss();
                showToast("Couldn't connect to KSL server.");

            }
        });


    }

    public void goOpenAccount(View v) {
        startActivity(new Intent(this, OpenaccountActivity.class));
    }

    private void showToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}
