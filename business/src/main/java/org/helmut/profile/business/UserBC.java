package org.helmut.profile.business;

import org.helmut.profile.mapping.UserMapper;
import org.helmut.profile.model.UserTO;
import org.helmut.profile.repository.UserRepository;
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
    private UserMapper userMapper;

    public List<UserTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapUserTO)
                .collect(Collectors.toList());
    }

    public UserTO getByUsername(String username) {
        List<UserEntity> users = userRepository.findByProperty("username", username);
        return users.size() == 1 ? userMapper.mapUserTO(users.get(0)) : null;
    }

    public boolean existsUser(String username) {
        return userRepository.countByProperty("username", username) == 1;
    }
}
