package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nowfel Mashnoor on 11/24/2017.
 */

public class PortfolioStatement {


    @SerializedName("portfolio_date")
    private String portfolioDate;

    @Override
    public String toString() {
        return "PortfolioStatement{" +
                "portfolioDate='" + portfolioDate + '\'' +
                ", client_id='" + client_id + '\'' +
                '}';
    }

    @SerializedName("client_id")
    private String client_id;

    public PortfolioStatement(String portfolioDate, String client_id) {
        this.portfolioDate = portfolioDate;
        this.client_id = client_id;

    }

}
