package utils.enums;

public enum Platforms {

    ANDROID("Android"),
    IOS("ios");

    private final String text;

    Platforms(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
