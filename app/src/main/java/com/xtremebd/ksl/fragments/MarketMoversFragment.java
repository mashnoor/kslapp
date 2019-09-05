package com.xtremebd.ksl.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_market_movers, container, false);
        TextView tvsubject = v.findViewById(R.id.tvmarketmover_subject);
        rvlistMarketMovers = v.findViewById(R.id.rvMarketMovers);
        rvlistMarketMovers.setLayoutManager(new LinearLayoutManager(getContext()));
        int position = getArguments().getInt("position");
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading Data. Please wait...");


        switch (position) {
            case 0:
                //By Value
                tvsubject.setText("Value");
                client = new AsyncHttpClient();
                client.get(AppURLS.GET_MARKET_MOVERS_BY_VALUE, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        dialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        List<Item> valueMovers = Arrays.asList(Geson.g().fromJson(response, Item[].class));
                        adapter = new MarketMoversAdapter(valueMovers, 0);
                        rvlistMarketMovers.setAdapter(adapter);
                        dialog.dismiss();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();

                    }
                });


                break;
            case 1:
                tvsubject.setText("Volume");
                client = new AsyncHttpClient();
                client.get(AppURLS.GET_MARKET_MOVERS_BY_VOLUME, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        dialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        List<Item> valueMovers = Arrays.asList(Geson.g().fromJson(response, Item[].class));
                        adapter = new MarketMoversAdapter(valueMovers, 1);
                        rvlistMarketMovers.setAdapter(adapter);
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                });


                break;
            case 2:
                tvsubject.setText("Trade");
                client = new AsyncHttpClient();
                client.get(AppURLS.GET_MARKET_MOVERS_BY_TRADE, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        dialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = new String(responseBody);
                        List<Item> valueMovers = Arrays.asList(Geson.g().fromJson(response, Item[].class));
                        adapter = new MarketMoversAdapter(valueMovers, 2);
                        rvlistMarketMovers.setAdapter(adapter);
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                });
                break;

        }


        return v;
    }


}
