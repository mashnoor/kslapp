package com.xtremebd.ksl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PortfolioDetail {

    @SerializedName("scrip_id")
    private String scripId;

    @SerializedName("is_margin_allowed")
    private String isMarginAllowed;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("saleable_quantity")
    private Integer saleableQuantity;

    @SerializedName("rate")
    private float rate;

    @SerializedName("bep")
    private float bep;

    @SerializedName("total_buy_cost")
    private Integer totalBuyCost;

    @SerializedName("market_rate")
    private Object marketRate;

    @SerializedName("market_value")
    private Integer marketValue;

    @SerializedName("unrealized_pl")
    private Integer unrealizedPl;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSaleableQuantity() {
        return saleableQuantity;
    }

    public void setSaleableQuantity(Integer saleableQuantity) {
        this.saleableQuantity = saleableQuantity;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public float getBep() {
        return bep;
    }

    public void setBep(Integer bep) {
        this.bep = bep;
    }

    public Integer getTotalBuyCost() {
        return totalBuyCost;
    }

    public void setTotalBuyCost(Integer totalBuyCost) {
        this.totalBuyCost = totalBuyCost;
    }

    public Object getMarketRate() {
        return marketRate;
    }

    public void setMarketRate(Object marketRate) {
        this.marketRate = marketRate;
    }

    public Integer getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Integer marketValue) {
        this.marketValue = marketValue;
    }

    public Integer getUnrealizedPl() {
        return unrealizedPl;
    }

    public void setUnrealizedPl(Integer unrealizedPl) {
        this.unrealizedPl = unrealizedPl;
    }

}
