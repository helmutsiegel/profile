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

    @PrePersist
    @PreUpdate
    private void validate(UserEntity userEntity) {
        if (Objects.isNull(userEntity.getEmail()) || !Pattern.compile("^(.+)@(.+)$").matcher(userEntity.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (Objects.isNull(userEntity.getFirstName()) || "".equals(userEntity.getFirstName())) {
            throw new IllegalArgumentException("Invalid first name!");
        }
        if (Objects.isNull(userEntity.getLastName()) || "".equals(userEntity.getLastName())) {
            throw new IllegalArgumentException("Invalid last name!");
        }
    }
}
