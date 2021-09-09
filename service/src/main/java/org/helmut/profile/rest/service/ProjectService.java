package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.ProjectBC;
import org.helmut.profile.business.model.CreateChapterTO;
import org.helmut.profile.business.model.ProjectTO;
import org.helmut.profile.business.model.SectionTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;

@Path("project")
public class ProjectService {

    @Inject
    private ProjectBC projectBC;

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProjectTO getByName(@PathParam("name") String name) {
        return projectBC.getByName(name);
    }

    @GET
    @Path("byEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectTO> getByEmail(@PathParam("email") String email) {
        return projectBC.getByEmail(email);
    }

    @POST
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(ProjectTO projectTO) {
        String currentUserEmail = httpHeaders.getHeaderString(CURRENT_USER_EMAIL);
        UserTO userTO = new UserTO();
        userTO.setEmail(currentUserEmail);
        projectTO.setUserTO(userTO);
        try {
            projectBC.createProject(projectTO);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e)
                    .build();
        }
    }

    @POST
    @JWTTokenNeeded
    @Path("chapter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createChapter(CreateChapterTO createChapterTO) {
        String currentUserEmail = httpHeaders.getHeaderString(CURRENT_USER_EMAIL);
        try {
            projectBC.createChapter(createChapterTO, currentUserEmail);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e)
                    .build();
        }
    }

    @PUT
    @JWTTokenNeeded
    @Path("section")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSection(SectionTO sectionTO) {
        String currentUserEmail = httpHeaders.getHeaderString(CURRENT_USER_EMAIL);
        try {
            projectBC.updateSection(sectionTO, currentUserEmail);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e)
                    .build();
        }
    }
}
