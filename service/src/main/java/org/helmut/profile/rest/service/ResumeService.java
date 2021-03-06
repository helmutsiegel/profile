package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.ResumeBC;
import org.helmut.profile.common.model.ResumeTO;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("resume")
public class ResumeService {

    @Inject
    private ResumeBC resumeBC;

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResumeTO getByEmail(@PathParam("email") String email) {
        return resumeBC.getByEmail(email);
    }

    @PUT
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ResumeTO resumeTO) {
        resumeBC.update(resumeTO);
        return Response.ok().build();
    }
}
