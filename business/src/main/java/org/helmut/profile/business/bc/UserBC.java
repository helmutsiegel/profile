package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.UserMapper;
import org.helmut.profile.business.model.SignUpUserTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.business.util.PasswordUtils;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.CVEntity;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class UserBC {

    @Inject
    private UserRepository userRepository;

    @Inject
    private CvRepository cvRepository;

    @Inject
    private UserMapper userMapper;

    @Inject
    private PasswordUtils passwordUtils;

    public List<UserTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public UserTO getByEmail(String email) {
        List<UserEntity> users = userRepository.findByProperty("email", email);
        return users.size() == 1 ? userMapper.mapToTO(users.get(0)) : null;
    }

    public UserTO logIn(String email, String password) {
        UserEntity userEntity = userRepository.getByEmailAndPassword(email, passwordUtils.digestPassword(password));
        return userMapper.mapToTO(userEntity);
    }

    public boolean existsUser(String email) {
        return userRepository.countByProperty("email", email) == 1;
    }

    public void signUp(SignUpUserTO signUpUserTO) {
        UserEntity userEntity = userMapper.mapToEntity(signUpUserTO);
        userRepository.persist(userEntity);
        CVEntity cvEntity = new CVEntity();
        cvEntity.setUserEntity(userEntity);
        cvRepository.persist(cvEntity);
    }

    public List<UserTO> searchUsers(String searchTerm) {
        return userRepository.searchUser(searchTerm)
                .stream()
                .map(userMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
