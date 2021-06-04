package org.helmut.profile.mapping;

import org.helmut.profile.model.SignUpUserTO;
import org.helmut.profile.model.UserTO;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserMapper {

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
        userEntity.setTitle(signUpUserTO.getTitle());
        userEntity.setBirthDate(signUpUserTO.getBirthDate());
        return userEntity;
    }
}
