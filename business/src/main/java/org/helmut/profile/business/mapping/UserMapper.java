package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.SignUpUserTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.business.util.PasswordUtils;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserMapper {

    @Inject
    private PasswordUtils passwordUtils;

    public UserTO mapToTO(UserEntity userEntity) {
        UserTO userTO = new UserTO();
        userTO.setUsername(userEntity.getUsername());
        userTO.setFirstName(userEntity.getFirstName());
        userTO.setLastName(userEntity.getLastName());
        userTO.setBirthDate(userEntity.getBirthDate());
        userTO.setTitle(userEntity.getTitle());
        userTO.setSeniority(userEntity.getSeniority());
        return userTO;
    }

    public UserEntity mapToEntity(SignUpUserTO signUpUserTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(signUpUserTO.getFirstName());
        userEntity.setLastName(signUpUserTO.getLastName());
        userEntity.setUsername(signUpUserTO.getUsername());
        userEntity.setPassword(passwordUtils.digestPassword(signUpUserTO.getPassword1()));
        return userEntity;
    }
}