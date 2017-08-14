package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 8/10/17.
 */

public class Item {
    @SerializedName("item")
    private String item;
    @SerializedName("volume")
    private String volume;
    @SerializedName("change")
    private String change;
    @SerializedName("ltp")
    private String ltp;

    public boolean isChangeNegetive()
    {
        float change_float = Float.parseFloat(change);
        if(change_float<0) return true;
        return false;
    }

    public String getItem() {
        return item;
    }

    public String getVolume() {
        return volume;
    }

    public String getChange() {
        return change;
    }

    public String getLtp() {
        return ltp;
    }
}
