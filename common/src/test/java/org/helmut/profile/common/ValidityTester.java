package org.helmut.profile.common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Test classes that want to use {@link Validator}, have to extend this class
 * It creates the validator before all and closes the validator factory at the end
 */
public abstract class ValidityTester {
    protected static Validator validator;
    private static ValidatorFactory vf;

    @BeforeAll
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterAll
    public static void close() {
        vf.close();
    }
}
