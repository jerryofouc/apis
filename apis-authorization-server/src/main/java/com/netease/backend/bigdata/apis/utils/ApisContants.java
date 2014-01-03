package com.netease.backend.bigdata.apis.utils;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 1/2/14
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class ApisContants {
    public static final String LOGIN_USER = "LOGIN_USER";
    public static final String MANAMENT_PREFIX = "/manage";
    public static final String LOGIN_URL = MANAMENT_PREFIX + "/login";
    public static final String LOGOUT_URL =  MANAMENT_PREFIX + "/logout";

    private ApisContants(){}
}
