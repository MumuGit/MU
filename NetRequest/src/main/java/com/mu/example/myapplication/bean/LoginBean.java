package com.mu.example.myapplication.bean;

import java.io.Serializable;

/**
 * Created by mu on 2018/1/5.
 */

public class LoginBean implements Serializable {
    private String StoreId;
    private String StoreUserId;
    private String EmployeeId;

    public String getTodayDate() {
        return TodayDate;
    }

    public void setTodayDate(String todayDate) {
        TodayDate = todayDate;
    }

    private String TodayDate;
    private String DeviceId;
    private String EmployeeName;
    private String StoreName;
    private String FirstAttend;
    private String LastAttend;
    private String YesterdayState;
}
