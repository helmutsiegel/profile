package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.CvBC;
import org.helmut.profile.business.model.CvTO;
import org.helmut.profile.business.model.ExperienceTO;
import org.helmut.profile.business.model.LanguageTO;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;

@Path("cv")
public class CvService {

    @Inject
    private CvBC cvBC;

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public CvTO getByEmail(@PathParam("email") String email) {
        return cvBC.getByEmail(email);
    }

    @PUT
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCV(CvTO cvTO) {
        cvBC.update(cvTO);
        return Response.ok().build();
    }

    @PUT
    @Path("experiences")
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateExperiences(List<ExperienceTO> experiences) {
        return Response.ok(cvBC.updateExperiences(experiences, httpHeaders.getHeaderString(CURRENT_USER_EMAIL))).build();
    }

    @PUT
    @Path("languages")
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLanguages(List<LanguageTO> language) {
        return Response.ok(cvBC.updateLanguages(language, httpHeaders.getHeaderString(CURRENT_USER_EMAIL))).build();
    }
}
