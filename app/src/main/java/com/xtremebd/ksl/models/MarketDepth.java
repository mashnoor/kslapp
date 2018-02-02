package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mashnoor on 30/1/18.
 */

public class MarketDepth {
    @SerializedName("price")
    private String price;

    @SerializedName("volume")
    private String volume;

    public String getVolume() {
        return volume;
    }

    public String getPrice() {
        return price;
    }
}
