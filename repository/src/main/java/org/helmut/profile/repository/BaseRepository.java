package org.helmut.profile.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
public abstract class BaseRepository<T> {

    private final Class<T> type;

    @PersistenceContext(unitName = "profile-unit")
    protected EntityManager em;

    public BaseRepository() {
        Type t = ((Class) getClass().getGenericSuperclass()).getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public T findById(Long id) {
        return em.find(type, id);
    }

    public List<T> findAll() {
        return em.createQuery("select t from " + type.getSimpleName() + " t", type).getResultList();
    }

    public void persist(T t) {
        em.persist(t);
    }

    public T update(T t) {
        return em.merge(t);
    }

    public void delete(T t) {
        em.remove(em.merge(t));
    }

    public List<T> findByProperty(String property, Object value) {
        return em.createQuery("select t from " + type.getSimpleName() + " t where t." + property + " like :param", type)
                .setParameter("param", value)
                .getResultList();
    }

    public T findByUniqueProperty(String property, Object value) {
        return em.createQuery("select t from " + type.getSimpleName() + " t where t." + property + " like :param", type)
                .setParameter("param", value)
                .getSingleResult();
    }

    public int countByProperty(String property, Object value) {
        return ((Number) (em.createQuery("select count(t) from " + type.getSimpleName() + " t where t." + property + " like :param", type)
                .setParameter("param", value)
                .getSingleResult())).intValue();
    }

}
