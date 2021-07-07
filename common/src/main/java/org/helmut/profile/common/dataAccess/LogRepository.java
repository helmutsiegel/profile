package org.helmut.profile.common.dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Repository class for Log
 */
@Transactional
public class LogRepository {

    @PersistenceContext(unitName = "profile-unit-log")
    protected EntityManager em;

    public void persist(LogEntity entity) {
        em.persist(entity);
    }
}
