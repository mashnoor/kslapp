package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class OpenaccountActivity extends AppCompatActivity {

    @BindView(R.id.etAccountName)
    EditText etAccountName;
    @BindView(R.id.etAccountEmail)
    EditText etAccountEmail;
    @BindView(R.id.etAccountDetails)
    EditText etAccountDetails;
    @BindView(R.id.etAccountPhone)
    EditText etAccountPhone;

    private FirebaseAnalytics mFirebaseAnalytics;

    ProgressDialog dialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openaccount);
        ButterKnife.bind(this);
        TopBar.attach(this, "REQUEST ACCOUNT");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please Wait...");
        dialog.setCancelable(false);
    }

    private void showToast(String s) {
        Toast.makeText(OpenaccountActivity.this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void submitRequest(View v) {
        String accountName = etAccountName.getText().toString().trim();

        String accountEmail = etAccountEmail.getText().toString().trim();
        String accountDetails = etAccountDetails.getText().toString().trim();
        String phone = etAccountPhone.getText().toString();
        if (TextUtils.isEmpty(accountName)) etAccountName.setError("Account Name can't be empty");

        if (TextUtils.isEmpty(accountEmail)) etAccountEmail.setError("Email can't be empty");
        if (TextUtils.isEmpty(accountDetails))
            etAccountDetails.setError("Account Details can't be empty");

        RequestParams params = new RequestParams();
        params.put("accname", accountName);
        params.put("email", accountEmail);
        params.put("details", accountDetails);
        params.put("phone", phone);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(AppURLS.REQUEST_ACCOUNT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                showToast("Successfully requested account");
                dialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("Something went wrong");
                dialog.dismiss();

            }
        });


    }
}
