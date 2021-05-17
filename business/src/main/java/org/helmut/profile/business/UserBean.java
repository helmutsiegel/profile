package org.helmut.profile.business;

import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserBean {

    @Inject
    private UserRepository userRepository;

    public UserEntity getById(Long id) {
        return userRepository.findById(id);
    }
}
