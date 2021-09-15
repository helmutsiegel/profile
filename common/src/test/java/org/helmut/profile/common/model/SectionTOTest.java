package org.helmut.profile.common.model;

import org.helmut.profile.common.ValidityTester;
import org.helmut.profile.common.validation.groups.Existing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SectionTOTest extends ValidityTester {

    @Test
    @DisplayName("Validate empty SectionTO default group, should return two violations")
    public void testValidity1() {
        Set<ConstraintViolation<SectionTO>> violations = validator.validate(new SectionTO());
        assertEquals(violations.size(), 2);
    }

    @Test
    @DisplayName("Validate empty SectionTO Existing group, should return one violations")
    public void testValidity2() {
        Set<ConstraintViolation<SectionTO>> violations = validator.validate(new SectionTO(), Existing.class);
        assertEquals(violations.size(), 1);
    }

    @Test
    @DisplayName("Validate empty SectionTO all groups, should return three violations")
    public void testValidity3() {
        Set<ConstraintViolation<SectionTO>> violations = validator.validate(new SectionTO(), Default.class, Existing.class);
        assertEquals(violations.size(), 3);
    }

    @Test
    @DisplayName("Valid SectionTO all groups, should return no violations")
    public void testValidity4() {
        SectionTO sectionTO = new SectionTO();
        sectionTO.setId(1L);
        sectionTO.setTitle("title");
        sectionTO.setDescription("Description Description Description Description Description");
        Set<ConstraintViolation<SectionTO>> violations = validator.validate(sectionTO, Default.class, Existing.class);
        assertEquals(violations.size(), 0);
    }

    @Test
    @DisplayName("Valid SectionTO Existing group, should return no violations")
    public void testValidity5() {
        SectionTO sectionTO = new SectionTO();
        sectionTO.setId(1L);
        Set<ConstraintViolation<SectionTO>> violations = validator.validate(sectionTO, Existing.class);
        assertEquals(violations.size(), 0);
    }

    @Test
    @DisplayName("Valid SectionTO Default group, should return no violations")
    public void testValidity6() {
        SectionTO sectionTO = new SectionTO();
        sectionTO.setTitle("title");
        sectionTO.setDescription("Description Description Description Description Description");
        Set<ConstraintViolation<SectionTO>> violations = validator.validate(sectionTO, Default.class);
        assertEquals(violations.size(), 0);
    }
}