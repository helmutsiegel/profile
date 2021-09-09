package org.helmut.profile.repository;

import org.helmut.profile.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @InjectMocks
    private UserRepository userRepository;

    @Mock
    private EntityManager em;

    @Test
    void searchUser() {
        String query = "select u from UserEntity u where lower(u.firstName) like :param or lower(u.lastName) like :param or lower(u.email) like :param";
        TypedQuery<UserEntity> queryMock = mock(TypedQuery.class);
        List<UserEntity> usersFromDB = Collections.singletonList(new UserEntity());
        doReturn(usersFromDB).when(queryMock).getResultList();
        doReturn(queryMock).when(em).createQuery(query, UserEntity.class);
        doReturn(queryMock).when(queryMock).setParameter("param", "%searchterm%");

        List<UserEntity> userEntities = userRepository.searchUser("SearchTerm");

        verify(em, times(1)).createQuery(query, UserEntity.class);
        verify(queryMock, times(1)).getResultList();
        verify(queryMock, times(1)).setParameter("param", "%searchterm%");
        assertSame(userEntities, usersFromDB);
    }

    @Test
    void getByEmailAndPassword() {
        TypedQuery<UserEntity> queryMock = mock(TypedQuery.class);
        UserEntity entityFromDB = new UserEntity();
        doReturn(entityFromDB).when(queryMock).getSingleResult();
        doReturn(queryMock).when(em).createNamedQuery(UserEntity.BY_EMAIL_AND_PASSWORD, UserEntity.class);
        doReturn(queryMock).when(queryMock).setParameter("email", "email@mail");
        doReturn(queryMock).when(queryMock).setParameter("password", "Password");

        UserEntity userEntity = userRepository.getByEmailAndPassword("email@mail", "Password");

        verify(em, times(1)).createNamedQuery(UserEntity.BY_EMAIL_AND_PASSWORD, UserEntity.class);
        verify(queryMock, times(1)).getSingleResult();
        verify(queryMock, times(1)).setParameter("email", "email@mail");
        verify(queryMock, times(1)).setParameter("password", "Password");
        assertSame(userEntity, userEntity);
    }

    @Test
    void getByEmail() {

    }
}