package com.nimap.app.util;

public class Utility {

    public static boolean isEmptyNullStr(String val){
        return null==val || val.trim().isEmpty();
    }

    public static boolean isEmptyNullDouble(double val){
        return val <= 0.00;
    }

    public static boolean isEmptyNullInt(int val){
        return val <= 0;
    }
}
