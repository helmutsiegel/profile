package org.helmut.profile.rest.service;

import org.helmut.profile.business.bci.UserBCI;
import org.helmut.profile.business.client.UserClient;
import org.helmut.profile.business.model.ChangePasswordTO;
import org.helmut.profile.business.model.LoginUserTO;
import org.helmut.profile.business.model.SignUpUserTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.common.logging.Logger;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;
import org.helmut.profile.rest.auth.util.TokenIssuer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;

@Path("user")
public class UserService {

    @Inject
    private UserClient userClient;

    @Inject
    private Logger logger;

    @Inject
    private UserBCI userBC;

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
        return userBC.getByEmail(httpHeaders.getHeaderString(CURRENT_USER_EMAIL));
    }

    /**
     * The methods getByEmail and getUserByEmail are same.
     * They are created to show how jersey client works
     * {@link UserClient}
     */
    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserTO getByEmail(@PathParam("email") String email) {
        return userClient.getUserByEmail(email);
    }

    @GET
    @Path("userByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserTO getUserByEmail(@PathParam("email") String email) {
        return userBC.getByEmail(email);
    }

    @GET
    @Path("exists/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userExists(@PathParam("email") String email) {
        return userBC.existsUser(email) ? Response.ok().build() : Response.status(Response.Status.NOT_FOUND).build();
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
            UserTO userTO = userBC.logIn(loginUserTO.getEmail(), loginUserTO.getPassword());
            String token = tokenIssuer.issueToken(loginUserTO.getEmail());
            return Response.ok(userTO).header(AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @POST
    @JWTTokenNeeded
    @Path("changePassword")
    public Response changePassword(ChangePasswordTO changePasswordTO) {
        if (!changePasswordTO.getNewPassword1().equals(changePasswordTO.getNewPassword2())) {
            return Response.status(BAD_REQUEST).entity("The new passwords does not match!").build();
        }
        try {
            String currentUserEmail = httpHeaders.getHeaderString(CURRENT_USER_EMAIL);
            changePasswordTO.setEmail(currentUserEmail);
            userBC.changePassword(changePasswordTO);
            return Response.ok().build();
        } catch (Exception e) {
            logger.info("Password could not change", UserService.class);
            return Response.status(BAD_REQUEST).entity("Current password is incorrect!").build();
        }
    }

    @PUT
    @JWTTokenNeeded
    public Response updateUser(UserTO userTO) {
        String currentUserEmail = httpHeaders.getHeaderString(CURRENT_USER_EMAIL);
        if (!userTO.getEmail().equals(currentUserEmail)) {
            return Response.status(UNAUTHORIZED).build();
        }
        return Response.ok(userBC.updateUser(userTO)).build();
    }
}
