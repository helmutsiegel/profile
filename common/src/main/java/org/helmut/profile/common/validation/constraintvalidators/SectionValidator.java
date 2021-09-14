package org.helmut.profile.common.validation.constraintvalidators;

import org.helmut.profile.common.model.SectionTO;
import org.helmut.profile.common.validation.CRUD;
import org.helmut.profile.common.validation.constraints.Section;
import org.helmut.profile.common.validation.groups.Existing;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;

public class SectionValidator implements ConstraintValidator<Section, SectionTO> {

    @Inject
    private Validator validator;

    private CRUD method;

    @Override
    public void initialize(Section section) {
        this.method = section.method();
    }

    @Override
    public boolean isValid(SectionTO sectionTO, ConstraintValidatorContext constraintValidatorContext) {
        if (method == CRUD.UPDATE || method == CRUD.DELETE) {
            return validator.validate(sectionTO, Existing.class).size() == 0;
        } else {
            return validator.validate(sectionTO).size() == 0;
        }
    }
}
