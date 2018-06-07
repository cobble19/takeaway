package com.github.wxpay.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Administrator
 */
public class WxCardSign {
    private static final Logger logger = LoggerFactory.getLogger(WxCardSign.class);

    private ArrayList<String> paramToSign;

    public WxCardSign() {
        paramToSign = new ArrayList<String>();
    }

    public void addData(String value) {
        paramToSign.add(value);
    }

    public void addData(Integer value) {
        paramToSign.add(value.toString());
    }

    public String getSignature() {
        String ret = "";
        Collections.sort(paramToSign);
        StringBuilder stringToSign = new StringBuilder();
        for (String str : paramToSign) {
            stringToSign.append(str);
        }

        try {
            MessageDigest hasher = MessageDigest.getInstance("SHA-1");
            byte[] digest = hasher.digest(stringToSign.toString().getBytes());
            ret = ByteToHexString(digest);
            logger.info("stringToSign:{}, ret: {}", stringToSign, ret);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Get digest exception: ", e);
        }

        return ret;
    }

    public String ByteToHexString(byte[] data) {
        StringBuilder str = new StringBuilder();
        for (byte b : data) {
            String hv = Integer.toHexString(b & 0xFF);
            if (hv.length() < 2)
                str.append("0");
            str.append(hv);
        }
        return str.toString();
    }
}
