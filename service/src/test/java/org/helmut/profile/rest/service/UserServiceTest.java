package org.helmut.profile.rest.service;

import org.helmut.profile.business.bci.UserBCI;
import org.helmut.profile.business.client.UserClient;
import org.helmut.profile.business.model.ChangePasswordTO;
import org.helmut.profile.business.model.LoginUserTO;
import org.helmut.profile.business.model.SignUpUserTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.common.logging.Logger;
import org.helmut.profile.rest.auth.util.TokenIssuer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserClient userClient;

    @Mock
    private Logger logger;

    @Mock
    private UserBCI userBC;

    @Mock
    private TokenIssuer tokenIssuer;

    @Mock
    private HttpHeaders httpHeaders;

    @Test
    void getAll() {
        List<UserTO> usersFromBC = Collections.singletonList(new UserTO());
        doReturn(usersFromBC).when(userBC).getAll();

        List<UserTO> userTOS = userService.getAll();

        assertSame(userTOS, usersFromBC);
        verify(userBC, times(1)).getAll();
    }

    @Test
    void getByCurrentUser() {
        UserTO userFromBC = new UserTO();
        String email = "email";
        doReturn(userFromBC).when(userBC).getByEmail(email);
        doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

        UserTO userTO = userService.getByCurrentUser();

        assertSame(userTO, userFromBC);
        verify(userBC, times(1)).getByEmail(email);
        verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
    }

    @Test
    void getByEmail() {
        String email = "email";
        UserTO userTOFromClient = new UserTO();
        doReturn(userTOFromClient).when(userClient).getUserByEmail(email);

        UserTO userTO = userService.getByEmail(email);

        assertSame(userTO, userTOFromClient);
        verify(userClient, times(1)).getUserByEmail(email);
    }

    @Test
    void getUserByEmail() {
        String email = "email";
        UserTO userTOFromBC = new UserTO();
        doReturn(userTOFromBC).when(userBC).getByEmail(email);

        UserTO userTO = userService.getUserByEmail(email);

        assertSame(userTO, userTOFromBC);
        verify(userBC, times(1)).getByEmail(email);
    }

    @Nested
    @DisplayName("User exists test")
    class UserExistsTest {

        @Test
        @DisplayName("Exists")
        void userExists() {
            String email = "email";
            doReturn(true).when(userBC).existsUser(email);

            Response response = userService.userExists(email);

            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
            verify(userBC, times(1)).existsUser(email);
        }

        @Test
        @DisplayName("Does not exists")
        void userDoesNotExists() {
            String email = "email";
            doReturn(false).when(userBC).existsUser(email);

            Response response = userService.userExists(email);

            assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
            verify(userBC, times(1)).existsUser(email);
        }
    }

    @Test
    void searchUsers() {
        String searchTerm = "searchTerm";
        List<UserTO> usersFromBC = Collections.singletonList(new UserTO());
        doReturn(usersFromBC).when(userBC).searchUsers(searchTerm);

        List<UserTO> userTOS = userService.searchUsers(searchTerm);

        assertSame(userTOS, usersFromBC);
        verify(userBC, times(1)).searchUsers(searchTerm);
    }

    @Nested
    @DisplayName("Sign up user test")
    class SignUpTest {

        @Test
        @DisplayName("Successful")
        void signUpSuccessful() {
            SignUpUserTO signUpUserTO = new SignUpUserTO();

            Response response = userService.signUp(signUpUserTO);

            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
            assertSame(response.getEntity(), signUpUserTO);
            verify(userBC, times(1)).signUp(signUpUserTO);
        }

        @Test
        @DisplayName("Failed")
        void signUpFailed() {
            SignUpUserTO signUpUserTO = new SignUpUserTO();
            IllegalArgumentException exception = new IllegalArgumentException();
            doThrow(exception).when(userBC).signUp(signUpUserTO);

            Response response = userService.signUp(signUpUserTO);

            assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
            assertSame(response.getEntity(), exception);
            verify(userBC, times(1)).signUp(signUpUserTO);
        }
    }

    @Nested
    @DisplayName("Sign up user test")
    class LoginTest {

        @Test
        @DisplayName("Successful")
        void loginSuccessful() {
            LoginUserTO loginUserTO = new LoginUserTO();
            loginUserTO.setEmail("mail");
            loginUserTO.setPassword("password");
            UserTO userTO = new UserTO();
            doReturn(userTO).when(userBC).logIn(loginUserTO.getEmail(), loginUserTO.getPassword());
            String token = "token";
            doReturn(token).when(tokenIssuer).issueToken(loginUserTO.getEmail());

            Response response = userService.login(loginUserTO);

            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
            assertSame(response.getEntity(), userTO);
            assertEquals(response.getHeaderString(AUTHORIZATION), "Bearer " + token);
            verify(userBC, times(1)).logIn(loginUserTO.getEmail(), loginUserTO.getPassword());
            verify(tokenIssuer, times(1)).issueToken(loginUserTO.getEmail());
        }

        @Test
        @DisplayName("Failed")
        void loginFailed() {
            LoginUserTO loginUserTO = new LoginUserTO();
            loginUserTO.setEmail("mail");
            loginUserTO.setPassword("password");
            doThrow(new IllegalArgumentException()).when(userBC).logIn(loginUserTO.getEmail(), loginUserTO.getPassword());

            Response response = userService.login(loginUserTO);

            assertEquals(response.getStatus(), Response.Status.UNAUTHORIZED.getStatusCode());
            assertNull(response.getHeaderString(AUTHORIZATION));
            verify(userBC, times(1)).logIn(loginUserTO.getEmail(), loginUserTO.getPassword());
            verify(tokenIssuer, never()).issueToken(any());
        }
    }

    @Nested
    @DisplayName("Change password test")
    class ChangePasswordTest {

        @Nested
        @DisplayName("Different passwords")
        class DifferentPasswordsTest {
            @Test
            @DisplayName("Should fail")
            void changePassword() {
                ChangePasswordTO changePasswordTO = new ChangePasswordTO();
                changePasswordTO.setNewPassword1("Pass1");
                changePasswordTO.setNewPassword2("Pass2");

                Response response = userService.changePassword(changePasswordTO);

                assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
                assertEquals(response.getEntity(), "The new passwords does not match!");
            }
        }

        @Nested
        @DisplayName("Equal passwords")
        class EqualPasswordsTest {

            @Test
            @DisplayName("Change successful")
            void changePasswordSuccessful() {
                ChangePasswordTO changePasswordTO = new ChangePasswordTO();
                changePasswordTO.setNewPassword1("Pass");
                changePasswordTO.setNewPassword2("Pass");
                String email = "email";
                doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

                Response response = userService.changePassword(changePasswordTO);

                assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
                verify(userBC, times(1)).changePassword(changePasswordTO);
                verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
                assertEquals(changePasswordTO.getEmail(), email);
            }

            @Test
            @DisplayName("Change failed")
            void changePasswordFailed() {
                ChangePasswordTO changePasswordTO = new ChangePasswordTO();
                changePasswordTO.setNewPassword1("Pass");
                changePasswordTO.setNewPassword2("Pass");
                doThrow(new IllegalStateException()).when(userBC).changePassword(changePasswordTO);

                Response response = userService.changePassword(changePasswordTO);

                assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
                assertEquals(response.getEntity(), "Current password is incorrect!");
                verify(userBC, times(1)).changePassword(changePasswordTO);
                verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
                verify(logger, times(1)).info("Password could not change", UserService.class);
            }
        }
    }

    @Nested
    @DisplayName("Update user")
    class UpdateUserTest {

        @Test
        @DisplayName("Successful")
        void updateUserSuccessful() {
            UserTO userToUpdate = new UserTO();
            String email = "email";
            userToUpdate.setEmail(email);
            doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);
            UserTO updatedUserTO = new UserTO();
            doReturn(updatedUserTO).when(userBC).updateUser(userToUpdate);

            Response response = userService.updateUser(userToUpdate);

            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
            assertSame(response.getEntity(), updatedUserTO);
            verify(userBC, times(1)).updateUser(userToUpdate);
            verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
        }

        @Test
        @DisplayName("Failed")
        void updateUserFailed() {
            UserTO userToUpdate = new UserTO();
            userToUpdate.setEmail("email");
            doReturn("different email").when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

            Response response = userService.updateUser(userToUpdate);

            assertEquals(response.getStatus(), Response.Status.UNAUTHORIZED.getStatusCode());
            verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
        }
    }
}