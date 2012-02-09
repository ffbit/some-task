package com.ffbit.yandex.report.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private final Pattern pattern;
    
    public EmailValidator() {
        pattern = Pattern.compile("\\A[\\w+\\-.]+@[a-z\\d\\-.]+\\.[a-z]+\\z",
                Pattern.CASE_INSENSITIVE);
    }
    
    public boolean validate(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
}
