package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeActivity extends AppCompatActivity {

    @BindView(R.id.itemName)
    EditText txtItemName;

    @BindView(R.id.itemQuantity)
    EditText txtQty;
    @BindView(R.id.itemLtp)
    TextView tvLtp;
    @BindView(R.id.spnrItsAccounts)
    Spinner spnrItsAccounts;
    AsyncHttpClient client;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;
    List<ITSAccount> itsAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        ButterKnife.bind(this);
        Intent i = getIntent();
        try {
            String itemName = i.getStringExtra("itemname");
            txtItemName.setFocusable(false);
            txtItemName.setText(itemName);
            getLtp();

        } catch (Exception e) {

        }


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        client = new AsyncHttpClient();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Connecting with server. Please wait...");


        ApiInterfaceGetter.getDynamicInterface().getItsAccounts(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<ITSAccount>>() {
            @Override
            public void onResponse(Call<List<ITSAccount>> call, Response<List<ITSAccount>> response) {
                itsAccounts = response.body();
                List<String> accountNos = new ArrayList<String>();
                for (int i = 0; i < itsAccounts.size(); i++) {
                    accountNos.add(itsAccounts.get(i).getItsAccountNo());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TradeActivity.this, android.R.layout.simple_spinner_item, accountNos);
                spnrItsAccounts.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ITSAccount>> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void showToast(String s) {
        Toast.makeText(TradeActivity.this, s, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnGetLtp)
    public void getLtp() {
        String itemName = txtItemName.getText().toString().trim();
        if (TextUtils.isEmpty(itemName)) {
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
                if (response.equals("null")) {
                    showToast("Item Name Error. Please check item name");

                } else {
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

    public void btnBuy(View v) {
        RequestParams trading_params = new RequestParams();
        trading_params.put("loginid", spnrItsAccounts.getSelectedItem().toString());
        trading_params.put("password", itsAccounts.get(spnrItsAccounts.getSelectedItemPosition()).getItsAccountPass());
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
