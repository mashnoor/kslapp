package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.AllItemListAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.DBHelper;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;
import com.xtremebd.ksl.utils.WatchlistHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class WatchlistActivity extends AppCompatActivity {

    List<Item> allItem;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;
    private FirebaseAnalytics mFirebaseAnalytics;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        ButterKnife.bind(this);
        TopBar.attach(this, "WATCHLIST");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        dialog = new ProgressDialog(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        dialog.setMessage("Getting data from server...");
        updateWatchlistItems();


    }

    private void updateWatchlistItems() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_ALL_ITEMS_UPDATES, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);
                Item[] items = Geson.g().fromJson(response, Item[].class);
                for (Item i : items) {
                    if (WatchlistHelper.isIteminWatchlist(WatchlistActivity.this, i.getItem())) {
                        WatchlistHelper.deleteItemFromWatchList(WatchlistActivity.this, i.getItem());
                        WatchlistHelper.addIteminWatchList(WatchlistActivity.this, i);
                    }
                }
                viewWatchListItems();
                Logger.d(response);
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                showToast("Something went wrong");

            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void viewWatchListItems() {
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
