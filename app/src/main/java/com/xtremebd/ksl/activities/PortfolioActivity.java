package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.PortfolioListAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.PortfolioHelper;
import com.xtremebd.ksl.utils.TopBar;
import com.xtremebd.ksl.utils.WatchlistHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PortfolioActivity extends AppCompatActivity {


    @BindView(R.id.rvPortfolioList)
    RecyclerView rvPortfolioList;

    @BindView(R.id.tvTotalPriceOnLtp)
    TextView tvTotalPriceOnLtp;

    @BindView(R.id.tvTotalPriceOnPurchase)
    TextView tvTotalPriceOnPurhcase;

    @BindView(R.id.tvNetProfit)
    TextView tvNetProfit;

    PortfolioListAdapter adapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    ProgressDialog dialog;


    double totalBuyPrice = 0.0, totalPriceOnLtp = 0.0;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        ButterKnife.bind(this);
        TopBar.attach(this, "PORTFOLIO");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please wait...");
        dialog.setCancelable(false);

        updatePortfolioItems();

    }

    private void viewPortfolioItems() {
        final List<Item> portfolioItems = PortfolioHelper.getPortfolioItems(this);
        rvPortfolioList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new PortfolioListAdapter(portfolioItems);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showCustomizeDialog(portfolioItems.get(position));
            }
        });

        rvPortfolioList.setAdapter(adapter);

        //Show The Calculation
        tvTotalPriceOnLtp.setText("" + totalPriceOnLtp);
        tvTotalPriceOnPurhcase.setText("" + totalBuyPrice);
        double profit = totalPriceOnLtp - totalBuyPrice;
        tvNetProfit.setText("" + profit);
        if (profit < 0) {
            tvNetProfit.setTextColor(Color.RED);
        } else {
            tvNetProfit.setTextColor(Color.GREEN);
        }

    }

    private void showCustomizeDialog(final Item item) {
        final AlertDialog.Builder portfolioAddDialouge = new AlertDialog.Builder(
                this);


        LayoutInflater inflater = getLayoutInflater();
        View dialougeView = inflater.inflate(
                R.layout.dialog_customize_portfolio, null);

        portfolioAddDialouge.setView(dialougeView);

        final AlertDialog alert = portfolioAddDialouge.create();


        TextView tveditStock = dialougeView.findViewById(R.id.tvEditStock);
        TextView removeStock = dialougeView.findViewById(R.id.tvRemoveItem);
        TextView seeItemDetail = dialougeView.findViewById(R.id.tvSeeItemDetail);


        ///Add to stock
        tveditStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editStock(item);
                alert.dismiss();

            }
        });


        //Remove Stock
        removeStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                PortfolioHelper.deleteItemFromPortfolio(PortfolioActivity.this, item.getItem());
                updatePortfolioItems();

            }
        });

        //Item Details
        seeItemDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                Intent i = new Intent(PortfolioActivity.this, ItemDetailActivity.class);
                i.putExtra("item", item.getItem());
                startActivity(i);

            }
        });


        alert.show();


    }

    private void editStock(final Item item) {
        AlertDialog.Builder editStockDialog = new AlertDialog.Builder(
                this);

        LayoutInflater inflater = getLayoutInflater();
        View dialougeView = inflater.inflate(
                R.layout.edit_stock_dialog, null);

        final EditText etNoOfStock = dialougeView.findViewById(R.id.etNoOfStocks);

        etNoOfStock.setText(item.getNoOfStock());
        etNoOfStock.setSelection(etNoOfStock.getText().length());


        editStockDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String noOfStock = etNoOfStock.getText().toString().trim();
                if (noOfStock.isEmpty()) {
                    showToast("No of stock can't be empty");
                    return;
                }


                PortfolioHelper.deleteItemFromPortfolio(PortfolioActivity.this, item.getItem());
                item.setNoOfStock(noOfStock);
                PortfolioHelper.addIteminPortfolio(PortfolioActivity.this, item);
                updatePortfolioItems();
                dialog.dismiss();


            }
        });


        editStockDialog.setView(dialougeView);

        editStockDialog.show();

    }


    private void updatePortfolioItems() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_ALL_ITEMS_UPDATES, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                Item[] items = Geson.g().fromJson(response, Item[].class);
                for (Item i : items) {
                    if (PortfolioHelper.isIteminPortfolio(PortfolioActivity.this, i.getItem())) {
                        Item needToUpdateItem = PortfolioHelper.getItem(PortfolioActivity.this, i.getItem());
                        PortfolioHelper.deleteItemFromPortfolio(PortfolioActivity.this, i.getItem());
                        i.setBuyPrice(needToUpdateItem.getBuyPrice());
                        i.setNoOfStock(needToUpdateItem.getNoOfStock());
                        PortfolioHelper.addIteminPortfolio(PortfolioActivity.this, i);

                        //Calculate Total Buy Price
                        try {
                            double buyPrice = Double.parseDouble(i.getBuyPrice());
                            double ltp = Double.parseDouble(i.getLtp());
                            double noofStocks = Double.parseDouble(i.getNoOfStock());

                            totalBuyPrice += buyPrice * noofStocks;
                            totalPriceOnLtp += ltp * noofStocks;


                        } catch (Exception e) {

                        }

                    }
                }
                viewPortfolioItems();
                Logger.d(response);
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                showToast("Something went wrong");

            }
        });
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
