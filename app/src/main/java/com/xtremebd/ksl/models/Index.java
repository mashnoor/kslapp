package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 8/14/17.
 */

public class Index {



    public String getCse50value() {
        return cse50value;
    }

    public String getCse50change() {
        return cse50change;
    }

    public String getCse30value() {
        return cse30value;
    }

    public String getCse30change() {
        return cse30change;
    }

    public String getCscxvalue() {
        return cscxvalue;
    }

    public String getCscxchange() {
        return cscxchange;
    }

    public String getCaspivalue() {
        return caspivalue;
    }

    public String getCaspichange() {
        return caspichange;
    }

    public String getCsivalue() {
        return csivalue;
    }

    public String getCsichange() {
        return csichange;
    }
    @SerializedName("cse50value")
    private String cse50value;
    @SerializedName("cse50change")
    private String cse50change;
    @SerializedName("cse30value")
    private String cse30value;
    @SerializedName("cse30change")
    private String cse30change;
    @SerializedName("cscxvalue")
    private String cscxvalue;
    @SerializedName("cscxchange")
    private String cscxchange;
    @SerializedName("caspivalue")
    private String caspivalue;
    @SerializedName("caspichange")
    private String caspichange;
    @SerializedName("csivalue")
    private String csivalue;
    @SerializedName("csichange")
    private String csichange;


}
