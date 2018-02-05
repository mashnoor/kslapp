package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.PortfolioListAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.PortfolioHelper;
import com.xtremebd.ksl.utils.TopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PortfolioActivity extends AppCompatActivity {


    @BindView(R.id.rvPortfolioList)
    RecyclerView rvPortfolioList;

    PortfolioListAdapter adapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        ButterKnife.bind(this);
        TopBar.attach(this, "PORTFOLIO");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        List<Item> portfolioItems = PortfolioHelper.getPortfolioItems(this);
        rvPortfolioList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new PortfolioListAdapter(portfolioItems);
        rvPortfolioList.setAdapter(adapter);

    }
}
