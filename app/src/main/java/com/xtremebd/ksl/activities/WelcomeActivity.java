package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xtremebd.ksl.R;

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

}
