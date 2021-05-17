package org.helmut.profile.rest.service;

import org.helmut.profile.business.UserBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
@RequestScoped
public class TestService {

    @Inject
    private UserBean userBean;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String processWho() {
        return "<p>" + userBean.getById(1L) + "Test service works</p>";
    }
}
