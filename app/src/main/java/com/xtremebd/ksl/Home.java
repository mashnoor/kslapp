package com.xtremebd.ksl;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        client = new AsyncHttpClient();
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
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }


}
