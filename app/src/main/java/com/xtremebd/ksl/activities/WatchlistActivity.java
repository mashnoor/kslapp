package com.xtremebd.ksl.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
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
import com.xtremebd.ksl.utils.Constants;
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.PortfolioHelper;
import com.xtremebd.ksl.utils.TopBar;
import com.xtremebd.ksl.utils.WatchlistHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WatchlistActivity extends AppCompatActivity {

    List<Item> allItem;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;
    private FirebaseAnalytics mFirebaseAnalytics;
    ProgressDialog dialog;
    final private int SEARCH_CODE = 11;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
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
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                showToast("Something went wrong");

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                showCustomizeDialog(allItem.get(position));
            }
        });
        itemList.setAdapter(adapter);
    }
    private void showCustomizeDialog(final Item item) {
        final AlertDialog.Builder customizeWatchlistDialogue = new AlertDialog.Builder(
                this);


        LayoutInflater inflater = getLayoutInflater();
        View dialougeView = inflater.inflate(
                R.layout.dialog_customize_watchlist, null);

        customizeWatchlistDialogue.setView(dialougeView);

        final AlertDialog alert = customizeWatchlistDialogue.create();


        TextView removeItem = dialougeView.findViewById(R.id.tvRemoveItem);
        TextView seeItemDetail = dialougeView.findViewById(R.id.tvSeeItemDetail);




        //Remove Item
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                WatchlistHelper.deleteItemFromWatchList(WatchlistActivity.this, item.getItem());
                recreate();

            }
        });

        //Item Details
        seeItemDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                Intent i = new Intent(WatchlistActivity.this, ItemDetailActivity.class);
                i.putExtra("which", Constants.CSE_ITEM_DETAIL);
                i.putExtra("item", item.getItem());
                startActivity(i);

            }
        });


        alert.show();


    }

    public void goAddItem(View v)
    {
        Intent i = new Intent(this, SelectItemForTradeActivity.class);
        startActivityForResult(i, SEARCH_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_CODE && resultCode == Activity.RESULT_OK) {
            String itemName = data.getStringExtra("itemname");
            if (!WatchlistHelper.isIteminWatchlist(this, itemName)) {
                getItemDetail(itemName);
            }
            else
            {
                showToast("Item already added in watchlist!");
            }
        }

    }

    private void getItemDetail(final String item_name) {


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(AppURLS.GET_CSE_ITEM_DETAIL + item_name, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                Item current_item = Geson.g().fromJson(response, Item.class);
                current_item.setItem(item_name);
                WatchlistHelper.addIteminWatchList(WatchlistActivity.this, current_item);
                showToast("Item added to watchlist successfully!");
                recreate();


                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                showToast("Couldn't load data. Try again");
                Logger.d(error.getMessage());
                finish();

            }
        });


    }

}
