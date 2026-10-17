package utils.enums.messages;

public enum LoginMessages {
    INVALID_DATA("Введены неверные данные"),
    EXCEPT_VALUE("ExceptValue"),
    INVALID_VALUE("InvalidValue"),
    ;


    private final String text;

    LoginMessages(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}