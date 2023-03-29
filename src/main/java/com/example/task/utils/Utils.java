package com.example.task.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @author Tarkhov Evgeniy
 */
public class Utils {

    private Utils() {
    }

    public static Optional<Long> parseLongSafety(String str) {
        if (StringUtils.isBlank(str)) {
            return Optional.empty();
        }
        try {
            return Optional.of(Long.valueOf(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


    public static Optional<Integer> parseIntegerSafety(String str) {
        if (StringUtils.isBlank(str)) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.valueOf(str));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
