package com.xtremebd.ksl.models;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

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
    //For Portfolio
    private String buyPrice;
    private String noOfStock;

    public String getBuyPrice() {
        return buyPrice;
    }

    public String getNoOfStock() {
        return noOfStock;
    }

    public void setNoOfStock(String noOfStock) {
        this.noOfStock = noOfStock;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getNetProfit()
    {
        if(getLtp().equals("Not Traded Today"))
            return "0";
        if(getBuyPrice().equals("Not Traded Today"))
            return "0";
        double buyPrice = Double.parseDouble(getBuyPrice());
        double noStock = Double.parseDouble(getNoOfStock());
        double ltp = Double.parseDouble(getLtp());

        return String.valueOf((ltp-buyPrice) * noStock);
    }
    public String getNetProfitPercentage()
    {
        if(getBuyPrice().equals("Not Traded Today") || getLtp().equals("Not Traded Today"))
        {
            return "0";
        }

        double buyPrice = Double.parseDouble(getBuyPrice());

        double ltp = Double.parseDouble(getLtp());
        double profitRatio = (ltp - buyPrice) /ltp;
        double percentage =  profitRatio*100;
        DecimalFormat df = new DecimalFormat("0.00");
        return String.valueOf(df.format(percentage)).concat("%");
    }

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
    public int getColor()
    {
        try

        {
            double change = Double.parseDouble(getChange());
            if(change<0)
            {
                return Color.RED;
            }
            else
            {
                return Color.GREEN;
            }

        }
        catch (Exception e)
        {
            return Color.RED;
        }
    }
}
