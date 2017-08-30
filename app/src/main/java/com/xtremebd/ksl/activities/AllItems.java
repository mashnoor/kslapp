package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.AllItemListAdapter;
import com.xtremebd.ksl.models.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllItems extends AppCompatActivity {

    List<Item> allItem;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;


    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        ButterKnife.bind(this);
        itemList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);

        getItemLists();


    }

    private void getItemLists() {
        dialog.show();

        Call<List<Item>> items = ApiInterfaceGetter.getStaticInterface().getAllLatestItemUpdates();
        items.enqueue(new Callback<List<Item>>() {

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                dialog.dismiss();
                allItem = response.body();


                adapter = new AllItemListAdapter(allItem);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent i = new Intent(AllItems.this, ItemDetailActivity.class);
                        i.putExtra("item", allItem.get(position).getItem());
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
