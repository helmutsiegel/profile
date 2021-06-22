package org.helmut.profile.rest.auth.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TokenIssuer {

    public static long VALIDITY_LENGTH = 60L;

    @Context
    private UriInfo uriInfo;

    @Inject
    private KeyGenerator keyGenerator;

    /**
     * Issues a token for the given user
     */
    public String issueToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(VALIDITY_LENGTH).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, keyGenerator.generateKey())
                .compact();
    }
}
