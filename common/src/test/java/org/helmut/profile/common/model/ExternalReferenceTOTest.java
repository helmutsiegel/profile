package org.helmut.profile.common.model;

import org.helmut.profile.common.ValidityTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link ExternalReferenceTO}
 */
class ExternalReferenceTOTest extends ValidityTester {

    private static final String URL_HTTPS_HOST_PORT = "https://host.com:6262";
    private static final String URL_HTTP_HOST_PORT = "http://host.com:6262";
    private static final String MALFORMED_URL = "malformed";

    @Test
    @DisplayName("Name is null and url is not set, should return two violation")
    public void testValidation1() {
        assertEquals(validator.validate(new ExternalReferenceTO()).size(), 2);
    }

    @Test
    @DisplayName("Name is set, url is not set, should return one violation")
    public void testValidation2() {
        ExternalReferenceTO externalReferenceTO = new ExternalReferenceTO();
        externalReferenceTO.setName("Name");
        assertEquals(validator.validate(externalReferenceTO).size(), 1);
    }

    @Test
    @DisplayName("Name is empty string, should return one violation")
    public void testValidation3() {
        ExternalReferenceTO externalReferenceTO = new ExternalReferenceTO();
        externalReferenceTO.setName("");
        externalReferenceTO.setUrl(URL_HTTPS_HOST_PORT);

        assertEquals(validator.validate(externalReferenceTO).size(), 1);
    }

    @Test
    @DisplayName("Malformed url, should return one violation")
    public void testValidation4() {
        ExternalReferenceTO externalReferenceTO = new ExternalReferenceTO();
        externalReferenceTO.setName("Name");
        externalReferenceTO.setUrl(MALFORMED_URL);

        assertEquals(validator.validate(externalReferenceTO).size(), 1);
    }

    @Test
    @DisplayName("Invalid protocol, should return one violation")
    public void testValidation5() {
        ExternalReferenceTO externalReferenceTO = new ExternalReferenceTO();
        externalReferenceTO.setName("Name");
        externalReferenceTO.setUrl(URL_HTTP_HOST_PORT);

        assertEquals(validator.validate(externalReferenceTO).size(), 1);
    }
}