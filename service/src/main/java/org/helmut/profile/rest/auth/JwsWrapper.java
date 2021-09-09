package org.helmut.profile.rest.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.Key;

/**
 * Wrapper class for Jws. Created to facilitate testing
 */
public class JwsWrapper {

    public Jws<Claims> getClaimsJws(Key key, String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }
}
