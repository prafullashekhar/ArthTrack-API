package com.prafull.ArthTrack.common;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean equals(String str1, String str2) {
        if(str1 == null || str2 == null) {
            return false;
        }

        return str1.equals(str2);
    }

    public static boolean equals(RowStatus rowStatus1, RowStatus rowStatus2) {
        if(rowStatus1 == null || rowStatus2 == null) {
            return false;
        }

        return rowStatus1 == rowStatus2;
    }
}
