package com.xtremebd.ksl.models;


import com.google.gson.annotations.SerializedName;

public class IpoInformation {

    @SerializedName("application_amount")
    
    private Integer applicationAmount;
    @SerializedName("allotment_amount")
    
    private Integer allotmentAmount;
    @SerializedName("allotment_quantity")
    
    private Integer allotmentQuantity;
    @SerializedName("forfeit_amount")
    
    private Integer forfeitAmount;
    @SerializedName("net_refund_amount")
    
    private Integer netRefundAmount;

    public Integer getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(Integer applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public Integer getAllotmentAmount() {
        return allotmentAmount;
    }

    public void setAllotmentAmount(Integer allotmentAmount) {
        this.allotmentAmount = allotmentAmount;
    }

    public Integer getAllotmentQuantity() {
        return allotmentQuantity;
    }

    public void setAllotmentQuantity(Integer allotmentQuantity) {
        this.allotmentQuantity = allotmentQuantity;
    }

    public Integer getForfeitAmount() {
        return forfeitAmount;
    }

    public void setForfeitAmount(Integer forfeitAmount) {
        this.forfeitAmount = forfeitAmount;
    }

    public Integer getNetRefundAmount() {
        return netRefundAmount;
    }

    public void setNetRefundAmount(Integer netRefundAmount) {
        this.netRefundAmount = netRefundAmount;
    }




}