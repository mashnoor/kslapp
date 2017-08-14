package com.xtremebd.ksl.interfaces;

import com.xtremebd.ksl.models.Index;
import com.xtremebd.ksl.models.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Mashnoor on 8/14/17.
 */

public interface ApiInterface {

    @GET("all_items_latest_update.txt")
    Call<List<Item>> getAllLatestItemUpdates();

    @GET("homedatas/all_indexes.txt")
    Call<Index> getHomeIndex();





}
