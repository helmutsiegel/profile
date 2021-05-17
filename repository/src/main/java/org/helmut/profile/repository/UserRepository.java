package org.helmut.profile.repository;

import org.helmut.profile.repository.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class UserRepository {

    @PersistenceContext(unitName = "profile-unit")
    private EntityManager em;

    @Transactional(Transactional.TxType.NEVER)
    public UserEntity findById(Long id) {
        return em.find(UserEntity.class, id);
    }
}
