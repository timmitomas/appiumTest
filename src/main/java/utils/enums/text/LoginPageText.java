package utils.enums.text;

public enum LoginPageText {

    LOGIN_TITLE("Вход в Alfa-Test");

    private final String text;

    LoginPageText(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
