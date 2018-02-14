package com.mu.example.myapplication.model;

/**
 * Created by mu on 2018/2/7.
 */

public class LoginSmsResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getContent() {
        return content;
    }

    public void setContent(User content) {
        this.content = content;
    }

    private User content;
}
