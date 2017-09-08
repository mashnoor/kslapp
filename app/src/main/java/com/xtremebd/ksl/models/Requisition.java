package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 8/30/17.
 */

public class Requisition {

    @SerializedName("itsaccno")
    private String itsAccNo;
    @SerializedName("amount")
    private String amount;
    @SerializedName("reqdate")
    private String reqDate;

    public Requisition(String itsAccNo, String amount, String reqDate) {
        this.itsAccNo = itsAccNo;
        this.amount = amount;
        this.reqDate = reqDate;
    }
}
