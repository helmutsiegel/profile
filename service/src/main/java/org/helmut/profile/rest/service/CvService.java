package org.helmut.profile.rest.service;

import org.helmut.profile.business.CvBC;
import org.helmut.profile.model.CvTO;
import org.helmut.profile.model.SignUpUserTO;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;

import javax.enterprise.context.RequestScoped;
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
