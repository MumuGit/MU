package com.mu.example.myapplication.core.permission;

/**
 * Created by mu on 2018/2/12.
 */

public class PermissionData {
    private String explain;
    private int requestCode;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String[] getUnauthorizedPermission() {
        return unauthorizedPermission;
    }

    public void setUnauthorizedPermission(String[] unauthorizedPermission) {
        this.unauthorizedPermission = unauthorizedPermission;
    }

    private String[] unauthorizedPermission;


}
