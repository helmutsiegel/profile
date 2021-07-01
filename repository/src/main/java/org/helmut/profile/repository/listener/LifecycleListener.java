package org.helmut.profile.repository.listener;

import javax.persistence.*;

/**
 * Default lifecycle listener
 */
public class LifecycleListener {
    @PrePersist
    void prePersist(Object object) {
        System.out.println(".LifecycleListener prePersist() " + object.getClass().getSimpleName());
    }

    @PostPersist
    void postPersist(Object object) {
        System.out.println(".LifecycleListener postPersist() " + object.getClass().getSimpleName());
    }

    @PreUpdate
    void preUpdate(Object object) {
        System.out.println(".LifecycleListener preUpdate() " + object.getClass().getSimpleName());
    }

    @PostUpdate
    void postUpdate(Object object) {
        System.out.println(".LifecycleListener postUpdate() " + object.getClass().getSimpleName());
    }

    @PreRemove
    void preRemove(Object object) {
        System.out.println(".LifecycleListener preRemove() " + object.getClass().getSimpleName());
    }

    @PostRemove
    void postRemove(Object object) {
        System.out.println(".LifecycleListener postRemove() " + object.getClass().getSimpleName());
    }

    @PostLoad
    void postLoad(Object object) {
        System.out.println(".LifecycleListener postLoad() " + object.getClass().getSimpleName());
    }
}
