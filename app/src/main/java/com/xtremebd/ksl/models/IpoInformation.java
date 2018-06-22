package com.xtremebd.ksl.models;


import com.google.gson.annotations.SerializedName;

public class IpoInformation {

    @SerializedName("application_amount")
    
    private String applicationAmount;
    @SerializedName("allotment_amount")
    
    private String allotmentAmount;
    @SerializedName("allotment_quantity")
    
    private String allotmentQuantity;
    @SerializedName("forfeit_amount")
    
    private String forfeitAmount;
    @SerializedName("net_refund_amount")
    
    private String netRefundAmount;

    public String getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(String applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public String getAllotmentAmount() {
        return allotmentAmount;
    }

    public void setAllotmentAmount(String allotmentAmount) {
        this.allotmentAmount = allotmentAmount;
    }

    public String getAllotmentQuantity() {
        return allotmentQuantity;
    }

    public void setAllotmentQuantity(String allotmentQuantity) {
        this.allotmentQuantity = allotmentQuantity;
    }

    public String getForfeitAmount() {
        return forfeitAmount;
    }

    public void setForfeitAmount(String forfeitAmount) {
        this.forfeitAmount = forfeitAmount;
    }

    public String getNetRefundAmount() {
        return netRefundAmount;
    }

    public void setNetRefundAmount(String netRefundAmount) {
        this.netRefundAmount = netRefundAmount;
    }




}