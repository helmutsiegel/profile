package org.helmut.profile.common.model;


import org.helmut.profile.common.ValidityTester;
import org.helmut.profile.common.validation.groups.Existing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostTOTest extends ValidityTester {

    @Test
    @DisplayName("Validate empty object creation state")
    public void testValidation1() {
        Set<ConstraintViolation<PostTO>> violations = validator.validate(new PostTO());
        assertEquals(violations.size(), 3);
    }

    @Test
    @DisplayName("Validate empty object existing state")
    public void testValidation2() {
        Set<ConstraintViolation<PostTO>> violations = validator.validate(new PostTO(), Existing.class);
        assertEquals(violations.size(), 4);
    }

    @Test
    @DisplayName("Just id is set")
    public void testValidation3() {
        PostTO postTO = new PostTO();
        postTO.setId(1L);
        Set<ConstraintViolation<PostTO>> violations = validator.validate(postTO);
        assertEquals(violations.size(), 3);
    }

    @Test
    @DisplayName("The object is valid")
    public void testValidation4() {
        PostTO postTO = new PostTO();
        postTO.setId(1L);
        postTO.setTitle("Title");
        postTO.setContent("Content...........................................");
        postTO.setTags("tags");
        Set<ConstraintViolation<PostTO>> violations = validator.validate(postTO);
        assertEquals(violations.size(), 0);
    }
}