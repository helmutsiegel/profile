package org.helmut.profile.rest.service;

import org.helmut.profile.business.ResumeBC;
import org.helmut.profile.model.ResumeTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("resume")
@RequestScoped
public class ResumeService {

    @Inject
    private ResumeBC resumeBC;

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResumeTO getByUsername(@PathParam("username") String username) {
        return resumeBC.getByUsername(username);
    }
}
