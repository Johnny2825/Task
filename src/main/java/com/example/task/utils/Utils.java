package com.example.task.utils;

public class Utils {

    private Utils() {

    }

    public static Long parseLongSafety(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public static Integer parseIntegerSafety(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
