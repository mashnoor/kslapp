package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nowfel Mashnoor on 9/9/2017.
 */

public class ITSAccount {
    @SerializedName("itsaccountno")
    private String itsAccountNo;


    public ITSAccount(String itsAccountNo, String itsAccountPass) {
        this.itsAccountNo = itsAccountNo;
        this.itsAccountPass = itsAccountPass;
    }

    public String getItsAccountNo() {
        return itsAccountNo;
    }

    public String getItsAccountPass() {
        return itsAccountPass;
    }

    @SerializedName("itsaccountpass")
    private String itsAccountPass;



}
