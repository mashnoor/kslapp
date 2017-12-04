

package com.xtremebd.ksl.models;


import com.google.gson.annotations.SerializedName;

public class OrderStatus {

    @SerializedName("pricetype")

    private String pricetype;
    @SerializedName("settlor")

    private String settlor;
    @SerializedName("exch")

    private String exch;
    @SerializedName("mc")

    private String mc;
    @SerializedName("minfillqty")

    private String minfillqty;
    @SerializedName("executedqty")

    private String executedqty;
    @SerializedName("price")

    private String price;
    @SerializedName("avgprice")

    private String avgprice;
    @SerializedName("time")

    private String time;
    @SerializedName("symbol")

    private String symbol;
    @SerializedName("orderno")

    private String orderno;
    @SerializedName("boardtype")

    private String boardtype;
    @SerializedName("bs")

    private String bs;
    @SerializedName("status")

    private String status;
    @SerializedName("orderqty")

    private String orderqty;
    @SerializedName("scripgroup")

    private String scripgroup;

    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    public String getSettlor() {
        return settlor;
    }

    public void setSettlor(String settlor) {
        this.settlor = settlor;
    }

    public String getExch() {
        return exch;
    }

    public void setExch(String exch) {
        this.exch = exch;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getMinfillqty() {
        return minfillqty;
    }

    public void setMinfillqty(String minfillqty) {
        this.minfillqty = minfillqty;
    }

    public String getExecutedqty() {
        return executedqty;
    }

    public void setExecutedqty(String executedqty) {
        this.executedqty = executedqty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(String avgprice) {
        this.avgprice = avgprice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getBoardtype() {
        return boardtype;
    }

    public void setBoardtype(String boardtype) {
        this.boardtype = boardtype;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(String orderqty) {
        this.orderqty = orderqty;
    }

    public String getScripgroup() {
        return scripgroup;
    }

    public void setScripgroup(String scripgroup) {
        this.scripgroup = scripgroup;
    }

}