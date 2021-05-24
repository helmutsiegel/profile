package org.helmut.profile.rest.service;

import org.helmut.profile.business.CvBC;
import org.helmut.profile.model.CvTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("cv")
@RequestScoped

public class CvService {

    @Inject
    private CvBC cvBC;

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public CvTO getByUsername(@PathParam("username") String username) {
        return cvBC.getByUsername(username);
    }
}
