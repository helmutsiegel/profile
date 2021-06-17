package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.CvBC;
import org.helmut.profile.business.model.CvTO;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cv")
public class CvService {

    @Inject
    private CvBC cvBC;

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public CvTO getByUsername(@PathParam("username") String username) {
        return cvBC.getByUsername(username);
    }

    @PUT
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCV(CvTO cvTO) {
       cvBC.update(cvTO);
       return Response.ok().build();
    }
}
