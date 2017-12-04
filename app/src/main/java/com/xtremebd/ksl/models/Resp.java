
package com.xtremebd.ksl.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resp {

    @SerializedName("_ret_data_table")
    
    private List<RetDataTable> retDataTable = null;
    @SerializedName("_ret_val")
    
    private String retVal;
    @SerializedName("_ret_msg")
    
    private String retMsg;

    public List<RetDataTable> getRetDataTable() {
        return retDataTable;
    }

    public void setRetDataTable(List<RetDataTable> retDataTable) {
        this.retDataTable = retDataTable;
    }

    public String getRetVal() {
        return retVal;
    }

    public void setRetVal(String retVal) {
        this.retVal = retVal;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

}