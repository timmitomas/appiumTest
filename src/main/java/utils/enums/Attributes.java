package utils.enums;

public enum Attributes {

    PASSWORD("password");

    private final String text;

    Attributes(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
