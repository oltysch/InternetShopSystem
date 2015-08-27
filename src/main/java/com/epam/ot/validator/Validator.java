package com.epam.ot.validator;

public class Validator {
    public static String isLoginValid(String login) {
        if (login.length() > 25) {
            return "error.login.too_long";
        }
        if (!login.matches("[A-Za-z0-9_]+")) {
            return "error.login.incorrect_characters";
        }
        return null;
    }

    public static String isEmailValid(String email) {
        if (email.length() > 60) {
            return "error.email.too_long";
        }
        if (!email.matches("\\w+@\\w+")) {
            return "error.email.incorrect";
        }
        return null;
    }

    public static String isPasswordValid(String password) {
        if (password.length() > 60) {
            return "error.password.too_long";
        }
        if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$")) {
            return "error.password.incorrect";
        }
        return null;
    }
}
