package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.PostBC;
import org.helmut.profile.common.model.PostTO;
import org.helmut.profile.common.validation.groups.Existing;
import org.helmut.profile.common.validation.util.ValidationUtils;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;

@Path("post")
public class PostService {

    @Inject
    private Validator validator;

    @Inject
    private PostBC postBC;

    @Inject
    private ValidationUtils<PostTO> validationUtils;

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Path("byEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PostTO> getByEmail(@PathParam("email") String email) {
        return postBC.getPostsByEmail(email);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PostTO getById(@PathParam("id") Long id) {
        return postBC.getById(id);
    }

    @POST
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newPost(PostTO postTO) {
        try {
            Set<ConstraintViolation<PostTO>> constraintViolations = validator.validate(postTO);
            if (constraintViolations.size() > 0) {
                throw new IllegalArgumentException(validationUtils.violationsToSting(constraintViolations));
            }

            postBC.newPost(postTO, httpHeaders.getHeaderString(CURRENT_USER_EMAIL));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e)
                    .build();
        }
    }

    @PUT
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePost(PostTO postTO) {
        try {
            postBC.updatePost(postTO, httpHeaders.getHeaderString(CURRENT_USER_EMAIL));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e)
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            postBC.deletePost(id, httpHeaders.getHeaderString(CURRENT_USER_EMAIL));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e)
                    .build();
        }
    }
}
