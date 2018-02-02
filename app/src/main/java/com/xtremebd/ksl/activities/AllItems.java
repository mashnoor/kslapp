package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.AllItemListAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.Sidebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllItems extends AppCompatActivity {

    List<Item> allItem, allItemCopy;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;

    @BindView(R.id.etSearch)
    EditText etSearch;


    AlertDialog dialog;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        ButterKnife.bind(this);
        Sidebar.attach(this, "All Items");
        itemList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        Logger.addLogAdapter(new AndroidLogAdapter());

        getItemLists();


    }

    private void getItemLists() {
        dialog.show();

        ApiInterfaceGetter.getStaticInterface().getAllLatestItemUpdates().enqueue(new Callback<List<Item>>() {

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                dialog.dismiss();
                allItem = response.body();
                allItemCopy = new ArrayList<>(allItem);


                adapter = new AllItemListAdapter(allItemCopy);
                Logger.d(allItem.size());

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
                            Logger.d("Entering");
                            for (Item item : allItem) {

                                if (item.getItem().toLowerCase().contains(searchText.toLowerCase())) {
                                    allItemCopy.add(item);
                                    Logger.d(item);
                                }
                            }
                        }
                        Logger.d(allItem.size() + " - " + allItemCopy.size());
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
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                dialog.dismiss();
                Log.d("--------", "Error Occured");

            }
        });
    }

}
