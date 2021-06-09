package org.helmut.profile.rest.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.helmut.profile.business.UserBC;
import org.helmut.profile.model.LoginUserTO;
import org.helmut.profile.model.SignUpUserTO;
import org.helmut.profile.model.UserTO;
import org.helmut.profile.rest.auth.filter.JWTTokenNeeded;
import org.helmut.profile.rest.auth.util.KeyGenerator;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.helmut.profile.rest.service.Constants.CURRENT_USER;

@Path("user")
public class UserService {

    @Inject
    private UserBC userBC;

    @Inject
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

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
    public Response authenticateUser(LoginUserTO loginUserTO) {
        try {
            UserTO userTO = userBC.logIn(loginUserTO.getUsername(), loginUserTO.getUsername());
            String token = issueToken(loginUserTO.getUsername());
            return Response.ok(userTO).header(AUTHORIZATION, "Bearer " + token).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, keyGenerator.generateKey())
                .compact();
    }
}
