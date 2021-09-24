package org.helmut.profile.business.startup;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

import java.util.logging.Logger;

@Startup
@Singleton
public class StartUp {

    @PostConstruct
    void init() {
        Logger.getLogger(StartUp.class.getName()).info("Startup Started");
    }


}