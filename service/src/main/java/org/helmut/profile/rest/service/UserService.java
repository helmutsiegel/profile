package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.UserBC;
import org.helmut.profile.business.model.LoginUserTO;
import org.helmut.profile.business.model.SignUpUserTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;
import org.helmut.profile.rest.auth.util.TokenIssuer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.helmut.profile.rest.service.Constants.CURRENT_USER;

@Path("user")
public class UserService {

    @Inject
    private UserBC userBC;

    @Inject
    private TokenIssuer tokenIssuer;

    @Context
    private HttpHeaders httpHeaders;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserTO> getAll() {
        return userBC.getAll();
    }

    @GET
    @JWTTokenNeeded
    @Path("currentUser")
    @Produces(MediaType.APPLICATION_JSON)
    public UserTO getByCurrentUser() {
        return userBC.getByUsername(httpHeaders.getHeaderString(CURRENT_USER));
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

    @GET
    @Path("search/{searchTerm}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserTO> searchUsers(@PathParam("searchTerm") String searchTerm) {
        return userBC.searchUsers(searchTerm);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(SignUpUserTO signUpUserTO) {
        try {
            userBC.signUp(signUpUserTO);
            return Response.ok(signUpUserTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e)
                    .build();
        }
    }

    @POST
    @Path("login")
    public Response login(LoginUserTO loginUserTO) {
        try {
            UserTO userTO = userBC.logIn(loginUserTO.getUsername(), loginUserTO.getPassword());
            String token = tokenIssuer.issueToken(loginUserTO.getUsername());
            return Response.ok(userTO).header(AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }
}
