package utils.constantData;

import java.util.regex.Pattern;

public class LoginConstantData {

    public static final String VALID_USERNAME = "Login";
    public static final String VALID_PASSWORD = "Password";
    public static final String LOGIN_1 = "Login-1";
    public static final String LOGIN_2 = "Login-2";
    public static final String PASS_1 = "Pass-1";
    public static final String PASS_2 = "Pass-2";
    public static final String PASS_3 = "Pass-3";
    public static final int MAX_LOGIN_FIELD_LENGTH = 50;

    public static final int MAX_PASSWORD_FIELD_LENGTH = 50;

    // Латиница, символы [ . , / ' _ -] и пробел
    public static final Pattern ALLOWED_LOGIN_CHARS = Pattern.compile("^[A-Za-z .,/'_\\-]+$");

    private LoginConstantData() {
    }
}
