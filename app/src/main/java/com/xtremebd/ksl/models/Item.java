package com.xtremebd.ksl.models;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;
import com.xtremebd.ksl.R;

import java.text.DecimalFormat;

/**
 * Created by Mashnoor on 8/10/17.
 */

public class Item {

    //For Price Alert
    @SerializedName("high_value")
    private String highPrice;
    @SerializedName("low_value")
    private String lowPrice;
    @SerializedName("last_value_for_notification")
    private String lastValueForNotification;

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

    //For Top Gainer Loser
    @SerializedName("high")
    private String high;
    @SerializedName("low")
    private String low;

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getAmountTradedInBdt() {
        return amountTradedInBdt;
    }

    public String getOpenprice() {
        return openprice;
    }


    public String getDaysrange() {
        return daysrange;
    }


    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTotaltrade() {
        return totaltrade;
    }


    public String getAuthorizedcapital() {
        return authorizedcapital;
    }


    public String getPaidupvalue() {
        return paidupvalue;
    }


    public String getFacevalue() {
        return facevalue;
    }


    public String getNoofsecurities() {
        return noofsecurities;
    }


    public String getWeekrange() {
        return weekrange;
    }

    public String getMarketlot() {
        return marketlot;
    }


    public String getRightissue() {
        return rightissue;
    }


    public String getYearend() {
        return yearend;
    }


    public String getReserveandsurplus() {
        return reserveandsurplus;
    }


    public String getBonousissue() {
        return bonousissue;
    }


    public String getChangepercentage() {
        return changepercentage;
    }


    public String getLastagm() {
        return lastagm;
    }


    public String getPERatioBasic() {
        return pERatioBasic;
    }


    public String getPERatioDiluted() {
        return pERatioDiluted;
    }


    public String getMarketcatagory() {
        return marketcatagory;
    }


    public String getSpSponsorDirector() {
        return spSponsorDirector;
    }


    public String getSpGovt() {
        return spGovt;
    }


    public String getSpInstitute() {
        return spInstitute;
    }


    public String getSpForeign() {
        return spForeign;
    }


    public String getSpPublic() {
        return spPublic;
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

        DecimalFormat df = new DecimalFormat("0.00");
        return String.valueOf(df.format((ltp - buyPrice) * noStock));
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
           if(getChangepercentage().contains("-"))
           {
               return Color.RED;
           }
           else
           {
               return Color.GREEN;
           }

        } catch (Exception e) {
            return Color.RED;
        }
    }

    public int getNetProfitColor() {
        if (Double.parseDouble(getNetProfit()) < 0)
            return Color.RED;
        return Color.GREEN;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public int getStatusIcon() {
        double highVal = Double.parseDouble(getHighPrice());
        double lowVal = Double.parseDouble(getLowPrice());
        double ltpVal = Double.parseDouble(getLtp());
        if (ltpVal == lowVal || ltpVal == highVal) {
            return R.drawable.stable_arrow;
        } else if (ltpVal > lowVal && ltpVal < highVal) {
            return R.drawable.stable_arrow;
        } else if (ltpVal > highVal && ltpVal > lowVal) {
            return R.drawable.up_arrow;
        } else {
            return R.drawable.down_arrow;
        }
    }

    public String getLastValueForNotification() {
        return lastValueForNotification;
    }

    public void setLastValueForNotification(String lastValueForNotification) {
        this.lastValueForNotification = lastValueForNotification;
    }
}
