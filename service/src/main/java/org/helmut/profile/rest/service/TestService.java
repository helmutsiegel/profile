package org.helmut.profile.rest.service;

import org.helmut.profile.business.TestBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
@RequestScoped
public class TestService {

    @Inject
    private TestBean testBean;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String processWho() {
        return "<p>" + testBean.testBean() + "Test service works</p>";
    }
}
