package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Account;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenaccountActivity extends AppCompatActivity {

    @BindView(R.id.etUserName)
    TextView etUserName;
    @BindView(R.id.etUserEmail)
    TextView etUserEmail;
    @BindView(R.id.etUserMobile)
    TextView etUserMobile;
    @BindView(R.id.etUserPassword)
    TextView etUserPassword;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openaccount);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }
    private void showToast(String s)
    {
        Toast.makeText(OpenaccountActivity.this, s, Toast.LENGTH_LONG).show();
    }

    public void submitRequest(View v)
    {
        String userName = etUserName.getText().toString().trim();
        String userPassword = etUserPassword.getText().toString().trim();
        String userEmail = etUserEmail.getText().toString().trim();
        String userMobile = etUserMobile.getText().toString().trim();
        if(TextUtils.isEmpty(userName)) etUserName.setError("User Name can't be empty");
        if(TextUtils.isEmpty(userPassword)) etUserPassword.setError("Password can't be empty");
        if(TextUtils.isEmpty(userEmail)) etUserEmail.setError("Email can't be empty");
        if(TextUtils.isEmpty(userMobile)) etUserMobile.setError("Mobile can't be empty");
        Account account = new Account(userName, userEmail, userPassword, userMobile);
        ApiInterfaceGetter.getDynamicInterface().submitAccountRequest(account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                showToast("Response");
                showToast(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showToast("Some error occured");
            }
        });


    }
}
