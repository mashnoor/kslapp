package com.xtremebd.ksl.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RetDataTable {

    @SerializedName("client_id")
    
    private String clientId;
    @SerializedName("fah_full_name")
    
    private String fahFullName;
    @SerializedName("comm_email")
    
    private Object commEmail;
    @SerializedName("bo_id")
    
    private String boId;
    @SerializedName("terminal_id")
    
    private Object terminalId;
    @SerializedName("account_type_const")
    
    private String accountTypeConst;
    @SerializedName("_ledger_date")
    
    private String ledgerDate;
    @SerializedName("matured_balance")
    
    private Double maturedBalance;
    @SerializedName("immatured_balance")
    
    private Integer immaturedBalance;
    @SerializedName("ipo_balance")
    
    private Integer ipoBalance;
    @SerializedName("realized_gain_loss")
    
    private Double realizedGainLoss;
    @SerializedName("bonus_rcvd")
    
    private Integer bonusRcvd;
    @SerializedName("portfolio_detail")
    
    private Object portfolioDetail;
    @SerializedName("total_deposit")
    
    private Integer totalDeposit;
    @SerializedName("total_withdrawal")
    
    private Integer totalWithdrawal;
    @SerializedName("transfer_in")
    
    private Integer transferIn;
    @SerializedName("transfer_out")
    
    private Double transferOut;
    @SerializedName("other_charge")
    
    private Double otherCharge;
    @SerializedName("ipo_information")
    
    private List<IpoInformation> ipoInformation = null;
    @SerializedName("stock_value")
    
    private Integer stockValue;
    @SerializedName("accumulated_interest")
    
    private Integer accumulatedInterest;
    @SerializedName("margin_limit")
    
    private Integer marginLimit;
    @SerializedName("margin_ratio")
    
    private Integer marginRatio;
    @SerializedName("ledger_balance")
    
    private Double ledgerBalance;
    @SerializedName("net_realized_gain_loss")
    
    private Double netRealizedGainLoss;
    @SerializedName("total_buy_cost")
    
    private Integer totalBuyCost;
    @SerializedName("market_value")
    
    private Integer marketValue;
    @SerializedName("owners_equity")
    
    private Double ownersEquity;
    @SerializedName("current_balance")
    
    private Double currentBalance;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFahFullName() {
        return fahFullName;
    }

    public void setFahFullName(String fahFullName) {
        this.fahFullName = fahFullName;
    }

    public Object getCommEmail() {
        return commEmail;
    }

    public void setCommEmail(Object commEmail) {
        this.commEmail = commEmail;
    }

    public String getBoId() {
        return boId;
    }

    public void setBoId(String boId) {
        this.boId = boId;
    }

    public Object getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Object terminalId) {
        this.terminalId = terminalId;
    }

    public String getAccountTypeConst() {
        return accountTypeConst;
    }

    public void setAccountTypeConst(String accountTypeConst) {
        this.accountTypeConst = accountTypeConst;
    }

    public String getLedgerDate() {
        return ledgerDate;
    }

    public void setLedgerDate(String ledgerDate) {
        this.ledgerDate = ledgerDate;
    }

    public Double getMaturedBalance() {
        return maturedBalance;
    }

    public void setMaturedBalance(Double maturedBalance) {
        this.maturedBalance = maturedBalance;
    }

    public Integer getImmaturedBalance() {
        return immaturedBalance;
    }

    public void setImmaturedBalance(Integer immaturedBalance) {
        this.immaturedBalance = immaturedBalance;
    }

    public Integer getIpoBalance() {
        return ipoBalance;
    }

    public void setIpoBalance(Integer ipoBalance) {
        this.ipoBalance = ipoBalance;
    }

    public Double getRealizedGainLoss() {
        return realizedGainLoss;
    }

    public void setRealizedGainLoss(Double realizedGainLoss) {
        this.realizedGainLoss = realizedGainLoss;
    }

    public Integer getBonusRcvd() {
        return bonusRcvd;
    }

    public void setBonusRcvd(Integer bonusRcvd) {
        this.bonusRcvd = bonusRcvd;
    }

    public Object getPortfolioDetail() {
        return portfolioDetail;
    }

    public void setPortfolioDetail(Object portfolioDetail) {
        this.portfolioDetail = portfolioDetail;
    }

    public Integer getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(Integer totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public Integer getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(Integer totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public Integer getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(Integer transferIn) {
        this.transferIn = transferIn;
    }

    public Double getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(Double transferOut) {
        this.transferOut = transferOut;
    }

    public Double getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(Double otherCharge) {
        this.otherCharge = otherCharge;
    }

    public List<IpoInformation> getIpoInformation() {
        return ipoInformation;
    }

    public void setIpoInformation(List<IpoInformation> ipoInformation) {
        this.ipoInformation = ipoInformation;
    }

    public Integer getStockValue() {
        return stockValue;
    }

    public void setStockValue(Integer stockValue) {
        this.stockValue = stockValue;
    }

    public Integer getAccumulatedInterest() {
        return accumulatedInterest;
    }

    public void setAccumulatedInterest(Integer accumulatedInterest) {
        this.accumulatedInterest = accumulatedInterest;
    }

    public Integer getMarginLimit() {
        return marginLimit;
    }

    public void setMarginLimit(Integer marginLimit) {
        this.marginLimit = marginLimit;
    }

    public Integer getMarginRatio() {
        return marginRatio;
    }

    public void setMarginRatio(Integer marginRatio) {
        this.marginRatio = marginRatio;
    }

    public Double getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(Double ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public Double getNetRealizedGainLoss() {
        return netRealizedGainLoss;
    }

    public void setNetRealizedGainLoss(Double netRealizedGainLoss) {
        this.netRealizedGainLoss = netRealizedGainLoss;
    }

    public Integer getTotalBuyCost() {
        return totalBuyCost;
    }

    public void setTotalBuyCost(Integer totalBuyCost) {
        this.totalBuyCost = totalBuyCost;
    }

    public Integer getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Integer marketValue) {
        this.marketValue = marketValue;
    }

    public Double getOwnersEquity() {
        return ownersEquity;
    }

    public void setOwnersEquity(Double ownersEquity) {
        this.ownersEquity = ownersEquity;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

}