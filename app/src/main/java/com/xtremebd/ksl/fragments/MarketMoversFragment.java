package com.xtremebd.ksl.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.MarketMoversAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.ApiInterfaceGetter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketMoversFragment extends Fragment {



    RecyclerView rvlistMarketMovers;
    MarketMoversAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_market_movers, container, false);
        TextView tvsubject = v.findViewById(R.id.tvmarketmover_subject);
        rvlistMarketMovers = v.findViewById(R.id.rvMarketMovers);
        rvlistMarketMovers.setLayoutManager(new LinearLayoutManager(getContext()));
        int position = getArguments().getInt("position");

        switch (position)
        {
            case 0:
                //By Value
                tvsubject.setText("Value");
                ApiInterfaceGetter.getInterface().getTopTwentyByValueMaerketMover().enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                        adapter = new MarketMoversAdapter(response.body(), 0);
                        rvlistMarketMovers.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Log.d("-------- Err", t.getMessage());
                    }
                });


                break;
            case 1:
                tvsubject.setText("Volume");
                ApiInterfaceGetter.getInterface().getTopTwentyByVolumeMaerketMover().enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                        adapter = new MarketMoversAdapter(response.body(), 1);
                        rvlistMarketMovers.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Log.d("-------- Err", t.getMessage());
                    }
                });

                break;
            case 2:
                tvsubject.setText("Trade");
                ApiInterfaceGetter.getInterface().getTopTwentyByTradeMaerketMover().enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                        adapter = new MarketMoversAdapter(response.body(), 2);
                        rvlistMarketMovers.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Log.d("-------- Err", t.getMessage());
                    }
                });

                break;

        }


        return v;
    }








}
