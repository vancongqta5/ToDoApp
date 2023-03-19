package com.example.todoapp.validate;

import java.util.regex.Matcher;

public class Validate {

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
