package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.interfaces.DynamicApiInterface;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tvMasterId)
    EditText tvMasterId;

    @BindView(R.id.tvMasterPassword)
    EditText tvMasterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

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
        final MasterAccount account = new MasterAccount(masterId, masterPassword);
        ApiInterfaceGetter.getDynamicInterface().masterLogin(account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("--------", response.body());
                if (response.body().equals("success")) {

                    DBHelper.setMasterAccount(LoginActivity.this, account);

                    startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("--------", t.getMessage());

            }
        });


    }
}
