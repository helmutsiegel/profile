package org.helmut.profile.rest.auth.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.helmut.profile.rest.auth.JwsWrapper;
import org.helmut.profile.rest.auth.util.KeyGenerator;
import org.helmut.profile.rest.auth.util.TokenIssuer;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.helmut.profile.rest.auth.util.TokenIssuer.VALIDITY_LENGTH;

@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    private TokenIssuer tokenIssuer;

    @Inject
    private JwsWrapper jwsWrapper;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer".length()).trim();

            try {
                Jws<Claims> claimsJws = jwsWrapper.getClaimsJws(keyGenerator.generateKey(), token);

                if (minutesUntilExpiration(claimsJws.getBody().getExpiration()) < VALIDITY_LENGTH / 2) {
                    requestContext.getHeaders().remove(HttpHeaders.AUTHORIZATION);
                    requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenIssuer.issueToken(claimsJws.getBody().getSubject()));
                }
                responseContext.getHeaders().add(HttpHeaders.AUTHORIZATION, requestContext.getHeaderString(HttpHeaders.AUTHORIZATION));
            } catch (Exception e) {
                responseContext.getHeaders().remove(HttpHeaders.AUTHORIZATION);
            }
        }
    }

    private long minutesUntilExpiration(Date expirationDate) {
        long diffInMillis = Math.abs(expirationDate.getTime() - new Date().getTime());
        return TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }
}
