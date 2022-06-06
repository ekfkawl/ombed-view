package com.example.demo.util;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlUtils {
    public static boolean isValidUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}
