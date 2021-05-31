package org.helmut.profile.rest.service;

import org.helmut.profile.business.UserBC;
import org.helmut.profile.model.SignUpUserTO;
import org.helmut.profile.model.UserTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
@RequestScoped
public class UserService {

    @Inject
    private UserBC userBC;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserTO> getAll() {
        return userBC.getAll();
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserTO getByUsername(@PathParam("username") String username) {
        return userBC.getByUsername(username);
    }

    @GET
    @Path("exists/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userExists(@PathParam("username") String username) {
        return userBC.existsUser(username) ? Response.ok().build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(SignUpUserTO signUpUserTO) {
        userBC.signUp(signUpUserTO);
        return Response.ok(signUpUserTO).build();
    }
}
