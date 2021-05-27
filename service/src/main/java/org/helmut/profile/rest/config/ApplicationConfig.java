package org.helmut.profile.rest.config;

import org.helmut.profile.rest.service.CvService;
import org.helmut.profile.rest.service.ProjectService;
import org.helmut.profile.rest.service.UserService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("rs")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(UserService.class);
        resources.add(CvService.class);
        resources.add(ProjectService.class);
    }
}