package org.helmut.profile.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ValidationUtilsTest {

    @InjectMocks
    private ValidationUtils<TestObj> validationUtils;

    @Test
    void violationsToSting() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        String violationsToSting = validationUtils.violationsToSting(validator.validate(new TestObj()));

        assertTrue(violationsToSting.contains("# TestObj.id - Invalid Value = null - Error message = may not be null"));
        assertTrue(violationsToSting.contains("# TestObj.attribute2 - Invalid Value =  - Error message = size must be between 2 and 5"));
        assertTrue(violationsToSting.contains("# TestObj.attribute1 - Invalid Value = null - Error message = may not be null"));

        vf.close();
    }
}

class TestObj {
    @NotNull
    private Long id;

    @NotNull
    private String attribute1;

    @NotNull
    @Size(min = 2, max = 5)
    private String attribute2 = "";


}