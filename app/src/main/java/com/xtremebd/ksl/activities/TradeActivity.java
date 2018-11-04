package com.xtremebd.ksl.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TradeActivity extends AppCompatActivity {

    @BindView(R.id.itemName)
    EditText txtItemName;

    @BindView(R.id.itemQuantity)
    EditText txtQty;

    @BindView(R.id.spnrItsAccounts)
    Spinner spnrItsAccounts;

    private FirebaseAnalytics mFirebaseAnalytics;
    List<ITSAccount> itsAccounts;

    @BindView(R.id.rvBuyMarketDepth)
    RecyclerView rvBuyMarketDepth;

    @BindView(R.id.rvSellMarketDepth)
    RecyclerView rvSellMarketDepth;

    @BindView(R.id.etPrice)
    EditText etPrice;

    ProgressDialog dialog;

    final private int SEARCH_CODE = 11;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        TopBar.attach(this, "TRADE");
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please Wait...");

        Intent i = getIntent();


        final String itemName = i.getStringExtra("itemname");
        txtItemName.setFocusable(false);

        rvBuyMarketDepth.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvSellMarketDepth.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        Logger.addLogAdapter(new AndroidLogAdapter());


        dialog = new ProgressDialog(this);
        dialog.setMessage("Connecting with server. Please wait...");


        getItsAccounts();

        if (itemName != null) {
            getMarketDepth(itemName);
            txtItemName.setText(itemName);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_CODE && resultCode == Activity.RESULT_OK) {
            String itemName = data.getStringExtra("itemname");
            getMarketDepth(itemName);
            txtItemName.setText(itemName);
        }

    }

    private void getItsAccounts() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        MasterAccount acc = DBHelper.getMasterAccount(this);
        params.put("masterid", acc.getMasterId());
        params.put("masterpass", acc.getMasterPass());
        client.post(AppURLS.GET_ITS_ACCOUNTS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
                Logger.d("Getting its");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                itsAccounts = Arrays.asList(Geson.g().fromJson(response, ITSAccount[].class));
                List<String> accountNos = new ArrayList<>();
                for (int i = 0; i < itsAccounts.size(); i++) {
                    accountNos.add(itsAccounts.get(i).getItsAccountNo());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(TradeActivity.this, android.R.layout.simple_spinner_dropdown_item, accountNos);
                spnrItsAccounts.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                showToast("Error! Refresh to try again");
                dialog.dismiss();
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getMarketDepth(final String itemName) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(AppURLS.GET_MARKET_DEPTH + itemName, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                JSONArray buyMarketDepthJsonArray, sellMarketDepthJsonArray;

                try {
                    JSONObject motherObj = new JSONObject(response);
                    buyMarketDepthJsonArray = motherObj.getJSONArray("buy");
                    sellMarketDepthJsonArray = motherObj.getJSONArray("sell");

                    List<MarketDepth> buyMarketDepth = Arrays.asList(Geson.g().fromJson(buyMarketDepthJsonArray.toString(), MarketDepth[].class));
                    List<MarketDepth> sellMarketDepth = Arrays.asList(Geson.g().fromJson(sellMarketDepthJsonArray.toString(), MarketDepth[].class));
                    MarketDepthAdapter buyAdapter = new MarketDepthAdapter(buyMarketDepth);
                    rvBuyMarketDepth.setAdapter(buyAdapter);

                    MarketDepthAdapter sellAdapter = new MarketDepthAdapter(sellMarketDepth);
                    rvSellMarketDepth.setAdapter(sellAdapter);

                }
                catch (Exception e)
                {
                    showToast("Error");
                    e.printStackTrace();

                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(error.getMessage());
            }
        });
    }




    private void showToast(String s) {
        Toast.makeText(TradeActivity.this, s, Toast.LENGTH_LONG).show();
    }

    public void goSelectItem(View v) {
        Intent i = new Intent(this, SelectItemForTradeActivity.class);
        startActivityForResult(i, SEARCH_CODE);
    }


    private void doTrade(String verb) {

        String symbol = txtItemName.getText().toString().trim();
        String quantity = txtQty.getText().toString().trim();
        String price = etPrice.getText().toString().trim();

        if (symbol.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            showToast("All fields are required!");
            return;
        }


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams trading_params = new RequestParams();
        trading_params.put("loginid", spnrItsAccounts.getSelectedItem().toString());
        trading_params.put("password", itsAccounts.get(spnrItsAccounts.getSelectedItemPosition()).getItsAccountPass());
        trading_params.put("symbol", symbol);
        trading_params.put("qty", quantity);
        trading_params.put("verb", verb);
        trading_params.put("price", price);

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
