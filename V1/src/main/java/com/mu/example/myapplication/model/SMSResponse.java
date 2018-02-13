package com.mu.example.myapplication.model;

/**
 * Created by mu on 2018/2/7.
 */

public class SMSResponse {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public SMSContent getContent() {
        return content;
    }

    public void setContent(SMSContent content) {
        this.content = content;
    }

    private SMSContent content;
    public class ErrorMessage {

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private String message;
    }

}
