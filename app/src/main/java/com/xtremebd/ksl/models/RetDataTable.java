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
    
    private String terminalId;
    @SerializedName("account_type_const")
    
    private String accountTypeConst;
    @SerializedName("_ledger_date")
    
    private String ledgerDate;
    @SerializedName("matured_balance")
    
    private Double maturedBalance;
    @SerializedName("immatured_balance")
    
    private double immaturedBalance;
    @SerializedName("ipo_balance")
    
    private double ipoBalance;
    @SerializedName("realized_gain_loss")
    
    private Double realizedGainLoss;
    @SerializedName("bonus_rcvd")
    
    private double bonusRcvd;
    @SerializedName("portfolio_detail")
    
    private List<PortfolioDetail> portfolioDetail = null;
    @SerializedName("total_deposit")
    
    private double totalDeposit;
    @SerializedName("total_withdrawal")
    
    private double totalWithdrawal;
    @SerializedName("transfer_in")
    
    private double transferIn;
    @SerializedName("transfer_out")
    
    private Double transferOut;
    @SerializedName("other_charge")
    
    private Double otherCharge;
    @SerializedName("ipo_information")
    
    private List<IpoInformation> ipoInformation = null;
    @SerializedName("stock_value")
    
    private double stockValue;
    @SerializedName("accumulated_interest")
    
    private double accumulatedInterest;
    @SerializedName("margin_limit")
    
    private double marginLimit;
    @SerializedName("margin_ratio")
    
    private double marginRatio;
    @SerializedName("ledger_balance")
    
    private Double ledgerBalance;
    @SerializedName("net_realized_gain_loss")
    
    private Double netRealizedGainLoss;
    @SerializedName("total_buy_cost")
    
    private double totalBuyCost;
    @SerializedName("market_value")
    
    private double marketValue;
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

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
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

    public double getImmaturedBalance() {
        return immaturedBalance;
    }

    public void setImmaturedBalance(double immaturedBalance) {
        this.immaturedBalance = immaturedBalance;
    }

    public double getIpoBalance() {
        return ipoBalance;
    }

    public void setIpoBalance(double ipoBalance) {
        this.ipoBalance = ipoBalance;
    }

    public Double getRealizedGainLoss() {
        return realizedGainLoss;
    }

    public void setRealizedGainLoss(Double realizedGainLoss) {
        this.realizedGainLoss = realizedGainLoss;
    }

    public double getBonusRcvd() {
        return bonusRcvd;
    }

    public void setBonusRcvd(double bonusRcvd) {
        this.bonusRcvd = bonusRcvd;
    }

    public List<PortfolioDetail> getPortfolioDetail() {
        return portfolioDetail;
    }

    public void setPortfolioDetail(List<PortfolioDetail> portfolioDetail) {
        this.portfolioDetail = portfolioDetail;
    }

    public double getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(double totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public double getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(double totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public double getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(double transferIn) {
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

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }

    public double getAccumulatedInterest() {
        return accumulatedInterest;
    }

    public void setAccumulatedInterest(double accumulatedInterest) {
        this.accumulatedInterest = accumulatedInterest;
    }

    public double getMarginLimit() {
        return marginLimit;
    }

    public void setMarginLimit(double marginLimit) {
        this.marginLimit = marginLimit;
    }

    public double getMarginRatio() {
        return marginRatio;
    }

    public void setMarginRatio(double marginRatio) {
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

    public double getTotalBuyCost() {
        return totalBuyCost;
    }

    public void setTotalBuyCost(double totalBuyCost) {
        this.totalBuyCost = totalBuyCost;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
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