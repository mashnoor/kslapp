package com.xtremebd.ksl.models;

/**
 * Created by Mashnoor on 8/10/17.
 */

public class Item {
    private String item;
    private String volume;
    private String change;
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
