package org.helmut.profile.rest.service;

import org.helmut.profile.business.UserBC;
import org.helmut.profile.model.UserTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
