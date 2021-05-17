package org.helmut.profile.repository;

import org.helmut.profile.repository.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepository {

    @PersistenceContext(unitName = "profile-unit")
    private EntityManager em;

    public UserEntity findById(Long id) {
        return em.find(UserEntity.class, id);
    }
}
