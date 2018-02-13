package com.mu.example.myapplication.model;

/**
 * Created by mu on 2018/1/16.
 */

public class SMSContent {
    public int sms_id;
    private ErrorMessage error;

    public int getSms_id() {
        return sms_id;
    }

    public void setSms_id(int sms_id) {
        this.sms_id = sms_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(long time_expire) {
        this.time_expire = time_expire;
    }

    public String message;
    public long time_expire;
}
