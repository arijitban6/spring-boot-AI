package com.TechEnd.AI.entity;

/**
 * Created by ariji on 10/28/2017.
 */
public class ValidatorBean {
    private final String appSecret;
    private final String verifyToken;

    public ValidatorBean(String appSecret, String verifyToken) {
        this.appSecret = appSecret;
        this.verifyToken = verifyToken;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getVerifyToken() {
        return verifyToken;
    }
}
