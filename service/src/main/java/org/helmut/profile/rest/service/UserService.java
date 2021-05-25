package org.helmut.profile.rest.service;

import org.helmut.profile.business.UserBC;
import org.helmut.profile.model.UserTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

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
    public Response userExists(@PathParam("username") String username) {
        return Objects.nonNull(userBC.getByUsername(username)) ?
                Response.ok().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
