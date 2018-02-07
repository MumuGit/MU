package com.mu.example.myapplication.model;

/**
 * Created by mu on 2018/2/7.
 */

public class Content {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    private ErrorMessage error;
    private String status;

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
