package com.bootdo.common.utils;

/**
 * @author bootdo
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
    public static boolean isIntegerForDouble(Double obj){
        double eps=1e-10;
        return obj-Math.floor(obj)<eps;
    }
}
