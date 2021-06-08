package org.helmut.profile.business;

import org.helmut.profile.mapping.UserMapper;
import org.helmut.profile.model.SignUpUserTO;
import org.helmut.profile.model.UserTO;
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

    public List<UserTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public UserTO getByUsername(String username) {
        List<UserEntity> users = userRepository.findByProperty("username", username);
        return users.size() == 1 ? userMapper.mapToTO(users.get(0)) : null;
    }

    public UserTO logIn(String username, String password) {
        UserEntity userEntity = userRepository.findByUniqueProperty("username", username);
        return userMapper.mapToTO(userEntity);
    }

    public boolean existsUser(String username) {
        return userRepository.countByProperty("username", username) == 1;
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
