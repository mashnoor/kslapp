package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.hawk.Hawk;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.Constants;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.TopBar;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Hawk.init(this).build();
        TopBar.attach(this, "Welcome, " + DBHelper.getMasterAccount(this).getName());
        Log.d("--------", "-" + Hawk.count());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MasterAccount acc = DBHelper.getMasterAccount(this);
        Log.d("--------", acc.getMasterPass());


    }

    public void showPlaceOrderActivity(View v) {
        startActivity(new Intent(WelcomeActivity.this, TradeActivity.class));
    }

    public void showAllItems(View v) {
        startActivity(new Intent(WelcomeActivity.this, AllItems.class));

    }

    public void goHome(View v) {
        startActivity(new Intent(WelcomeActivity.this, MarketSummaryActivity.class));
    }

    public void goFundRequisition(View v) {
        startActivity(new Intent(WelcomeActivity.this, FundRequisitionActivity.class));
    }

    public void goMarketMovers(View v) {
        startActivity(new Intent(WelcomeActivity.this, MarketMoversActivity.class));
    }

    public void goNews(View v) {
        Intent i = new Intent(this, CseNewsActivity.class);
        i.putExtra("which", "all");
        startActivity(i);
    }

    public void goIPO(View v) {
        startActivity(new Intent(this, IPOActivity.class));
    }

    public void goOpenAccount(View v) {
        startActivity(new Intent(this, OpenaccountActivity.class));
    }

    public void goITSAccounts(View v) {
        startActivity(new Intent(this, ITSAccountsActivity.class));
    }

    public void goWatchlist(View v) {
        startActivity(new Intent(this, WatchlistActivity.class));
    }

    public void goPortfolio(View v) {
        startActivity(new Intent(this, PortfolioActivity.class));
    }

    public void goDiscussion(View v) {
        startActivity(new Intent(this, DiscussionActivity.class));
    }

    public void goLogout(View v) {
        DBHelper.setMasterAccount(this, null);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void goNotifications(View v) {
        startActivity(new Intent(this, NotificationsActivity.class));
    }

    public void goPortfolioStatement(View v) {
        startActivity(new Intent(this, PortfolioStatement.class));
    }

    public void goOrderStatus(View v) {
        startActivity(new Intent(this, OrderStatus.class));
    }

    public void goFinacialStatement(View v) {
        startActivity(new Intent(this, FinancialLedgerActivity.class));
    }

    public void goKslNews(View v) {
        startActivity(new Intent(this, KSLNews.class));
    }

    public void goTopTenGainers(View v) {
        Intent i = new Intent(this, TopGainerLoserActivity.class);
        i.putExtra("which", Constants.TOP_GAINER);
        startActivity(i);
    }

    public void goTopTenLosers(View v) {
        Intent i = new Intent(this, TopGainerLoserActivity.class);
        i.putExtra("which", Constants.TOP_LOSERS);
        startActivity(i);
    }

    public void goAbout(View v) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void goPriceAlert(View v) {
        startActivity(new Intent(this, PriceAlertActivity.class));
    }
}
