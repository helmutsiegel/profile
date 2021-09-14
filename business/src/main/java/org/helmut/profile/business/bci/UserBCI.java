package org.helmut.profile.business.bci;

import org.helmut.profile.common.model.ChangePasswordTO;
import org.helmut.profile.common.model.SignUpUserTO;
import org.helmut.profile.common.model.UserTO;

import java.util.List;

public interface UserBCI {
    void signUp(SignUpUserTO signUpUserTO);

    List<UserTO> getAll();

    UserTO getByEmail(String email);

    UserTO logIn(String email, String password);

    void changePassword(ChangePasswordTO changePasswordTO);

    boolean existsUser(String email);

    List<UserTO> searchUsers(String searchTerm);

    UserTO updateUser(UserTO userTO);
}
