package com.xtremebd.ksl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.AllItemListAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.WatchlistHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchlistActivity extends AppCompatActivity {

    List<Item> allItem;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        allItem = WatchlistHelper.getWatchlistItems(this);
        itemList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AllItemListAdapter(allItem);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent i = new Intent(WatchlistActivity.this, ItemDetailActivity.class);
                i.putExtra("item", allItem.get(position).getItem());
                startActivity(i);
            }
        });
        itemList.setAdapter(adapter);
    }
}
