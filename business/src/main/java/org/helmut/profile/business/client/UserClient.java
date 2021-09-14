package org.helmut.profile.business.client;

import org.helmut.profile.common.model.UserTO;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;

@RequestScoped
public class UserClient {

    private static final String HOST = "http://localhost:8080/profile/rs/";

    /**
     * Example of simple get with binding to an object
     */
    public UserTO getUserByEmail(String email) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(HOST);
        return target.path("user/userByEmail/" + email).request().get(UserTO.class);
    }

    /**
     * Example of get with binding to a list of objects
     */
    public List<UserTO> searchUser(String searchTerm) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(HOST);
        return target.path("user/search/" + searchTerm).request().get(new GenericType<List<UserTO>>() {
        });
    }
}
