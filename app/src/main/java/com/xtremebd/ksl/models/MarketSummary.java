package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class MarketSummary {
    @SerializedName("issues_traded")
    private String issuesTraded;
    @SerializedName("issues_advanced")
    private String issuesAdvanced;
    @SerializedName("issues_unchanged")
    private String issuesUnchanged;
    @SerializedName("volume")
    private String volume;
    @SerializedName("issues_declined")
    private String issuesDeclined;
    @SerializedName("issued_capital")
    private String issuedCapital;
    @SerializedName("closing_market_capitalization")
    private String closingMarketCapitalization;
    @SerializedName("contract_number")
    private String contractNumber;
    @SerializedName("value_in_taka")
    private String valueInTaka;

    public String getIssuesTraded() {
        return issuesTraded;
    }

    public String getIssuesAdvanced() {
        return issuesAdvanced;
    }

    public String getIssuesUnchanged() {
        return issuesUnchanged;
    }

    public String getVolume() {
        return volume;
    }

    public String getIssuesDeclined() {
        return issuesDeclined;
    }

    public String getIssuedCapital() {
        return issuedCapital;
    }

    public String getClosingMarketCapitalization() {
        return closingMarketCapitalization;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getValueInTaka() {
        return valueInTaka;
    }
}
