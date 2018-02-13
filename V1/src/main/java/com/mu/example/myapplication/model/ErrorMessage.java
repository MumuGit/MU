package com.mu.example.myapplication.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mu on 2018/2/13.
 */
@Entity
public class ErrorMessage {
    @Id(autoincrement = true)
    public long id;


    private String message;


    @Generated(hash = 2081173520)
    public ErrorMessage(long id, String message) {
        this.id = id;
        this.message = message;
    }


    @Generated(hash = 1087851058)
    public ErrorMessage() {
    }


    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getMessage() {
        return this.message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


}
