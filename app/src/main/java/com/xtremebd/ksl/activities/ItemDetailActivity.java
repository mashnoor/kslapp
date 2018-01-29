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
import com.xtremebd.ksl.utils.Sidebar;
import com.xtremebd.ksl.utils.WatchlistHelper;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {


    @BindView(R.id.tvLTP)
    TextView tvLTP;
    @BindView(R.id.tvChange)
    TextView tvChange;
    @BindView(R.id.tvChangePercentage)
    TextView tvChangePercentage;
    @BindView(R.id.tvClosePrice)
    TextView tvClosePrice;
    @BindView(R.id.tvTodayOpenPrice)
    TextView tvTodayOpenPrice;
    @BindView(R.id.tvDaysRange)
    TextView tvDaysRange;
    @BindView(R.id.tvTotalTrade)
    TextView tvTotalTrade;
    @BindView(R.id.tvAmountTradedInBdt)
    TextView tvAmountTradedinBdt;
    @BindView(R.id.tvYCP)
    TextView tvYCP;
    @BindView(R.id.tvAdjustOpenPrice)
    TextView tvAdjustOpenPrice;
    @BindView(R.id.tvMarketCapital)
    TextView tvMarketCapital;
    @BindView(R.id.tvMarketCategory)
    TextView tvMarketCategory;

    //Basic Info
    @BindView(R.id.tvAuthorizedCapitalinBdt)
    TextView tvAuthorizedCapitalinBdt;
    @BindView(R.id.tvPaidUpValue)
    TextView tvPaidupValue;
    @BindView(R.id.tvFaceValue)
    TextView tvFaceValue;
    @BindView(R.id.tvTotalNoofSecurities)
    TextView tvTotalNoOfSecurities;
    @BindView(R.id.tv52WeekRange)
    TextView tv52WeekRange;
    @BindView(R.id.tvMarketLot)
    TextView tvMarketLot;

    //PE Ratio
    @BindView(R.id.tvPEBasic)
    TextView tvPEBaic;
    @BindView(R.id.tvPEDiluted)
    TextView tvPEDiluted;

    //History
    @BindView(R.id.tvLastAGM)
    TextView tvLastAgm;
    @BindView(R.id.tvYearEnd)
    TextView tvYearEnd;

    //Others
    @BindView(R.id.tvBonousIssue)
    TextView tvBonusIssue;
    @BindView(R.id.tvRightIssue)
    TextView tvRightIssue;
    @BindView(R.id.tvReserveAndSurplus)
    TextView tvReserveAndSurplus;
    //Share Percentage
    @BindView(R.id.tvDirector)
    TextView tvDirector;
    @BindView(R.id.tvGovernMent)
    TextView tvGovernment;
    @BindView(R.id.tvInstitute)
    TextView tvInstitute;
    @BindView(R.id.tvForeign)
    TextView tvForeign;
    @BindView(R.id.tvPublic)
    TextView tvPublic;


    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.btnWatchList)
    Button btnWatchList;
    @BindView(R.id.btnPortfolio)
    Button btnPortfolio;

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
        Sidebar.attach(this, item_name + "  Details");
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
                tvAdjustOpenPrice.setText(current_item.getOpenPrice());
                tvTotalTrade.setText(current_item.getTotaltrade());
                //tvVolume.setText(current_item.getVolume());
                tvDaysRange.setText(current_item.getDaysrange());
                tvChangePercentage.setText(current_item.getChangepercentage());
                tvTodayOpenPrice.setText(current_item.getOpenprice());
                tvAmountTradedinBdt.setText(current_item.getAmountTradedInBdt());
                tvMarketCategory.setText(current_item.getMarketcatagory());

                tvMarketCapital.setText(current_item.getCapital());
                tvChange.setText(current_item.getChange());

                //Basic Info
                tvAuthorizedCapitalinBdt.setText(current_item.getAuthorizedcapital());
                tvPaidupValue.setText(current_item.getPaidupvalue());
                tvFaceValue.setText(current_item.getFacevalue());
                tvTotalNoOfSecurities.setText(current_item.getNoofsecurities());
                tv52WeekRange.setText(current_item.getWeekrange());
                tvMarketLot.setText(current_item.getMarketlot());

                //PE Ratio
                tvPEBaic.setText(current_item.getPERatioBasic());
                tvPEDiluted.setText(current_item.getPERatioDiluted());

                //History
                tvLastAgm.setText(current_item.getLastagm());
                tvYearEnd.setText(current_item.getYearend());

                //Others
                tvBonusIssue.setText(current_item.getBonousissue());
                tvRightIssue.setText(current_item.getRightissue());
                tvReserveAndSurplus.setText(current_item.getReserveandsurplus());
                //Share Percentage
                tvDirector.setText(current_item.getSpSponsorDirector());
                tvGovernment.setText(current_item.getSpGovt());
                tvInstitute.setText(current_item.getSpInstitute());
                tvForeign.setText(current_item.getSpForeign());
                tvPublic.setText(current_item.getSpPublic());

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
