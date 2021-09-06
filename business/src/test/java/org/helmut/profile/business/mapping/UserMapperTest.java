package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.repository.entity.UserEntity;
import org.helmut.profile.repository.enums.Seniority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Test
    void mapToTO() {
        UserEntity userEntity = createUserEntity();

        UserTO userTO = userMapper.mapToTO(userEntity);

        assertEquals(userTO.getFirstName(), userEntity.getFirstName());
        assertEquals(userTO.getLastName(), userEntity.getLastName());
        assertEquals(userTO.getEmail(), userEntity.getEmail());
        assertEquals(userTO.getTitle(), userEntity.getTitle());
        assertEquals(userTO.getSeniority(), userEntity.getSeniority());
        assertEquals(userTO.getBirthDate(), userEntity.getBirthDate());
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("email");
        userEntity.setFirstName("First Name");
        userEntity.setLastName("Last Name");
        userEntity.setBirthDate(LocalDate.now());
        userEntity.setTitle("Job Title");
        userEntity.setSeniority(Seniority.JUNIOR);
        return userEntity;
    }

    @Test
    void mapToEntity() {
    }

    @Test
    void updateUser() {
        UserEntity userEntity = createUserEntity();
        UserTO userTO = new UserTO();

        userMapper.updateUser(userEntity, userTO);

        assertEquals(userTO.getFirstName(), userEntity.getFirstName());
        assertEquals(userTO.getLastName(), userEntity.getLastName());
        assertEquals(userTO.getTitle(), userEntity.getTitle());
    }
}