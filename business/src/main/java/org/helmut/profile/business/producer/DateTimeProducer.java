package org.helmut.profile.business.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.time.OffsetDateTime;

@RequestScoped
public class DateTimeProducer {

    @Produces
    private OffsetDateTime currentDateTime(InjectionPoint injectionPoint) {
        System.out.println("The OffsetDateTime was injected in "+injectionPoint.getMember().getDeclaringClass().getName());
        return OffsetDateTime.now();
    }

    private void disposer(@Disposes OffsetDateTime currentDateTime){
        System.out.println("The disposer for OffsetDateTime was invoked!");
    }

}
