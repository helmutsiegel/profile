package org.helmut.profile.repository.listener;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LifecycleListenerTest {

    @InjectMocks
    private LifecycleListener lifecycleListener;

    @Test
    void prePersist() {
        lifecycleListener.prePersist(new Object());
    }

    @Test
    void postPersist() {
        lifecycleListener.postPersist(new Object());
    }

    @Test
    void preUpdate() {
        lifecycleListener.preUpdate(new Object());
    }

    @Test
    void postUpdate() {
        lifecycleListener.postUpdate(new Object());
    }

    @Test
    void preRemove() {
        lifecycleListener.preRemove(new Object());
    }

    @Test
    void postRemove() {
        lifecycleListener.postRemove(new Object());
    }

    @Test
    void postLoad() {
        lifecycleListener.postLoad(new Object());
    }
}