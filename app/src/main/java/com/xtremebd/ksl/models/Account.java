package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 8/29/17.
 */

public class Account {
    @SerializedName("username")
    private String userName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("mobile")
    private String mobile;

    public Account(String userName, String email, String password, String mobile) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
    }


    public String getPassword() {
        return password;
    }



}
