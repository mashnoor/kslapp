package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PasswordRecoveryActivity extends AppCompatActivity {

    @BindView(R.id.etMasterId)
    EditText etMasterId;

    @BindView(R.id.etEmail)
    EditText etEmail;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        ButterKnife.bind(this);
        TopBar.attach(this, "RECOVER PASSWORD");
        dialog = new ProgressDialog(this);
        dialog.setMessage("Connecting... Please Wait...");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void goRecover(View v) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String masterId = etMasterId.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (masterId.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "All inputs must be valid!", Toast.LENGTH_LONG).show();
            return;
        }

        params.put("email", email);
        params.put("masterid", masterId);

        client.post(AppURLS.PASSWORD_RECOVER, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                if (response.equals("error")) {
                    Toast.makeText(PasswordRecoveryActivity.this, "Couldn't match email and Master ID. Please Check inputs", Toast.LENGTH_LONG).show();
                } else if (response.equals("success")) {
                    Toast.makeText(PasswordRecoveryActivity.this, "An email is sent to your email with password!", Toast.LENGTH_LONG).show();
                    finish();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(PasswordRecoveryActivity.this, "Something went wrong. Try again later", Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });
    }
}
