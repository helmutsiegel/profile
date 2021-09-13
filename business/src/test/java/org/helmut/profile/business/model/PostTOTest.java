package org.helmut.profile.business.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostTOTest {

    private static Validator validator;
    private static ValidatorFactory vf;

    @BeforeAll
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    @DisplayName("Validate empty object")
    public void testValidation1() {
        Set<ConstraintViolation<PostTO>> violations = validator.validate(new PostTO());
        assertEquals(violations.size(), 4);
    }

    @Test
    @DisplayName("Just id is set")
    public void testValidation2() {
        PostTO postTO = new PostTO();
        postTO.setId(1L);
        Set<ConstraintViolation<PostTO>> violations = validator.validate(postTO);
        assertEquals(violations.size(), 3);
    }

    @Test
    @DisplayName("The object is valid")
    public void testValidation3() {
        PostTO postTO = new PostTO();
        postTO.setId(1L);
        postTO.setTitle("Title");
        postTO.setContent("Content...........................................");
        postTO.setTags("tags");
        Set<ConstraintViolation<PostTO>> violations = validator.validate(postTO);
        assertEquals(violations.size(), 0);
    }
}