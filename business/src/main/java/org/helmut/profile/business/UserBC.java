package org.helmut.profile.business;

import org.helmut.profile.mapping.UserMapper;
import org.helmut.profile.model.UserTO;
import org.helmut.profile.repository.UserRepository;

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
}
