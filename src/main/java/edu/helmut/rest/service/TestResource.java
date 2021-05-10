package edu.helmut.rest.service;

import edu.helmut.business.TestBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("test")
@RequestScoped
public class TestResource {

    @Inject
    private TestBean testBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String processWho() {
        return testBean.test();
    }

}
