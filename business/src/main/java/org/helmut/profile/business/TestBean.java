package org.helmut.profile.business;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TestBean {

    public String testBean() {
        return "Test bean works!";
    }

}
