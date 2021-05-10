package edu.helmut.business;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TestBean {
    public String test() {
        return "Test bean for profile works!";
    }
}
