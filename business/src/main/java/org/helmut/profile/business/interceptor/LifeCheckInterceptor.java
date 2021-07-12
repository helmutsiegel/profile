package org.helmut.profile.business.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Lifecycle interceptor
 */
@LifeCheck
@Interceptor
public class LifeCheckInterceptor {

    @PostConstruct
    private void postConstruct(InvocationContext ic) {
        System.out.println("PostConstruct " + ic.getTarget().getClass().getName());
    }

    @PreDestroy
    private void preDestroy(InvocationContext ic) {
        System.out.println("PreDestroy: " + ic.getTarget().getClass().getName());
    }

}
