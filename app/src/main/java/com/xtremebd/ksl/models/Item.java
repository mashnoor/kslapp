package com.xtremebd.ksl.models;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

/**
 * Created by Mashnoor on 8/10/17.
 */

public class Item {


    @SerializedName("high")
    private String highPrice;
    @SerializedName("low")
    private String lowPrice;

    //For Portfolio
    private String buyPrice;
    private String noOfStock;


    @SerializedName("closeprice")
    private String closeprice;

    @SerializedName("ycp")
    private String ycp;

    @SerializedName("openprice")
    private String openprice;

    @SerializedName("adjustopenprice")
    private String adjustopenprice;

    @SerializedName("daysrange")
    private String daysrange;

    @SerializedName("volume")
    private String volume;

    @SerializedName("totaltrade")
    private String totaltrade;

    @SerializedName("marketcapital")
    private String marketcapital;

    @SerializedName("authorizedcapital")
    private String authorizedcapital;

    @SerializedName("paidupvalue")
    private String paidupvalue;

    @SerializedName("facevalue")
    private String facevalue;

    @SerializedName("noofsecurities")
    private String noofsecurities;

    @SerializedName("weekrange")
    private String weekrange;

    @SerializedName("marketlot")
    private String marketlot;

    @SerializedName("segment")
    private String segment;

    @SerializedName("rightissue")
    private String rightissue;

    @SerializedName("yearend")
    private String yearend;

    @SerializedName("reserveandsurplus")
    private String reserveandsurplus;

    @SerializedName("bonousissue")
    private String bonousissue;

    @SerializedName("companyname")
    private String companyname;

    @SerializedName("ltp")
    private String ltp;

    @SerializedName("changeval")
    private String changeval;

    @SerializedName("changepercentage")
    private String changepercentage;

    @SerializedName("lastagm")
    private String lastagm;

    @SerializedName("p_e_ratio_basic")
    private String pERatioBasic;

    @SerializedName("p_e_ratio_diluted")
    private String pERatioDiluted;

    @SerializedName("marketcatagory")
    private String marketcatagory;

    @SerializedName("fp2013_epscontinueoperation")
    private String fp2013Epscontinueoperation;

    @SerializedName("fp2013_NAV")
    private String fp2013NAV;

    @SerializedName("fp2013_NPATcontinueoperation")
    private String fp2013NPATcontinueoperation;

    @SerializedName("fp2013_NPATextraordinaryincome")
    private String fp2013NPATextraordinaryincome;

    @SerializedName("fp2014_epscontinueoperation")
    private String fp2014Epscontinueoperation;

    @SerializedName("fp2014_NAV")
    private String fp2014NAV;

    @SerializedName("fp2014_NPATextraordinaryincome")
    private String fp2014NPATextraordinaryincome;

    @SerializedName("fp2014_NPATcontinueoperation")
    private String fp2014NPATcontinueoperation;

    @SerializedName("fpcontinue_dividend_2009")
    private String fpcontinueDividend2009;

    @SerializedName("fpcontinue_dividend_2010")
    private String fpcontinueDividend2010;

    @SerializedName("fpcontinue_dividend_2011")
    private String fpcontinueDividend2011;

    @SerializedName("fpcontinue_dividend_2012")
    private String fpcontinueDividend2012;

    @SerializedName("fpcontinue_dividend_2013")
    private String fpcontinueDividend2013;

    @SerializedName("fpcontinue_dividend_2014")
    private String fpcontinueDividend2014;

    @SerializedName("sp_sponsor_director")
    private String spSponsorDirector;

    @SerializedName("sp_govt")
    private String spGovt;

    @SerializedName("sp_institute")
    private String spInstitute;

    @SerializedName("sp_foreign")
    private String spForeign;

    @SerializedName("sp_public")
    private String spPublic;

    @SerializedName("value")
    private String value;

    @SerializedName("item")
    private String item;

    @SerializedName("amounttradedinbdt")
    private String amountTradedInBdt;

    public String getAmountTradedInBdt() {
        return amountTradedInBdt;
    }

    public void setCloseprice(String closeprice) {
        this.closeprice = closeprice;
    }

    public String getYcp() {
        return ycp;
    }

    public void setYcp(String ycp) {
        this.ycp = ycp;
    }

    public String getOpenprice() {
        return openprice;
    }

    public void setOpenprice(String openprice) {
        this.openprice = openprice;
    }

    public String getAdjustopenprice() {
        return adjustopenprice;
    }

    public void setAdjustopenprice(String adjustopenprice) {
        this.adjustopenprice = adjustopenprice;
    }

    public String getDaysrange() {
        return daysrange;
    }

    public void setDaysrange(String daysrange) {
        this.daysrange = daysrange;
    }


    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTotaltrade() {
        return totaltrade;
    }

    public void setTotaltrade(String totaltrade) {
        this.totaltrade = totaltrade;
    }

    public String getMarketcapital() {
        return marketcapital;
    }

    public void setMarketcapital(String marketcapital) {
        this.marketcapital = marketcapital;
    }

    public String getAuthorizedcapital() {
        return authorizedcapital;
    }

    public void setAuthorizedcapital(String authorizedcapital) {
        this.authorizedcapital = authorizedcapital;
    }

    public String getPaidupvalue() {
        return paidupvalue;
    }

    public void setPaidupvalue(String paidupvalue) {
        this.paidupvalue = paidupvalue;
    }

    public String getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(String facevalue) {
        this.facevalue = facevalue;
    }

    public String getNoofsecurities() {
        return noofsecurities;
    }

    public void setNoofsecurities(String noofsecurities) {
        this.noofsecurities = noofsecurities;
    }

    public String getWeekrange() {
        return weekrange;
    }

    public void setWeekrange(String weekrange) {
        this.weekrange = weekrange;
    }

    public String getMarketlot() {
        return marketlot;
    }

    public void setMarketlot(String marketlot) {
        this.marketlot = marketlot;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getRightissue() {
        return rightissue;
    }

    public void setRightissue(String rightissue) {
        this.rightissue = rightissue;
    }

    public String getYearend() {
        return yearend;
    }

    public void setYearend(String yearend) {
        this.yearend = yearend;
    }

    public String getReserveandsurplus() {
        return reserveandsurplus;
    }

    public void setReserveandsurplus(String reserveandsurplus) {
        this.reserveandsurplus = reserveandsurplus;
    }

    public String getBonousissue() {
        return bonousissue;
    }

    public void setBonousissue(String bonousissue) {
        this.bonousissue = bonousissue;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }


    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    public String getChangeval() {
        return changeval;
    }

    public void setChangeval(String changeval) {
        this.changeval = changeval;
    }

    public String getChangepercentage() {
        return changepercentage;
    }

    public void setChangepercentage(String changepercentage) {
        this.changepercentage = changepercentage;
    }

    public String getLastagm() {
        return lastagm;
    }

    public void setLastagm(String lastagm) {
        this.lastagm = lastagm;
    }

    public String getPERatioBasic() {
        return pERatioBasic;
    }

    public void setPERatioBasic(String pERatioBasic) {
        this.pERatioBasic = pERatioBasic;
    }

    public String getPERatioDiluted() {
        return pERatioDiluted;
    }

    public void setPERatioDiluted(String pERatioDiluted) {
        this.pERatioDiluted = pERatioDiluted;
    }

    public String getMarketcatagory() {
        return marketcatagory;
    }

    public void setMarketcatagory(String marketcatagory) {
        this.marketcatagory = marketcatagory;
    }

    public String getFp2013Epscontinueoperation() {
        return fp2013Epscontinueoperation;
    }

    public void setFp2013Epscontinueoperation(String fp2013Epscontinueoperation) {
        this.fp2013Epscontinueoperation = fp2013Epscontinueoperation;
    }

    public String getFp2013NAV() {
        return fp2013NAV;
    }

    public void setFp2013NAV(String fp2013NAV) {
        this.fp2013NAV = fp2013NAV;
    }

    public String getFp2013NPATcontinueoperation() {
        return fp2013NPATcontinueoperation;
    }

    public void setFp2013NPATcontinueoperation(String fp2013NPATcontinueoperation) {
        this.fp2013NPATcontinueoperation = fp2013NPATcontinueoperation;
    }

    public String getFp2013NPATextraordinaryincome() {
        return fp2013NPATextraordinaryincome;
    }

    public void setFp2013NPATextraordinaryincome(String fp2013NPATextraordinaryincome) {
        this.fp2013NPATextraordinaryincome = fp2013NPATextraordinaryincome;
    }

    public String getFp2014Epscontinueoperation() {
        return fp2014Epscontinueoperation;
    }

    public void setFp2014Epscontinueoperation(String fp2014Epscontinueoperation) {
        this.fp2014Epscontinueoperation = fp2014Epscontinueoperation;
    }

    public String getFp2014NAV() {
        return fp2014NAV;
    }

    public void setFp2014NAV(String fp2014NAV) {
        this.fp2014NAV = fp2014NAV;
    }

    public String getFp2014NPATextraordinaryincome() {
        return fp2014NPATextraordinaryincome;
    }

    public void setFp2014NPATextraordinaryincome(String fp2014NPATextraordinaryincome) {
        this.fp2014NPATextraordinaryincome = fp2014NPATextraordinaryincome;
    }

    public String getFp2014NPATcontinueoperation() {
        return fp2014NPATcontinueoperation;
    }

    public void setFp2014NPATcontinueoperation(String fp2014NPATcontinueoperation) {
        this.fp2014NPATcontinueoperation = fp2014NPATcontinueoperation;
    }

    public String getFpcontinueDividend2009() {
        return fpcontinueDividend2009;
    }

    public void setFpcontinueDividend2009(String fpcontinueDividend2009) {
        this.fpcontinueDividend2009 = fpcontinueDividend2009;
    }

    public String getFpcontinueDividend2010() {
        return fpcontinueDividend2010;
    }

    public void setFpcontinueDividend2010(String fpcontinueDividend2010) {
        this.fpcontinueDividend2010 = fpcontinueDividend2010;
    }

    public String getFpcontinueDividend2011() {
        return fpcontinueDividend2011;
    }

    public void setFpcontinueDividend2011(String fpcontinueDividend2011) {
        this.fpcontinueDividend2011 = fpcontinueDividend2011;
    }

    public String getFpcontinueDividend2012() {
        return fpcontinueDividend2012;
    }

    public void setFpcontinueDividend2012(String fpcontinueDividend2012) {
        this.fpcontinueDividend2012 = fpcontinueDividend2012;
    }

    public String getFpcontinueDividend2013() {
        return fpcontinueDividend2013;
    }

    public void setFpcontinueDividend2013(String fpcontinueDividend2013) {
        this.fpcontinueDividend2013 = fpcontinueDividend2013;
    }

    public String getFpcontinueDividend2014() {
        return fpcontinueDividend2014;
    }

    public void setFpcontinueDividend2014(String fpcontinueDividend2014) {
        this.fpcontinueDividend2014 = fpcontinueDividend2014;
    }

    public String getSpSponsorDirector() {
        return spSponsorDirector;
    }

    public void setSpSponsorDirector(String spSponsorDirector) {
        this.spSponsorDirector = spSponsorDirector;
    }

    public String getSpGovt() {
        return spGovt;
    }

    public void setSpGovt(String spGovt) {
        this.spGovt = spGovt;
    }

    public String getSpInstitute() {
        return spInstitute;
    }

    public void setSpInstitute(String spInstitute) {
        this.spInstitute = spInstitute;
    }

    public String getSpForeign() {
        return spForeign;
    }

    public void setSpForeign(String spForeign) {
        this.spForeign = spForeign;
    }

    public String getSpPublic() {
        return spPublic;
    }

    public void setSpPublic(String spPublic) {
        this.spPublic = spPublic;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public String getBuyPrice() {
        return buyPrice;
    }

    public String getNoOfStock() {
        return noOfStock;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setNoOfStock(String noOfStock) {
        this.noOfStock = noOfStock;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getNetProfit() {
        if (getLtp().equals("Not Traded Today"))
            return "0";
        if (getBuyPrice().equals("Not Traded Today"))
            return "0";
        double buyPrice = Double.parseDouble(getBuyPrice());
        double noStock = Double.parseDouble(getNoOfStock());
        double ltp = Double.parseDouble(getLtp());

        return String.valueOf((ltp - buyPrice) * noStock);
    }

    public String getNetProfitPercentage() {
        if (getBuyPrice().equals("Not Traded Today") || getLtp().equals("Not Traded Today")) {
            return "0";
        }

        double buyPrice = Double.parseDouble(getBuyPrice());

        double ltp = Double.parseDouble(getLtp());
        double profitRatio = (ltp - buyPrice) / ltp;
        double percentage = profitRatio * 100;
        DecimalFormat df = new DecimalFormat("0.00");
        return String.valueOf(df.format(percentage)).concat("%");
    }

    public String getCloseprice() {
        return closeprice;
    }

    public String getYesterdayClosePrice() {
        return ycp;
    }

    public String getOpenPrice() {
        return openprice;
    }

    public String getRange() {
        return daysrange;
    }


    public String getCapital() {
        return marketcapital;
    }

    public String getValue() {
        return value;
    }


    public boolean isChangeNegetive() {
        float change_float = Float.parseFloat(changeval);
        if (change_float < 0) return true;
        return false;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public String getVolume() {
        return volume;
    }

    public String getChange() {
        return changeval;
    }

    public String getLtp() {
        return ltp;
    }

    public int getColor() {
        try

        {
            double change = Double.parseDouble(getChange());
            if (change < 0) {
                return Color.RED;
            } else {
                return Color.GREEN;
            }

        } catch (Exception e) {
            return Color.RED;
        }
    }
}
