package com.xtremebd.ksl.models;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Keep;

import java.util.Date;

/**
 * Created by Mashnoor on 9/20/17.
 */

public class Message {
    @Keep
    @SerializedName("messageText")
    public String messageText;
    @Keep
    @SerializedName("messageUser")
    private String messageUser;
    @Keep
    @SerializedName("messageTime")
    private long messageTime;

    public Message(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    @Keep
    public Message()
    {

    }




    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

}
