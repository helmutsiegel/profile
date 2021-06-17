package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.ProjectBC;
import org.helmut.profile.business.model.ProjectTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("project")
public class ProjectService {

    @Inject
    private ProjectBC projectBC;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProjectTO getByName(@PathParam("name") String name) {
        return projectBC.getByName(name);
    }

    @GET
    @Path("byUsername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectTO> getByUsername(@PathParam("username") String username) {
        return projectBC.getByUsername(username);
    }
}
