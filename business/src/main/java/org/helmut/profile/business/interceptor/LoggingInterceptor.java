package org.helmut.profile.business.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import static javax.interceptor.Interceptor.Priority.LIBRARY_BEFORE;

/**
 * For more information about priority read the javadoc of {@link javax.interceptor.Interceptor.Priority}
 */
@Loggable
@Interceptor
@Priority(LIBRARY_BEFORE)
public class LoggingInterceptor {

    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        System.out.println("Intercept: " + ic.getMethod());
        return ic.proceed();
    }
}
