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
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public CvTO getByEmail(@PathParam("email") String email) {
        return cvBC.getByEmail(email);
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
