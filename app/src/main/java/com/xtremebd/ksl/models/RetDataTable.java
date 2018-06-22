package com.xtremebd.ksl.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RetDataTable {

    @SerializedName("client_id")
    
    private String clientId;
    @SerializedName("fah_full_name")
    
    private String fahFullName;
    @SerializedName("comm_email")
    
    private String commEmail;
    @SerializedName("bo_id")
    
    private String boId;
    @SerializedName("terminal_id")
    
    private String terminalId;
    @SerializedName("account_type_const")
    
    private String accountTypeConst;
    @SerializedName("_ledger_date")
    
    private String ledgerDate;
    @SerializedName("matured_balance")
    
    private String maturedBalance;
    @SerializedName("immatured_balance")
    
    private String immaturedBalance;
    @SerializedName("ipo_balance")
    
    private String ipoBalance;
    @SerializedName("realized_gain_loss")
    
    private String realizedGainLoss;
    @SerializedName("bonus_rcvd")
    
    private String bonusRcvd;
    @SerializedName("portfolio_detail")
    
    private String portfolioDetail;
    @SerializedName("total_deposit")
    
    private String totalDeposit;
    @SerializedName("total_withdrawal")
    
    private String totalWithdrawal;
    @SerializedName("transfer_in")
    
    private String transferIn;
    @SerializedName("transfer_out")
    
    private String transferOut;
    @SerializedName("other_charge")
    
    private String otherCharge;
    @SerializedName("ipo_information")
    
    private List<IpoInformation> ipoInformation;
    @SerializedName("stock_value")
    
    private String stockValue;
    @SerializedName("accumulated_interest")
    
    private String accumulatedInterest;
    @SerializedName("margin_limit")
    
    private String marginLimit;
    @SerializedName("margin_ratio")
    
    private String marginRatio;
    @SerializedName("ledger_balance")
    
    private String ledgerBalance;
    @SerializedName("net_realized_gain_loss")
    
    private String netRealizedGainLoss;
    @SerializedName("total_buy_cost")
    
    private String totalBuyCost;
    @SerializedName("market_value")
    
    private String marketValue;
    @SerializedName("owners_equity")
    
    private String ownersEquity;
    @SerializedName("current_balance")
    
    private String currentBalance;

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

    public String getCommEmail() {
        return commEmail;
    }

    public void setCommEmail(String commEmail) {
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

    public String getMaturedBalance() {
        return maturedBalance;
    }

    public void setMaturedBalance(String maturedBalance) {
        this.maturedBalance = maturedBalance;
    }

    public String getImmaturedBalance() {
        return immaturedBalance;
    }

    public void setImmaturedBalance(String immaturedBalance) {
        this.immaturedBalance = immaturedBalance;
    }

    public String getIpoBalance() {
        return ipoBalance;
    }

    public void setIpoBalance(String ipoBalance) {
        this.ipoBalance = ipoBalance;
    }

    public String getRealizedGainLoss() {
        return realizedGainLoss;
    }

    public void setRealizedGainLoss(String realizedGainLoss) {
        this.realizedGainLoss = realizedGainLoss;
    }

    public String getBonusRcvd() {
        return bonusRcvd;
    }

    public void setBonusRcvd(String bonusRcvd) {
        this.bonusRcvd = bonusRcvd;
    }

    public String getPortfolioDetail() {
        return portfolioDetail;
    }

    public void setPortfolioDetail(String portfolioDetail) {
        this.portfolioDetail = portfolioDetail;
    }

    public String getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(String totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public String getTotalWithdrawal() {
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(String totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public String getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(String transferIn) {
        this.transferIn = transferIn;
    }

    public String getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(String transferOut) {
        this.transferOut = transferOut;
    }

    public String getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(String otherCharge) {
        this.otherCharge = otherCharge;
    }

    public List<IpoInformation> getIpoInformation() {
        return ipoInformation;
    }

    public void setIpoInformation(List<IpoInformation> ipoInformation) {
        this.ipoInformation = ipoInformation;
    }

    public String getStockValue() {
        return stockValue;
    }

    public void setStockValue(String stockValue) {
        this.stockValue = stockValue;
    }

    public String getAccumulatedInterest() {
        return accumulatedInterest;
    }

    public void setAccumulatedInterest(String accumulatedInterest) {
        this.accumulatedInterest = accumulatedInterest;
    }

    public String getMarginLimit() {
        return marginLimit;
    }

    public void setMarginLimit(String marginLimit) {
        this.marginLimit = marginLimit;
    }

    public String getMarginRatio() {
        return marginRatio;
    }

    public void setMarginRatio(String marginRatio) {
        this.marginRatio = marginRatio;
    }

    public String getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(String ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public String getNetRealizedGainLoss() {
        return netRealizedGainLoss;
    }

    public void setNetRealizedGainLoss(String netRealizedGainLoss) {
        this.netRealizedGainLoss = netRealizedGainLoss;
    }

    public String getTotalBuyCost() {
        return totalBuyCost;
    }

    public void setTotalBuyCost(String totalBuyCost) {
        this.totalBuyCost = totalBuyCost;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getOwnersEquity() {
        return ownersEquity;
    }

    public void setOwnersEquity(String ownersEquity) {
        this.ownersEquity = ownersEquity;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

}