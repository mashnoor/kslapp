package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.News;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void showPlaceOrderActivity(View v)
    {
        startActivity(new Intent(WelcomeActivity.this, TradeActivity.class));
    }

    public void showAllItems(View v)
    {
        startActivity(new Intent(WelcomeActivity.this, AllItems.class));

    }

    public void goHome(View v)
    {
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
    }

    public void goMarketMovers(View v)
    {
        startActivity(new Intent(WelcomeActivity.this, MarketMoversActivity.class));
    }

    public void goNews(View v)
    {
        startActivity(new Intent(this, NewsActivity.class));
    }
    public void goIPO(View v)
    {
        startActivity(new Intent(this, IPOActivity.class));
    }
    public void goOpenAccount(View v)
    {
        startActivity(new Intent(this, OpenaccountActivity.class));
    }

}
