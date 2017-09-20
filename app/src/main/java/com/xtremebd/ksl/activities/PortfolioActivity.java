package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.PortfolioListAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.DBHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PortfolioActivity extends AppCompatActivity {


    @BindView(R.id.rvPortfolioList)
    RecyclerView rvPortfolioList;

    PortfolioListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        ButterKnife.bind(this);
        List<Item> portfolioItems = DBHelper.getPortfolioItems(this);
        rvPortfolioList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new PortfolioListAdapter(portfolioItems);
        rvPortfolioList.setAdapter(adapter);

    }
}
