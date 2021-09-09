package org.helmut.profile.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BaseRepositoryTest {

    @InjectMocks
    private RepositoryStub repositoryStub;

    @Mock
    private EntityManager em;

    @Test
    void findById() {
        long id = 1L;
        EntityStub entityFromDB = new EntityStub();
        doReturn(entityFromDB).when(em).find(EntityStub.class, id);

        EntityStub entityStub = repositoryStub.findById(id);

        verify(em, times(1)).find(EntityStub.class, id);
        assertSame(entityStub, entityFromDB);
    }

    @Test
    void findAll() {
        TypedQuery<EntityStub> queryMock = mock(TypedQuery.class);
        List<EntityStub> entitiesFromDB = Collections.singletonList(new EntityStub());
        doReturn(entitiesFromDB).when(queryMock).getResultList();
        doReturn(queryMock).when(em).createQuery("select t from EntityStub t", EntityStub.class);

        List<EntityStub> entityStubs = repositoryStub.findAll();

        verify(em, times(1)).createQuery("select t from EntityStub t", EntityStub.class);
        verify(queryMock, times(1)).getResultList();
        assertSame(entityStubs, entitiesFromDB);
    }

    @Test
    void persist() {
        EntityStub entityStub = new EntityStub();
        repositoryStub.persist(entityStub);

        verify(em, times(1)).persist(entityStub);
    }

    @Test
    void update() {
        EntityStub entityStub = new EntityStub();
        repositoryStub.update(entityStub);

        verify(em, times(1)).merge(entityStub);
    }

    @Test
    void delete() {
        EntityStub entityStub = new EntityStub();
        EntityStub mergedEntity = new EntityStub();
        doReturn(mergedEntity).when(em).merge(entityStub);

        repositoryStub.delete(entityStub);

        verify(em, times(1)).merge(entityStub);
        verify(em, times(1)).remove(mergedEntity);
    }

    @Test
    void findByProperty() {
        TypedQuery<EntityStub> queryMock = mock(TypedQuery.class);
        List<EntityStub> entitiesFromDB = Collections.singletonList(new EntityStub());
        doReturn(entitiesFromDB).when(queryMock).getResultList();
        doReturn(queryMock).when(em).createQuery("select t from EntityStub t where t.property like :param", EntityStub.class);
        doReturn(queryMock).when(queryMock).setParameter("param", "value");

        List<EntityStub> entityStubs = repositoryStub.findByProperty("property", "value");

        verify(em, times(1)).createQuery("select t from EntityStub t where t.property like :param", EntityStub.class);
        verify(queryMock, times(1)).getResultList();
        verify(queryMock, times(1)).setParameter("param", "value");
        assertSame(entityStubs, entitiesFromDB);
    }

    @Test
    void findByUniqueProperty() {
        TypedQuery<EntityStub> queryMock = mock(TypedQuery.class);
        EntityStub entityFromDB = new EntityStub();
        doReturn(entityFromDB).when(queryMock).getSingleResult();
        doReturn(queryMock).when(em).createQuery("select t from EntityStub t where t.property like :param", EntityStub.class);
        doReturn(queryMock).when(queryMock).setParameter("param", "value");

        EntityStub entityStubs = repositoryStub.findByUniqueProperty("property", "value");

        verify(em, times(1)).createQuery("select t from EntityStub t where t.property like :param", EntityStub.class);
        verify(queryMock, times(1)).getSingleResult();
        verify(queryMock, times(1)).setParameter("param", "value");
        assertSame(entityStubs, entityFromDB);

    }

    @Test
    void countByProperty() {
        TypedQuery<EntityStub> queryMock = mock(TypedQuery.class);
        EntityStub entityFromDB = new EntityStub();
        doReturn(1).when(queryMock).getSingleResult();
        doReturn(queryMock).when(em).createQuery("select count(t) from EntityStub t where t.property like :param", EntityStub.class);
        doReturn(queryMock).when(queryMock).setParameter("param", "value");

        int count = repositoryStub.countByProperty("property", "value");

        verify(em, times(1)).createQuery("select count(t) from EntityStub t where t.property like :param", EntityStub.class);
        verify(queryMock, times(1)).getSingleResult();
        verify(queryMock, times(1)).setParameter("param", "value");
        assertEquals(count, 1);
    }
}

class RepositoryStub extends BaseRepository<EntityStub> {
}

class EntityStub {
}