package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.beardedhen.androidbootstrap.BootstrapButton;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;
import com.xtremebd.ksl.utils.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity {


    @BindView(R.id.tvClosePrice) TextView tvClosePrice;
    @BindView(R.id.tvLTP) TextView tvLTP;
    @BindView(R.id.tvYCP) TextView tvYCP;
    @BindView(R.id.tvOpenPrice) TextView tvOpenPrice;
    @BindView(R.id.tvTrade) TextView tvTrade;
    @BindView(R.id.tvVolume) TextView tvVolume;
    @BindView(R.id.tvRange) TextView tvRange;
    @BindView(R.id.tvLTD) TextView tvLTD;
    @BindView(R.id.tvCapital) TextView tvCapital;
    @BindView(R.id.tvChange) TextView tvChange;
    @BindView(R.id.tvItemName) TextView tvItemName;
    @BindView(R.id.btnWatchList)
    BootstrapButton btnWatchList;

    String item_name;
    Item current_item;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        item_name = getIntent().getStringExtra("item");
        tvItemName.setText(item_name);
        if(DBHelper.isIteminWatchlist(this, item_name))
        {
            btnWatchList.setText("Remove from Watchlist");
        }
        else
        {
            btnWatchList.setText("Add to Watchlist");
        }

        getIntemDetail(item_name);

    }

    private void getIntemDetail(final String item_name)
    {

        ApiInterfaceGetter.getStaticInterface().getItemDetail(item_name).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                current_item = response.body();
                current_item.setItem(item_name);
                tvClosePrice.setText(current_item.getCloseprice());
                tvLTP.setText(current_item.getLtp());
                tvYCP.setText(current_item.getYesterdayClosePrice());
                tvOpenPrice.setText(current_item.getOpenPrice());
                tvTrade.setText(current_item.getTrade());
                tvVolume.setText(current_item.getVolume());
                tvRange.setText(current_item.getRange());
                tvLTD.setText(current_item.getLastTradeDate());
                tvCapital.setText(current_item.getCapital());
                tvChange.setText(current_item.getChange());

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {


            }
        });

    }

    public void actionWatchlist(View v) 
    {
        if(!DBHelper.isIteminWatchlist(ItemDetailActivity.this, item_name))
        {
            DBHelper.addIteminWatchList(ItemDetailActivity.this, current_item);
            showToast("Item added to watchlist successfully");

        }

    }

    private void showToast(String s)
    {
        Toast.makeText(ItemDetailActivity.this, s, Toast.LENGTH_LONG).show();
    }


}
