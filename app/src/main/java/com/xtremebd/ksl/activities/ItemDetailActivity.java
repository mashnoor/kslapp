package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.PortfolioHelper;
import com.xtremebd.ksl.utils.WatchlistHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    SpotsDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        item_name = getIntent().getStringExtra("item");
        tvItemName.setText(item_name);
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
        dialog.show();


        ApiInterfaceGetter.getStaticInterface().getItemDetail(item_name).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                current_item = response.body();
                current_item.setItem(item_name);
                tvClosePrice.setText(current_item.getCloseprice());
                tvLTP.setText(current_item.getLtp());
                tvYCP.setText(current_item.getYesterdayClosePrice());
                tvOpenPrice.setText(current_item.getOpenPrice());
                tvTrade.setText(current_item.getTrade());
                tvVolume.setText(current_item.getVolume());
                tvRange.setText(current_item.getRange());
                tvLTD.setText(current_item.getLastTradeDate());
                tvCapital.setText(current_item.getCapital());
                tvChange.setText(current_item.getChange());
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
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


}
