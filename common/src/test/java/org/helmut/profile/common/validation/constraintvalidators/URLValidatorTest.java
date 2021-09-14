package org.helmut.profile.common.validation.constraintvalidators;

import org.helmut.profile.common.ValidityTester;
import org.helmut.profile.common.validation.constraints.URL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class URLValidatorTest extends ValidityTester {
    @Test
    @DisplayName("Invalid host, should return one violation")
    public void test1() {
        TOForURLValidator toForURLValidator = new TOForURLValidator();
        toForURLValidator.url = "https://test.com:80";

        assertEquals(validator.validate(toForURLValidator).size(), 1);
    }

    @Test
    @DisplayName("Invalid host, should return one violation")
    public void test2() {
        TOForURLValidator toForURLValidator = new TOForURLValidator();
        toForURLValidator.url = "https://test.com:80";

        assertEquals(validator.validate(toForURLValidator).size(), 1);
    }

    @Test
    @DisplayName("Invalid port, should return one violation")
    public void test3() {
        TOForURLValidator toForURLValidator = new TOForURLValidator();
        toForURLValidator.url = "https://host.com:8080";

        assertEquals(validator.validate(toForURLValidator).size(), 1);
    }

    @Test
    @DisplayName("Valid URL, no violation expected")
    public void test4() {
        TOForURLValidator toForURLValidator = new TOForURLValidator();
        toForURLValidator.url = "https://host.com:80";

        assertEquals(validator.validate(toForURLValidator).size(), 0);
    }
}
class TOForURLValidator {
    @URL(host = "host.com", port = 80)
    String url;
}
