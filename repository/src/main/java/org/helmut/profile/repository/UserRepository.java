package org.helmut.profile.repository;

import org.helmut.profile.repository.entity.UserEntity;

import java.util.List;

public class UserRepository extends BaseRepository<UserEntity> {

    /**
     * Searches user matching by first name, last name or username
     */
    public List<UserEntity> searchUser(String searchTerm) {
        return em.createQuery("select u from UserEntity u where lower(u.firstName) like :param "
                + "or lower(u.lastName) like :param or lower(u.username) like :param", UserEntity.class)
                .setParameter("param", "%" + searchTerm.toLowerCase() + "%")
                .getResultList();
    }

    /**
     * Get user by username and password
     */
    public UserEntity getByUsernameAndPassword(String username, String password) {
        return em.createQuery("select u from UserEntity u where u.username like :username and u.password like :password", UserEntity.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }
}
