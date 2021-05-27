package org.helmut.profile.rest.service;

import org.helmut.profile.business.ProjectBC;
import org.helmut.profile.model.ProjectTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("project")
@RequestScoped
public class ProjectService {

    @Inject
    private ProjectBC projectBC;

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectTO> getByUsername(@PathParam("username") String username) {
        return projectBC.getByUsername(username);
    }
}
