package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.UserMapper;
import org.helmut.profile.common.model.ChangePasswordTO;
import org.helmut.profile.common.model.SignUpUserTO;
import org.helmut.profile.common.model.UserTO;
import org.helmut.profile.business.util.PasswordUtils;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserBCTest {

    @InjectMocks
    private UserBC userBC;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordUtils passwordUtils;

    @Test
    void getAll() {
        UserEntity userEntity = new UserEntity();
        UserTO userTO = new UserTO();
        doReturn(Collections.singletonList(userEntity)).when(userRepository).findAll();
        doReturn(userTO).when(userMapper).mapToTO(userEntity);

        List<UserTO> userTOS = userBC.getAll();

        assertEquals(userTOS.size(), 1);
        assertSame(userTOS.get(0), userTO);
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).mapToTO(userEntity);
    }

    @Test
    void getByEmail() {
        String email = "email@mail.com";
        UserEntity userEntity = new UserEntity();
        UserTO userTO = new UserTO();
        doReturn(userEntity).when(userRepository).getByEmail(email);
        doReturn(userTO).when(userMapper).mapToTO(userEntity);

        UserTO mappedUserTO = userBC.getByEmail(email);

        assertSame(mappedUserTO, userTO);
        verify(userRepository, times(1)).getByEmail(email);
        verify(userMapper, times(1)).mapToTO(userEntity);
    }

    @Test
    void logIn() {
        String email = "email@mail.com";
        String password = "password";
        String digestedPassword = "digestedPassword";
        UserEntity userEntity = new UserEntity();
        UserTO userTO = new UserTO();
        doReturn(userEntity).when(userRepository).getByEmailAndPassword(email, digestedPassword);
        doReturn(userTO).when(userMapper).mapToTO(userEntity);
        doReturn(digestedPassword).when(passwordUtils).digestPassword(password);

        UserTO mappedUserTO = userBC.logIn(email, password);

        assertSame(mappedUserTO, userTO);
        verify(userRepository, times(1)).getByEmailAndPassword(email, digestedPassword);
        verify(userMapper, times(1)).mapToTO(userEntity);
        verify(passwordUtils, times(1)).digestPassword(password);
    }

    @Test
    void changePassword() {
        ChangePasswordTO changePasswordTO = new ChangePasswordTO();
        changePasswordTO.setCurrentPassword("currentPassword");
        changePasswordTO.setNewPassword1("newPassword");

        String digestedCurrentPassword = "digestedCurrentPassword";
        String digestedNewPassword = "digestedNewPassword";
        doReturn(digestedCurrentPassword).when(passwordUtils).digestPassword(changePasswordTO.getCurrentPassword());
        doReturn(digestedNewPassword).when(passwordUtils).digestPassword(changePasswordTO.getNewPassword1());
        UserEntity userEntity = new UserEntity();
        doReturn(userEntity).when(userRepository).getByEmailAndPassword(changePasswordTO.getEmail(), digestedCurrentPassword);

        userBC.changePassword(changePasswordTO);

        verify(passwordUtils, times(1)).digestPassword(changePasswordTO.getCurrentPassword());
        verify(passwordUtils, times(1)).digestPassword(changePasswordTO.getNewPassword1());
        verify(userRepository, times(1)).getByEmailAndPassword(changePasswordTO.getEmail(), digestedCurrentPassword);
        verify(userRepository, times(1)).update(userEntity);
        assertEquals(userEntity.getPassword(), digestedNewPassword);
    }

    @Nested
    @DisplayName("Test user exists")
    class ExistsUserTest {

        @Test
        @DisplayName("User exits")
        void userExists() {
            String email = "mail@mail.com";
            doReturn(1).when(userRepository).countByProperty("email", email);

            boolean userExists = userBC.existsUser(email);

            assertTrue(userExists);
            verify(userRepository, times(1)).countByProperty("email", email);
        }

        @Test
        @DisplayName("User does not exits")
        void userDoesNotExists() {
            String email = "mail@mail.com";
            doReturn(0).when(userRepository).countByProperty("email", email);

            boolean userExists = userBC.existsUser(email);

            assertFalse(userExists);
            verify(userRepository, times(1)).countByProperty("email", email);
        }
    }


    @Test
    void signUp() {
        SignUpUserTO signUpUserTO = new SignUpUserTO();
        UserEntity userEntity = new UserEntity();
        doReturn(userEntity).when(userMapper).mapToEntity(signUpUserTO);

        userBC.signUp(signUpUserTO);

        verify(userMapper).mapToEntity(signUpUserTO);
        verify(userRepository).persist(userEntity);
    }

    @Test
    void searchUsers() {
        String searchTerm = "searchTerm";
        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();
        doReturn(Arrays.asList(userEntity1, userEntity2)).when(userRepository).searchUser(searchTerm);
        UserTO userTO1 = new UserTO();
        UserTO userTO2 = new UserTO();
        doReturn(userTO1).when(userMapper).mapToTO(userEntity1);
        doReturn(userTO2).when(userMapper).mapToTO(userEntity2);

        List<UserTO> foundUserTOs = userBC.searchUsers(searchTerm);

        assertEquals(foundUserTOs.size(), 2);
        assertTrue(foundUserTOs.contains(userTO1));
        assertTrue(foundUserTOs.contains(userTO2));
        verify(userRepository, times(1)).searchUser(searchTerm);
        verify(userMapper, times(1)).mapToTO(userEntity1);
        verify(userMapper, times(1)).mapToTO(userEntity2);
    }

    @Test
    void updateUser() {
        UserTO userTO = new UserTO();
        userTO.setEmail("email@mail.com");
        UserEntity userEntity = new UserEntity();
        doReturn(userEntity).when(userRepository).getByEmail(userTO.getEmail());

        userBC.updateUser(userTO);

        verify(userRepository, times(2)).getByEmail(userTO.getEmail());
        verify(userMapper, times(1)).updateUser(userEntity, userTO);
        verify(userRepository, times(1)).update(userEntity);
    }
}