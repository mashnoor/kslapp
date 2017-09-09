package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nowfel Mashnoor on 9/9/2017.
 */

public class MasterAccount {

    @SerializedName("masterid")
    private String masterId;

    @SerializedName("masterpass")
    private String masterPass;

    public MasterAccount(String masterId, String masterPass) {
        this.masterId = masterId;
        this.masterPass = masterPass;
    }

    public MasterAccount() {

    }

    public String getMasterId() {
        return masterId;
    }

    public String getMasterPass() {
        return masterPass;
    }
}
