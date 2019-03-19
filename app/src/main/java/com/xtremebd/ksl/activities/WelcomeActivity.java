package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.hawk.Hawk;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.MasterAccount;
import com.xtremebd.ksl.utils.Constants;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.TopBar;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Hawk.init(this).build();
        Fabric.with(this, new Crashlytics());
        TopBar.attach(this, "Welcome, " + DBHelper.getMasterAccount(this).getName());
        Log.d("--------", "-" + Hawk.count());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MasterAccount acc = DBHelper.getMasterAccount(this);
        Log.d("--------", acc.getMasterPass());


    }

    public void showAllItems(View v) {
        startActivity(new Intent(WelcomeActivity.this, AllItems.class));

    }

    public void goHome(View v) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        CharSequence items[] = new CharSequence[]{"CSE Market Summary", "DSE Market Summary"};
        adb.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0)
                    startActivity(new Intent(WelcomeActivity.this, CSEMarketSummaryActivity.class));
                else
                    startActivity(new Intent(WelcomeActivity.this, DSEMarketSummary.class));
            }
        });
        adb.setTitle("Select Market");
        adb.setPositiveButton("Cancel", null);
        adb.show();

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

    public void goItrade(View v) {
        startActivity(new Intent(this, TradeActivity.class));
    }
}
