package org.helmut.profile.common.validation.constraintvalidators;

import org.helmut.profile.common.util.ProfileUtils;
import org.helmut.profile.common.validation.constraints.URL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;

public class URLValidator implements ConstraintValidator<URL, String> {

    private String protocol;
    private String host;
    private int port;

    @Override
    public void initialize(URL url) {
        this.protocol = url.protocol();
        this.host = url.host();
        this.port = url.port();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (ProfileUtils.isNullOrEmpty(value)) {
            return true;
        }
        java.net.URL url;
        try {
            url = new java.net.URL(value);
        } catch (MalformedURLException e) {
            return false;
        }
        if (!ProfileUtils.isNullOrEmpty(protocol) && !url.getProtocol().equals(protocol)) {
            return false;
        }
        if (!ProfileUtils.isNullOrEmpty(host) && !url.getHost().equals(host)) {
            return false;
        }
        if (port != -1 && url.getPort() != port) {
            return false;
        }
        return true;
    }
}
