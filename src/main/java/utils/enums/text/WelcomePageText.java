package utils.enums.text;

public enum WelcomePageText {

    SUCCESSFUL_LOGIN("Вход в Alfa-Test выполнен");

    private final String text;

    WelcomePageText(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
