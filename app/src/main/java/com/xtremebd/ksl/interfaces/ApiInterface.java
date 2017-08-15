package com.xtremebd.ksl.interfaces;


import com.xtremebd.ksl.models.Index;
import com.xtremebd.ksl.models.Item;
import com.xtremebd.ksl.models.MarketSummary;
import com.xtremebd.ksl.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Mashnoor on 8/14/17.
 */

public interface ApiInterface {

    @GET("all_items_latest_update.txt")
    Call<List<Item>> getAllLatestItemUpdates();

    @GET("homedatas/all_indexes.txt")
    Call<Index> getHomeIndex();

    @GET("homedatas/market_summary.txt")
    Call<MarketSummary> getMarketSummary();

    @GET("marketmovers/top_twenty_by_trade.txt")
    Call<List<Item>> getTopTwentyByTradeMaerketMover();

    @GET("marketmovers/top_twenty_by_volume.txt")
    Call<List<Item>> getTopTwentyByVolumeMaerketMover();

    @GET("marketmovers/top_twenty_by_value.txt")
    Call<List<Item>> getTopTwentyByValueMaerketMover();

    @GET("latest_news.txt")
    Call<List<News>> getLatestNews();

    @GET("item_details/{item}.txt")
    Call<Item> getItemDetail(@Path("item") String item);






}
