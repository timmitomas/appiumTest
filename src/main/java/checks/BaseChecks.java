package checks;

import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseChecks {

    public SoftAssertions softly() {
        return new SoftAssertions();
    }

    public void verifyValueEqualsTo(String elementWithText, String expectedText, String checkDescription) {
        assertThat(elementWithText)
                .as(checkDescription)
                .isEqualTo(expectedText);
    }

    public void verifyValueEqualsTo(SoftAssertions softly, String elementWithText, String expectedText, String checkDescription) {
        softly.assertThat(elementWithText)
                .as(checkDescription)
                .isEqualTo(expectedText);
    }

    public void verifyValueEqualsTo(SoftAssertions softly, int actualValue, int expectedValue, String checkDescription) {
        softly.assertThat(actualValue)
                .as(checkDescription)
                .isEqualTo(expectedValue);
    }

    public void verifyThatIsTrue(boolean elementState, String checkDescription) {
        assertThat(elementState)
                .as(checkDescription)
                .isTrue();
    }

    public void verifyThatIsTrue(SoftAssertions softly, boolean elementState, String checkDescription) {
        softly.assertThat(elementState)
                .as(checkDescription)
                .isTrue();
    }

    public void verifyThatIsFalse(boolean elementState, String checkDescription) {
        assertThat(elementState)
                .as(checkDescription)
                .isFalse();
    }
}
