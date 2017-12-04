
package com.xtremebd.ksl.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ledger {

    @SerializedName("transaction_type")

    private String transactionType;
    @SerializedName("scrip_quantity")

    private String scripQuantity;
    @SerializedName("scrip_rate")

    private String scripRate;
    @SerializedName("documnet_number")

    private String documnetNumber;
    @SerializedName("dr_total")

    private String drTotal;
    @SerializedName("brokerage")

    private String brokerage;
    @SerializedName("dr")

    private String dr;
    @SerializedName("balance")

    private String balance;
    @SerializedName("cr")

    private String cr;
    @SerializedName("documnet_date")

    private String documnetDate;
    @SerializedName("explanation")

    private String explanation;
    @SerializedName("cr_total")

    private String crTotal;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getScripQuantity() {
        return scripQuantity;
    }

    public void setScripQuantity(String scripQuantity) {
        this.scripQuantity = scripQuantity;
    }

    public String getScripRate() {
        return scripRate;
    }

    public void setScripRate(String scripRate) {
        this.scripRate = scripRate;
    }

    public String getDocumnetNumber() {
        return documnetNumber;
    }

    public void setDocumnetNumber(String documnetNumber) {
        this.documnetNumber = documnetNumber;
    }

    public String getDrTotal() {
        return drTotal;
    }

    public void setDrTotal(String drTotal) {
        this.drTotal = drTotal;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getDocumnetDate() {
        return documnetDate;
    }

    public void setDocumnetDate(String documnetDate) {
        this.documnetDate = documnetDate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCrTotal() {
        return crTotal;
    }

    public void setCrTotal(String crTotal) {
        this.crTotal = crTotal;
    }

}