package org.helmut.profile.business.util;

import javax.enterprise.context.RequestScoped;
import java.security.MessageDigest;
import java.util.Base64;

@RequestScoped
public class PasswordUtils {
    public String digestPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            return new String(Base64.getEncoder().encode(passwordDigest));
        } catch (Exception e) {
            throw new RuntimeException("Exception encoding password", e);
        }
    }
}
