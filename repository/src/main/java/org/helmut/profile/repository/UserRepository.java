package org.helmut.profile.repository;

import org.helmut.profile.repository.entity.UserEntity;

import java.util.List;

public class UserRepository extends BaseRepository<UserEntity> {


    /**
     * Searches user matching by first name, last name or email
     */
    public List<UserEntity> searchUser(String searchTerm) {
        return em.createQuery("select u from UserEntity u where lower(u.firstName) like :param "
                + "or lower(u.lastName) like :param or lower(u.email) like :param", UserEntity.class)
                .setParameter("param", "%" + searchTerm.toLowerCase() + "%")
                .getResultList();
    }

    /**
     * Get user by email and password
     */
    public UserEntity getByEmailAndPassword(String email, String password) {
        return em.createNamedQuery(UserEntity.BY_EMAIL_AND_PASSWORD, UserEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    public UserEntity getByEmail(String email) {
        return findByUniqueProperty("email", email);
    }
}
