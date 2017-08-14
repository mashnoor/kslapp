package com.xtremebd.ksl.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xtremebd.ksl.clients.ApiClient;
import com.xtremebd.ksl.interfaces.ApiInterface;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.AllItemListAdapter;
import com.xtremebd.ksl.models.Item;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllItems extends AppCompatActivity {

    List<Item> allItem;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;
    ApiInterface apiInterface;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        itemList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog = new SpotsDialog(this, R.style.CustomLoadingDialog);
        
        getItemLists();


    }

    private void getItemLists() {
        dialog.show();

        Call<List<Item>> items = apiInterface.getAllLatestItemUpdates();
        items.enqueue(new Callback<List<Item>>() {

            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                dialog.dismiss();
                allItem = response.body();

                //Log.d("--------", allItem.get(0).getItem());
                adapter = new AllItemListAdapter(allItem);
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
