package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.MarketDepthAdapter;
import com.xtremebd.ksl.models.ITSAccount;
import com.xtremebd.ksl.models.MarketDepth;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.Sidebar;

import java.util.ArrayList;
import java.util.Arrays;
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

    @BindView(R.id.spnrItsAccounts)
    Spinner spnrItsAccounts;
    AsyncHttpClient client;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;
    List<ITSAccount> itsAccounts;

    @BindView(R.id.rvBuyMarketDepth)
    RecyclerView rvBuyMarketDepth;

    @BindView(R.id.rvSellMarketDepth)
    RecyclerView rvSellMarketDepth;

    @BindView(R.id.etPrice)
    EditText etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Sidebar.attach(this, "TRADE");

        Intent i = getIntent();

        final String itemName = i.getStringExtra("itemname");
        txtItemName.setFocusable(false);
        txtItemName.setText(itemName);
        rvBuyMarketDepth.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvSellMarketDepth.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        Logger.addLogAdapter(new AndroidLogAdapter());

        client = new AsyncHttpClient();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Connecting with server. Please wait...");


        ApiInterfaceGetter.getDynamicInterface().getItsAccounts(DBHelper.getMasterAccount(this)).enqueue(new Callback<List<ITSAccount>>() {
            @Override
            public void onResponse(Call<List<ITSAccount>> call, Response<List<ITSAccount>> response) {
                itsAccounts = response.body();
                List<String> accountNos = new ArrayList<>();
                for (int i = 0; i < itsAccounts.size(); i++) {
                    accountNos.add(itsAccounts.get(i).getItsAccountNo());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TradeActivity.this, android.R.layout.simple_spinner_item, accountNos);
                spnrItsAccounts.setAdapter(adapter);
                getBuyMarketDepth(itemName);
            }

            @Override
            public void onFailure(Call<List<ITSAccount>> call, Throwable t) {

            }
        });


    }

    private void getBuyMarketDepth(final String itemName) {
        AsyncHttpClient client = new AsyncHttpClient();
        Logger.d(AppURLS.GET_BUY_MARKET_DEPTH + itemName);
        client.get(AppURLS.GET_BUY_MARKET_DEPTH + itemName, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                Logger.d(response);
                List<MarketDepth> buyMarketDepth = Arrays.asList(Geson.g().fromJson(response, MarketDepth[].class));
                MarketDepthAdapter adapter = new MarketDepthAdapter(buyMarketDepth);
                rvBuyMarketDepth.setAdapter(adapter);
                getSellMarketDepth(itemName);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(error.getMessage());
            }
        });
    }

    private void getSellMarketDepth(String itemName) {
        AsyncHttpClient client = new AsyncHttpClient();
        Logger.d(AppURLS.GET_SELL_MARKET_DEPTH + itemName);
        client.get(AppURLS.GET_SELL_MARKET_DEPTH + itemName, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                Logger.d(response);
                List<MarketDepth> sellMarketDepth = Arrays.asList(Geson.g().fromJson(response, MarketDepth[].class));
                MarketDepthAdapter adapter = new MarketDepthAdapter(sellMarketDepth);
                rvSellMarketDepth.setAdapter(adapter);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(error.getMessage());
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
                    //tvLtp.setText(response);
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

    private void doTrade(String verb) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams trading_params = new RequestParams();
        trading_params.put("loginid", spnrItsAccounts.getSelectedItem().toString());
        trading_params.put("password", itsAccounts.get(spnrItsAccounts.getSelectedItemPosition()).getItsAccountPass());
        trading_params.put("symbol", txtItemName.getText().toString().trim());
        trading_params.put("qty", txtQty.getText().toString().trim());
        trading_params.put("verb", verb);
        trading_params.put("price", etPrice.getText().toString().trim());

        client.post(AppURLS.TRADE_URL, trading_params, new AsyncHttpResponseHandler() {

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

    public void goBuy(View v) {
        doTrade("BUY");
    }

    public void goSell(View v) {
        doTrade("SELL");
    }


}
