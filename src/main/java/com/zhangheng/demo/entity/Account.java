package com.zhangheng.demo.entity;

import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author zhangheng
 * @since 2022-03-24 14:14:25
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 531317742481606313L;
    /**
     * 账号
     */
    private Integer account_id;
    /**
     * 用户名
     */
    private String account_name;
    /**
     * 密码
     */
    private String account_password;

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    private String user_level;


//    public Integer getAccountId() {
//        return account_id;
//    }
//
//    public void setAccountId(Integer accountId) {
//        this.account_id = accountId;
//    }
//
//    public String getAccountName() {
//        return account_name;
//    }
//
//    public void setAccountName(String accountName) {
//        this.account_name = accountName;
//    }
//
//    public String getAccountPassword() {
//        return account_password;
//    }
//
//    public void setAccountPassword(String accountPassword) {
//        this.account_password = accountPassword;
//    }
//
//    public String getUserLevel() {
//        return user_level;
//    }
//
//    public void setUserLevel(String userLevel) {
//        this.user_level = userLevel;
//    }

}

