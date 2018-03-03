package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.PriceAlertAdapter;
import com.xtremebd.ksl.utils.PriceAlertHelper;
import com.xtremebd.ksl.utils.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceAlertActivity extends AppCompatActivity {

    @BindView(R.id.rvPriceAlert)
    RecyclerView rvPriceAlert;

    PriceAlertAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_alert);
        ButterKnife.bind(this);
        TopBar.attach(this, "PRICE ALERT");

        rvPriceAlert.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        updatePriceAlert();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void updatePriceAlert()
    {
        showPriceAlert();
    }

    private void showPriceAlert()
    {

        adapter = new PriceAlertAdapter(PriceAlertHelper.getPriceAlertItems(this));
        rvPriceAlert.setAdapter(adapter);
    }
}
