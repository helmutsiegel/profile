package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.SignUpUserTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.business.util.PasswordUtils;
import org.helmut.profile.repository.entity.UserEntity;
import org.helmut.profile.repository.enums.Seniority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private PasswordUtils passwordUtils;

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
        SignUpUserTO signUpUserTO = new SignUpUserTO();
        signUpUserTO.setFirstName("First Name");
        signUpUserTO.setLastName("Last Name");
        signUpUserTO.setPassword1("Password");
        signUpUserTO.setEmail("Email");

        doReturn("digested password").when(passwordUtils).digestPassword(signUpUserTO.getPassword1());

        UserEntity userEntity = userMapper.mapToEntity(signUpUserTO);

        assertEquals(userEntity.getFirstName(), signUpUserTO.getFirstName());
        assertEquals(userEntity.getLastName(), signUpUserTO.getLastName());
        assertEquals(userEntity.getEmail(), signUpUserTO.getEmail());
        assertEquals(userEntity.getPassword(), "digested password");
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