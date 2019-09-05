package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

public class PortfolioDetail {

    @SerializedName("scrip_id")
    private String scripId;

    @SerializedName("is_margin_allowed")
    private String isMarginAllowed;

    @SerializedName("quantity")
    private double quantity;

    @SerializedName("saleable_quantity")
    private double saleableQuantity;

    @SerializedName("rate")
    private double rate;

    @SerializedName("bep")
    private double bep;

    @SerializedName("total_buy_cost")
    private double totalBuyCost;

    @SerializedName("market_rate")
    private Object marketRate;

    @SerializedName("market_value")
    private double marketValue;

    @SerializedName("unrealized_pl")
    private double unrealizedPl;

    public String getScripId() {
        return scripId;
    }

    public void setScripId(String scripId) {
        this.scripId = scripId;
    }

    public String getIsMarginAllowed() {
        return isMarginAllowed;
    }

    public void setIsMarginAllowed(String isMarginAllowed) {
        this.isMarginAllowed = isMarginAllowed;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getSaleableQuantity() {
        return saleableQuantity;
    }

    public void setSaleableQuantity(double saleableQuantity) {
        this.saleableQuantity = saleableQuantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getBep() {
        return bep;
    }

    public void setBep(double bep) {
        this.bep = bep;
    }

    public double getTotalBuyCost() {
        return totalBuyCost;
    }

    public void setTotalBuyCost(double totalBuyCost) {
        this.totalBuyCost = totalBuyCost;
    }

    public Object getMarketRate() {
        return marketRate;
    }

    public void setMarketRate(Object marketRate) {
        this.marketRate = marketRate;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getUnrealizedPl() {
        return unrealizedPl;
    }

    public void setUnrealizedPl(double unrealizedPl) {
        this.unrealizedPl = unrealizedPl;
    }

}
