package org.helmut.profile.business.mapping;

import org.helmut.profile.business.util.PasswordUtils;
import org.helmut.profile.common.model.SignUpUserTO;
import org.helmut.profile.common.model.UserTO;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserMapper {

    @Inject
    private PasswordUtils passwordUtils;

    public UserTO mapToTO(UserEntity userEntity) {
        return new UserTO.Builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .birthDate(String.valueOf(userEntity.getBirthDate()))
                .title(userEntity.getTitle())
                .seniority(userEntity.getSeniority())
                .build();
    }

    public UserEntity mapToEntity(SignUpUserTO signUpUserTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(signUpUserTO.getFirstName());
        userEntity.setLastName(signUpUserTO.getLastName());
        userEntity.setEmail(signUpUserTO.getEmail());
        userEntity.setPassword(passwordUtils.digestPassword(signUpUserTO.getPassword1()));
        return userEntity;
    }

    public void updateUser(UserEntity userEntity, UserTO userTO) {
        userEntity.setFirstName(userTO.getFirstName());
        userEntity.setLastName(userTO.getLastName());
        userEntity.setTitle(userTO.getTitle());
    }
}
