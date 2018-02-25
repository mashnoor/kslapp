package com.xtremebd.ksl.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xtremebd.ksl.R;
import com.xtremebd.ksl.adapters.MarketMoversAdapter;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.utils.AppURLS;
import com.xtremebd.ksl.utils.Geson;

import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MarketMoversFragment extends Fragment {


    RecyclerView rvlistMarketMovers;
    MarketMoversAdapter adapter;
    AsyncHttpClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_market_movers, container, false);
        TextView tvsubject = v.findViewById(R.id.tvmarketmover_subject);
        rvlistMarketMovers = v.findViewById(R.id.rvMarketMovers);
        rvlistMarketMovers.setLayoutManager(new LinearLayoutManager(getContext()));
        int position = getArguments().getInt("position");


        switch (position) {
            case 0:
                //By Value
                tvsubject.setText("Value");
                client = new AsyncHttpClient();
                client.get(AppURLS.GET_MARKET_MOVERS_BY_VALUE, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        List<Item> valueMovers = Arrays.asList(Geson.g().fromJson(response, Item[].class));
                        adapter = new MarketMoversAdapter(valueMovers, 0);
                        rvlistMarketMovers.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });


                break;
            case 1:
                tvsubject.setText("Volume");
                client = new AsyncHttpClient();
                client.get(AppURLS.GET_MARKET_MOVERS_BY_VOLUME, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        List<Item> valueMovers = Arrays.asList(Geson.g().fromJson(response, Item[].class));
                        adapter = new MarketMoversAdapter(valueMovers, 1);
                        rvlistMarketMovers.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });


                break;
            case 2:
                tvsubject.setText("Trade");
                client = new AsyncHttpClient();
                client.get(AppURLS.GET_MARKET_MOVERS_BY_TRADE, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        List<Item> valueMovers = Arrays.asList(Geson.g().fromJson(response, Item[].class));
                        adapter = new MarketMoversAdapter(valueMovers, 2);
                        rvlistMarketMovers.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
                break;

        }


        return v;
    }


}
