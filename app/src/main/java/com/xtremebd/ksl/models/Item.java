package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 8/10/17.
 */

public class Item{
    @SerializedName("item")
    private String item;
    @SerializedName("volume")
    private String volume;
    @SerializedName("change")
    private String change;
    @SerializedName("ltp")
    private String ltp;
    @SerializedName("value")
    private String value;
    @SerializedName("trade")
    private String trade;
    @SerializedName("closeprice")
    private String closeprice;
    @SerializedName("ycp")
    private String yesterdayClosePrice;
    @SerializedName("openprice")
    private String openPrice;
    @SerializedName("range")
    private String range;
    @SerializedName("ltd")
    private String lastTradeDate;
    @SerializedName("capital")
    private String capital;




    public String getCloseprice() {
        return closeprice;
    }

    public String getYesterdayClosePrice() {
        return yesterdayClosePrice;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public String getRange() {
        return range;
    }

    public String getLastTradeDate() {
        return lastTradeDate;
    }

    public String getCapital() {
        return capital;
    }

    public String getValue() {
        return value;
    }

    public String getTrade() {
        return trade;
    }

    public boolean isChangeNegetive() {
        float change_float = Float.parseFloat(change);
        if (change_float < 0) return true;
        return false;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public String getVolume() {
        return volume;
    }

    public String getChange() {
        return change;
    }

    public String getLtp() {
        return ltp;
    }
}
