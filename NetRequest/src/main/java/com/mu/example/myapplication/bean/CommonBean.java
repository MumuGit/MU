package com.mu.example.myapplication.bean;

import java.io.Serializable;

/**
 * Created by mu on 2018/1/5.
 */

public class CommonBean<T> implements Serializable {
    public String code;
    public String message;
    public T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CommonBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result.toString() +
                '}';
    }
}
