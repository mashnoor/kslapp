package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.utils.AppURLS;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

public class Home extends AppCompatActivity {

    @BindView(R.id.itemName)
    EditText txtItemName;
    @BindView(R.id.loginId)
    EditText txtLoginId;
    @BindView(R.id.password)
    EditText txtPassword;
    @BindView(R.id.itemQuantity)
    EditText txtQty;
    @BindView(R.id.itemLtp)
    TextView tvLtp;
    AsyncHttpClient client;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        client = new AsyncHttpClient();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Connecting with server. Please wait...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void showToast(String s)
    {
        Toast.makeText(Home.this, s, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnGetLtp)
    public void getLtp()
    {
        String itemName = txtItemName.getText().toString().trim();
        if(TextUtils.isEmpty(itemName))
        {
            txtItemName.setError("Enter item name");
            return;
        }
        client.get(AppURLS.GET_LTP_URL + itemName, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                if (response.equals("null"))
                {
                    showToast("Item Name Error. Please check item name");

                }
                else
                {
                   tvLtp.setText(response);
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("Error occur. Please try again");
                dialog.dismiss();

            }
        });

    }

    public void btnBuy(View v)
    {
        RequestParams trading_params = new RequestParams();
        trading_params.put("loginid", txtLoginId.getText().toString().trim());
        trading_params.put("password", txtPassword.getText().toString().trim());
        trading_params.put("item", txtItemName.getText().toString().trim());
        trading_params.put("qty", txtQty.getText().toString().trim());
        client.post(AppURLS.BUY_URL, trading_params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                showToast(response);
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showToast("Error occur. Please try later");
                dialog.dismiss();
            }
        });
    }


}
