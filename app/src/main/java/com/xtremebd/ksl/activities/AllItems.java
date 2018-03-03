package com.xtremebd.ksl.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
import com.xtremebd.ksl.utils.Geson;
import com.xtremebd.ksl.utils.TopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class AllItems extends AppCompatActivity {

    List<Item> allItem, allItemCopy;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;

    @BindView(R.id.etSearch)
    EditText etSearch;


    ProgressDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ButterKnife.bind(this);
        TopBar.attach(this, "All Items");
        itemList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading. Please wait...");
        Logger.addLogAdapter(new AndroidLogAdapter());

        getItemLists();
        registerSwipeListener();


    }

    private void registerSwipeListener() {
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.layoutSwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getItemLists();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    private void getItemLists() {
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
                allItem = Arrays.asList(Geson.g().fromJson(response, Item[].class));

                allItemCopy = new ArrayList<>();
                allItemCopy.addAll(allItem);

                adapter = new AllItemListAdapter(allItemCopy);

                Logger.d(allItemCopy.get(0).getItem());

                etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String searchText = etSearch.getText().toString();
                        allItemCopy.clear();
                        Logger.d(allItem.size() + " - " + allItemCopy.size());
                        if (searchText.isEmpty()) {
                            allItemCopy.addAll(allItem);

                        } else {

                            for (Item item : allItem) {

                                if (item.getItem().toLowerCase().contains(searchText.toLowerCase())) {
                                    allItemCopy.add(item);
                                    Logger.d(item);
                                }
                            }
                        }

                        adapter.notifyDataSetChanged();


                    }
                });
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent i = new Intent(AllItems.this, ItemDetailActivity.class);
                        i.putExtra("item", allItemCopy.get(position).getItem());
                        startActivity(i);
                    }
                });

                itemList.setAdapter(adapter);
                dialog.dismiss();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(AllItems.this, "Error! Pull down to refresh", Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
