package org.helmut.profile.repository.listener;

import org.helmut.profile.repository.entity.UserEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Externalized entity listener
 * Validates the first name, last name, and email
 */
public class UserValidationListener {

    final static String INVALID_EMAIL = "Invalid email";
    final static String INVALID_FIRST_NAME = "Invalid first name!";
    final static String INVALID_LAST_NAME = "Invalid last name!";

    @PrePersist
    @PreUpdate
    void validate(UserEntity userEntity) {
        if (Objects.isNull(userEntity.getEmail()) || !Pattern.compile("^(.+)@(.+)$").matcher(userEntity.getEmail()).matches()) {
            throw new IllegalArgumentException(INVALID_EMAIL);
        }
        if (Objects.isNull(userEntity.getFirstName()) || "".equals(userEntity.getFirstName())) {
            throw new IllegalArgumentException(INVALID_FIRST_NAME);
        }
        if (Objects.isNull(userEntity.getLastName()) || "".equals(userEntity.getLastName())) {
            throw new IllegalArgumentException(INVALID_LAST_NAME);
        }
    }
}
