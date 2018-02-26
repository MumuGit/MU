package com.mu.example.myapplication.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by mu on 2018/1/16.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private long id;
    private String user_id;
    private String password;
    private String nickname;


    /**
     * 姓
     *
     */
    private String lastname;
    /**
     * 名
     */
    private String firstname;
    private String level;
    private String code_ssn;
    private String url_image_id;
    private String gender;
    private String dob;
    private String avatar;
    private String mobile;
    private String email;
    private String wechat_union_id;
    private String address_id;
    private String bank_name;
    private String bank_account;
    private String time_create;
    private String time_delete;
    private String time_edit;
    private long last_login_timestamp;
    private String last_login_ip;
    private String operator_id;
    private String status;

    @Generated(hash = 1655828481)
    public User(long id, String user_id, String password, String nickname,
            String lastname, String firstname, String level, String code_ssn,
            String url_image_id, String gender, String dob, String avatar,
            String mobile, String email, String wechat_union_id, String address_id,
            String bank_name, String bank_account, String time_create,
            String time_delete, String time_edit, long last_login_timestamp,
            String last_login_ip, String operator_id, String status) {
        this.id = id;
        this.user_id = user_id;
        this.password = password;
        this.nickname = nickname;
        this.lastname = lastname;
        this.firstname = firstname;
        this.level = level;
        this.code_ssn = code_ssn;
        this.url_image_id = url_image_id;
        this.gender = gender;
        this.dob = dob;
        this.avatar = avatar;
        this.mobile = mobile;
        this.email = email;
        this.wechat_union_id = wechat_union_id;
        this.address_id = address_id;
        this.bank_name = bank_name;
        this.bank_account = bank_account;
        this.time_create = time_create;
        this.time_delete = time_delete;
        this.time_edit = time_edit;
        this.last_login_timestamp = last_login_timestamp;
        this.last_login_ip = last_login_ip;
        this.operator_id = operator_id;
        this.status = status;
    }

    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUser_id() {
        return this.user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return this.firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLevel() {
        return this.level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getCode_ssn() {
        return this.code_ssn;
    }
    public void setCode_ssn(String code_ssn) {
        this.code_ssn = code_ssn;
    }
    public String getUrl_image_id() {
        return this.url_image_id;
    }
    public void setUrl_image_id(String url_image_id) {
        this.url_image_id = url_image_id;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDob() {
        return this.dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWechat_union_id() {
        return this.wechat_union_id;
    }
    public void setWechat_union_id(String wechat_union_id) {
        this.wechat_union_id = wechat_union_id;
    }
    public String getAddress_id() {
        return this.address_id;
    }
    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }
    public String getBank_name() {
        return this.bank_name;
    }
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
    public String getBank_account() {
        return this.bank_account;
    }
    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }
    public String getTime_create() {
        return this.time_create;
    }
    public void setTime_create(String time_create) {
        this.time_create = time_create;
    }
    public String getTime_delete() {
        return this.time_delete;
    }
    public void setTime_delete(String time_delete) {
        this.time_delete = time_delete;
    }
    public String getTime_edit() {
        return this.time_edit;
    }
    public void setTime_edit(String time_edit) {
        this.time_edit = time_edit;
    }
    public long getLast_login_timestamp() {
        return this.last_login_timestamp;
    }
    public void setLast_login_timestamp(long last_login_timestamp) {
        this.last_login_timestamp = last_login_timestamp;
    }
    public String getLast_login_ip() {
        return this.last_login_ip;
    }
    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }
    public String getOperator_id() {
        return this.operator_id;
    }
    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }



}
