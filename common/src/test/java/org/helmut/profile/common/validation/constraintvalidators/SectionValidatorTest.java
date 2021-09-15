package org.helmut.profile.common.validation.constraintvalidators;

import jersey.repackaged.com.google.common.collect.Sets;
import org.helmut.profile.common.model.SectionTO;
import org.helmut.profile.common.validation.CRUD;
import org.helmut.profile.common.validation.constraints.Section;
import org.helmut.profile.common.validation.groups.Existing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.annotation.Annotation;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SectionValidatorTest {

    @InjectMocks
    private SectionValidator sectionValidator;

    @Mock
    private Validator validator;

    @Test
    @DisplayName("Validate existing correct section")
    void isValid1() {
        SectionTO sectionTO = new SectionTO();
        doReturn(Collections.emptySet()).when(validator).validate(sectionTO, Existing.class, Default.class);
        sectionValidator.initialize(getSectionAnnotation(CRUD.UPDATE));

        assertTrue(sectionValidator.isValid(sectionTO, mock(ConstraintValidatorContext.class)));
    }

    @Test
    @DisplayName("Validate new correct section")
    void isValid2() {
        SectionTO sectionTO = new SectionTO();
        doReturn(Collections.emptySet()).when(validator).validate(sectionTO);
        sectionValidator.initialize(getSectionAnnotation(CRUD.CREATE));

        assertTrue(sectionValidator.isValid(sectionTO, mock(ConstraintValidatorContext.class)));
    }

    @Test
    @DisplayName("Validate existing incorrect section")
    void isValid3() {
        SectionTO sectionTO = new SectionTO();
        doReturn(Sets.newHashSet(mock(ConstraintViolation.class))).when(validator).validate(sectionTO, Existing.class, Default.class);
        sectionValidator.initialize(getSectionAnnotation(CRUD.UPDATE));

        assertFalse(sectionValidator.isValid(sectionTO, mock(ConstraintValidatorContext.class)));
    }

    @Test
    @DisplayName("Validate new incorrect section")
    void isValid4() {
        SectionTO sectionTO = new SectionTO();
        doReturn(Sets.newHashSet(mock(ConstraintViolation.class))).when(validator).validate(sectionTO);
        sectionValidator.initialize(getSectionAnnotation(CRUD.CREATE));

        assertFalse(sectionValidator.isValid(sectionTO, mock(ConstraintValidatorContext.class)));
    }

    private Section getSectionAnnotation(CRUD method) {
        return new Section() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String message() {
                return null;
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public CRUD method() {
                return method;
            }
        };
    }
}