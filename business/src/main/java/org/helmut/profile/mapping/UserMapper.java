package org.helmut.profile.mapping;

import org.helmut.profile.model.UserTO;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserMapper {

    public UserTO mapUserTO(UserEntity userEntity) {
        UserTO userTO = new UserTO();
        userTO.setUsername(userEntity.getUsername());
        userTO.setFirstName(userEntity.getFirstName());
        userTO.setLastName(userEntity.getLastName());
        userTO.setBirthDate(userEntity.getBirthDate());
        userTO.setTitle(userEntity.getTitle());
        return userTO;
    }
}
