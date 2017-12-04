package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.orhanobut.hawk.Hawk;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.models.News;
import com.xtremebd.ksl.utils.DBHelper;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Hawk.init(this).build();
        Log.d("--------", "-" + Hawk.count());

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
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
    }

    public void goFundRequisition(View v) {
        startActivity(new Intent(WelcomeActivity.this, FundRequisitionActivity.class));
    }

    public void goMarketMovers(View v) {
        startActivity(new Intent(WelcomeActivity.this, MarketMoversActivity.class));
    }

    public void goNews(View v) {
        startActivity(new Intent(this, NewsActivity.class));
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

    public void goFinacialStatement(View v)
    {
        startActivity(new Intent(this, FinancialLedgerActivity.class));
    }
}
