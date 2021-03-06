package org.helmut.profile.rest.auth.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.helmut.profile.common.logging.Logger;
import org.helmut.profile.common.qualifier.DatabaseLogger;
import org.helmut.profile.rest.auth.JwsWrapper;
import org.helmut.profile.rest.auth.util.KeyGenerator;

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

import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Inject
    private KeyGenerator keyGenerator;

    @Inject
    @DatabaseLogger
    private Logger logger;

    @Inject
    private JwsWrapper jwsWrapper;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            logger.info("Unauthorized!", JWTTokenNeededFilter.class);
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            Jws<Claims> claimsJws = jwsWrapper.getClaimsJws(keyGenerator.generateKey(), token);
            String subject = claimsJws.getBody().getSubject();
            requestContext.getHeaders().add(CURRENT_USER_EMAIL, subject);
        } catch (Exception e) {
            logger.info("Unauthorized!", JWTTokenNeededFilter.class);
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}