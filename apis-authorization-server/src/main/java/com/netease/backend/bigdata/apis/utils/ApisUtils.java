package com.netease.backend.bigdata.apis.utils;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 1/4/14
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public abstract class ApisUtils {

    public static String generateRandomId(){
        return UUID.randomUUID().toString();
    }

    private ApisUtils(){}
}
