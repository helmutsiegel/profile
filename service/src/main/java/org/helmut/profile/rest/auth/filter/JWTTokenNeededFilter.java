package org.helmut.profile.rest.auth.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.helmut.profile.rest.auth.util.KeyGenerator;
import org.helmut.profile.rest.auth.util.TokenIssuer;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.helmut.profile.rest.auth.util.TokenIssuer.VALIDITY_LENGTH;
import static org.helmut.profile.rest.service.Constants.CURRENT_USER;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    TokenIssuer tokenIssuer;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            Key key = keyGenerator.generateKey();
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            String subject = claimsJws.getBody().getSubject();
            requestContext.getHeaders().add(CURRENT_USER, subject);
            if (minutesUntilExpiration(claimsJws.getBody().getExpiration()) < VALIDITY_LENGTH) {
                requestContext.getHeaders().remove(HttpHeaders.AUTHORIZATION);
                requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenIssuer.issueToken(subject));
            }

        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private long minutesUntilExpiration(Date expirationDate) {
        long diffInMillis = Math.abs(expirationDate.getTime() - new Date().getTime());
        return TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }
}