package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mashnoor on 24/1/18.
 */

public class DayEndData {

    @SerializedName("date")
    private String date;

    @SerializedName("volume")
    private String volume;

    @SerializedName("high")
    private String highPrice;

    @SerializedName("low")
    private String lowPrice;

    @SerializedName("openprice")
    private String openPrice;


    @SerializedName("closeprice")
    private String closePrice;

    @SerializedName("diff")
    private String difference;

    public String getDate() {
        return date;
    }

    public Float getDifference() {
        return Float.valueOf(difference);
    }

    public Float getVolume() {
        return Float.valueOf(volume);
    }

    public float getLowPrice() {
        return Float.valueOf(lowPrice);
    }

    public float getHighPrice() {
        return Float.valueOf(highPrice);
    }

    public float getClosePrice() {
        return Float.valueOf(closePrice);
    }

    public float getOpenPrice() {
        return Float.valueOf(openPrice);
    }
}
