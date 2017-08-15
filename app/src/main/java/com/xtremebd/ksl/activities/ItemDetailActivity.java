package com.xtremebd.ksl.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;

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



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        String item_name = getIntent().getStringExtra("item");
        tvItemName.setText(item_name);
        getIntemDetail(item_name);
    }

    private void getIntemDetail(final String item_name)
    {

        ApiInterfaceGetter.getInterface().getItemDetail(item_name).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                tvClosePrice.setText(item.getCloseprice());
                tvLTP.setText(item.getLtp());
                tvYCP.setText(item.getYesterdayClosePrice());
                tvOpenPrice.setText(item.getOpenPrice());
                tvTrade.setText(item.getTrade());
                tvVolume.setText(item.getVolume());
                tvRange.setText(item.getRange());
                tvLTD.setText(item.getLastTradeDate());
                tvCapital.setText(item.getCapital());
                tvChange.setText(item.getChange());

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {


            }
        });

    }


}
