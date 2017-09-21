package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 9/21/17.
 */

public class Notification {

    @SerializedName("title")
    private String title;
    @SerializedName("message")
    private String message;

    @SerializedName("time")
    private String time;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
