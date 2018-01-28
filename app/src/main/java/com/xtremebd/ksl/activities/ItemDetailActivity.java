package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.beardedhen.androidbootstrap.BootstrapButton;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.PortfolioHelper;
import com.xtremebd.ksl.utils.WatchlistHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {


    @BindView(R.id.tvClosePrice)
    TextView tvClosePrice;
    @BindView(R.id.tvLTP)
    TextView tvLTP;
    @BindView(R.id.tvYCP)
    TextView tvYCP;
    @BindView(R.id.tvOpenPrice)
    TextView tvOpenPrice;
    @BindView(R.id.tvTrade)
    TextView tvTrade;
    @BindView(R.id.tvVolume)
    TextView tvVolume;
    @BindView(R.id.tvRange)
    TextView tvRange;
    @BindView(R.id.tvLTD)
    TextView tvLTD;
    @BindView(R.id.tvCapital)
    TextView tvCapital;
    @BindView(R.id.tvChange)
    TextView tvChange;
    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.btnWatchList)
    BootstrapButton btnWatchList;
    @BindView(R.id.btnPortfolio)
    BootstrapButton btnPortfolio;

    String item_name;
    Item current_item;
    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please wait...");
        item_name = getIntent().getStringExtra("item");
        tvItemName.setText(item_name);
        Logger.addLogAdapter(new AndroidLogAdapter());
        if (WatchlistHelper.isIteminWatchlist(this, item_name)) {
            btnWatchList.setText("Remove from Watchlist");
        } else {
            btnWatchList.setText("Add to Watchlist");
        }
        if (!PortfolioHelper.isIteminPortfolio(ItemDetailActivity.this, item_name)) {
            btnPortfolio.setText("Add to Portfolio");

        } else {
            btnPortfolio.setText("Customize Portfolio");
        }

        getIntemDetail(item_name);

    }

    private void getIntemDetail(final String item_name) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_ITEM_DETAIL + item_name, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                current_item = Geson.g().fromJson(response, Item.class);
                current_item.setItem(item_name);
                tvClosePrice.setText(current_item.getCloseprice());
                tvLTP.setText(current_item.getLtp());
                tvYCP.setText(current_item.getYesterdayClosePrice());
                tvOpenPrice.setText(current_item.getOpenPrice());
                tvTrade.setText(current_item.getTotaltrade());
                tvVolume.setText(current_item.getVolume());
                tvRange.setText(current_item.getRange());

                tvCapital.setText(current_item.getCapital());
                tvChange.setText(current_item.getChange());
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Logger.d(new String(responseBody));
                Logger.d(error.getMessage());
                dialog.dismiss();

            }
        });


    }

    public void actionWatchlist(View v) {
        if (!WatchlistHelper.isIteminWatchlist(ItemDetailActivity.this, item_name)) {
            WatchlistHelper.addIteminWatchList(ItemDetailActivity.this, current_item);
            showToast("Item added to watchlist successfully");
            recreate();

        } else {
            WatchlistHelper.deleteItemFromWatchList(ItemDetailActivity.this, item_name);
            showToast("Item removed from watchlist successfully");
            btnWatchList.setText("Add to watchlist");
        }

    }

    public void actionPortfolio(View v) {
        if (!PortfolioHelper.isIteminPortfolio(ItemDetailActivity.this, item_name)) {
            AlertDialog.Builder portfolioAddDialouge = new AlertDialog.Builder(
                    this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialougeView = inflater.inflate(
                    R.layout.addtoportfoliodialogue, null);
            portfolioAddDialouge.setView(dialougeView);

            portfolioAddDialouge.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    TextView tvnoOfStock = dialougeView.findViewById(R.id.etNoOfStocks);
                    TextView tvbuyPrice = dialougeView.findViewById(R.id.etBuyPrice);
                    current_item.setBuyPrice(tvbuyPrice.getText().toString());
                    current_item.setNoOfStock(tvnoOfStock.getText().toString());
                    PortfolioHelper.addIteminPortfolio(ItemDetailActivity.this, current_item);
                    showToast("Added to portfolio");
                    recreate();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();

        }

    }

    private void showToast(String s) {
        Toast.makeText(ItemDetailActivity.this, s, Toast.LENGTH_LONG).show();
    }

    public void btnPlaceOrder(View v) {
        Intent i = new Intent(this, TradeActivity.class);
        i.putExtra("itemname", item_name);
        startActivity(i);
    }

    public void goVolumeGraph(View v) {
        startActivity(new Intent(this, CandleStickChartActivity.class));
    }


}
