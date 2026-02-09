package com.backend.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Helper {
    private Helper() {}

    public static boolean matchesRegex(String patternStr, String target) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(target);

        return matcher.matches();
    }
}
