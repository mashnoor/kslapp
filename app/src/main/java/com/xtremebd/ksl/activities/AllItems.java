package com.xtremebd.ksl.activities;

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
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.AllItemListAdapter;
import com.xtremebd.ksl.models.Item;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class AllItems extends AppCompatActivity {

    AsyncHttpClient client;
    List<Item> allItem;
    AllItemListAdapter adapter;
    @BindView(R.id.itemList)
    RecyclerView itemList;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        ButterKnife.bind(this);
        itemList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        client = new AsyncHttpClient();
        getItemLists();
        dialog = new ProgressDialog(this);

    }

    private void getItemLists() {
        client.get(AppURLS.GET_ALL_ITEMS, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                dialog.setMessage("Getting data from server...");
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Gson gson = new Gson();
                Type type = new TypeToken<List<Item>>() {}.getType();
                allItem = gson.fromJson(response, type);
                Log.d("--------", allItem.get(0).getItem());
                adapter = new AllItemListAdapter(allItem);
                itemList.setAdapter(adapter);
                dialog.dismiss();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Toast.makeText(AllItems.this, "Some Error Occured", Toast.LENGTH_LONG).show();

            }
        });
    }

}
