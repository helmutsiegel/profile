package org.helmut.profile.repository.listener;

import org.helmut.profile.repository.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.helmut.profile.repository.listener.UserValidationListener.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserValidationListenerTest {

    @InjectMocks
    private UserValidationListener userValidationListener;

    @Nested
    @DisplayName("Invalid email")
    class InvalidEmail {
        @Test
        @DisplayName("Incorrect email pattern")
        void validate1() {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("mail");
            assertThrows(IllegalArgumentException.class, () -> {
                userValidationListener.validate(userEntity);
            }, INVALID_EMAIL);
        }

        @Test
        @DisplayName("Null email")
        void validate2() {
            UserEntity userEntity = new UserEntity();
            assertThrows(IllegalArgumentException.class, () -> {
                userValidationListener.validate(userEntity);
            }, INVALID_EMAIL);
        }
    }

    @Nested
    @DisplayName("Invalid email")
    class InvalidFirstName {
        @Test
        @DisplayName("Incorrect first name")
        void validate1() {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("mail@mail.com");
            userEntity.setFirstName("");
            assertThrows(IllegalArgumentException.class, () -> {
                userValidationListener.validate(userEntity);
            }, INVALID_FIRST_NAME);
        }

        @Test
        @DisplayName("Null first name")
        void validate2() {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("mail@mail.com");
            assertThrows(IllegalArgumentException.class, () -> {
                userValidationListener.validate(userEntity);
            }, INVALID_EMAIL);
        }
    }

    @Nested
    @DisplayName("Invalid last name")
    class InvalidLastName {
        @Test
        @DisplayName("Incorrect last name")
        void validate1() {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("mail@mail.com");
            userEntity.setFirstName("First name");
            userEntity.setLastName("");
            assertThrows(IllegalArgumentException.class, () -> {
                userValidationListener.validate(userEntity);
            }, INVALID_LAST_NAME);
        }

        @Test
        @DisplayName("Null last name")
        void validate2() {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("mail@mail.com");
            userEntity.setFirstName("First name");
            assertThrows(IllegalArgumentException.class, () -> {
                userValidationListener.validate(userEntity);
            }, INVALID_LAST_NAME);
        }
    }

    @Test
    @DisplayName("Valid user Entity")
    void validate() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("mail@mail.com");
        userEntity.setFirstName("First name");
        userEntity.setLastName("Last name");

        assertDoesNotThrow(() -> {
            userValidationListener.validate(userEntity);
        });
    }
}