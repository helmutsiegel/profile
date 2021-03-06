package org.helmut.profile.business.bc;

import org.helmut.profile.business.bci.UserBCI;
import org.helmut.profile.business.interceptor.LifeCheck;
import org.helmut.profile.business.mapping.UserMapper;
import org.helmut.profile.common.model.ChangePasswordTO;
import org.helmut.profile.common.model.SignUpUserTO;
import org.helmut.profile.common.model.UserTO;
import org.helmut.profile.business.util.PasswordUtils;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@LifeCheck
@RequestScoped
public class UserBC implements UserBCI {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @Inject
    private PasswordUtils passwordUtils;

    public List<UserTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserTO getByEmail(String email) {
        return userMapper.mapToTO(userRepository.getByEmail(email));
    }

    @Override
    public UserTO logIn(String email, String password) {
        UserEntity userEntity = userRepository.getByEmailAndPassword(email, passwordUtils.digestPassword(password));
        return userMapper.mapToTO(userEntity);
    }

    @Override
    public void changePassword(ChangePasswordTO changePasswordTO) {
        UserEntity userEntity = userRepository.getByEmailAndPassword(changePasswordTO.getEmail(),
                passwordUtils.digestPassword(changePasswordTO.getCurrentPassword()));

        userEntity.setPassword(passwordUtils.digestPassword(changePasswordTO.getNewPassword1()));
        userRepository.update(userEntity);
    }

    @Override
    public boolean existsUser(String email) {
        return userRepository.countByProperty("email", email) == 1;
    }

    @Override
    public void signUp(SignUpUserTO signUpUserTO) {
        UserEntity userEntity = userMapper.mapToEntity(signUpUserTO);
        userRepository.persist(userEntity);
    }

    @Override
    public List<UserTO> searchUsers(String searchTerm) {
        return userRepository.searchUser(searchTerm)
                .stream()
                .map(userMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserTO updateUser(UserTO userTO) {
        UserEntity userEntity = userRepository.getByEmail(userTO.getEmail());
        userMapper.updateUser(userEntity, userTO);
        userRepository.update(userEntity);
        return getByEmail(userTO.getEmail());
    }
}
