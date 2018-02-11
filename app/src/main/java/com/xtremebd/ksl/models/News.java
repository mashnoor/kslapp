package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mashnoor on 8/15/17.
 */

public class News {
    @SerializedName("body")
    private String body;

    @SerializedName("title")
    private String title;

    @SerializedName("date")
    private String date;

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
