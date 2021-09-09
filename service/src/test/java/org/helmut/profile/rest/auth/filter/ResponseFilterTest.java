package org.helmut.profile.rest.auth.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.helmut.profile.rest.auth.JwsWrapper;
import org.helmut.profile.rest.auth.util.KeyGenerator;
import org.helmut.profile.rest.auth.util.TokenIssuer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResponseFilterTest {

    @InjectMocks
    private ResponseFilter responseFilter;

    @Spy
    private KeyGenerator keyGenerator;

    @Mock
    private JwsWrapper jwsWrapper;

    @Mock
    private TokenIssuer tokenIssuer;

    @Test
    @DisplayName("Token issued successful")
    void filterSuccessful() {
        String authorizationHeader = "Bearer token";
        ContainerRequestContext requestContext = mock(ContainerRequestContext.class);
        ContainerResponseContext responseContext = mock(ContainerResponseContext.class);
        doReturn(authorizationHeader).when(requestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
        Jws<Claims> jws = mock(Jws.class);
        Claims claims = mock(Claims.class);
        doReturn(new Date()).when(claims).getExpiration();
        doReturn("subject").when(claims).getSubject();
        doReturn(claims).when(jws).getBody();
        doReturn(jws).when(jwsWrapper).getClaimsJws(any(), eq("token"));
        doReturn(new MultivaluedHashMap<String, Object>()).when(responseContext).getHeaders();
        doReturn(new MultivaluedHashMap<String, Object>()).when(requestContext).getHeaders();
        doReturn("new token").when(tokenIssuer).issueToken("subject");

        responseFilter.filter(requestContext, responseContext);

        verify(keyGenerator, times(1)).generateKey();
        verify(requestContext, times(2)).getHeaderString(HttpHeaders.AUTHORIZATION);
        verify(responseContext, times(1)).getHeaders();
        verify(jwsWrapper, times(1)).getClaimsJws(any(), any());
        verify(tokenIssuer, times(1)).issueToken("subject");
    }

    @Test
    @DisplayName("Token issue failed")
    void filterTokenIssueFailed() {
        String authorizationHeader = "Bearer token";
        ContainerRequestContext requestContext = mock(ContainerRequestContext.class);
        ContainerResponseContext responseContext = mock(ContainerResponseContext.class);
        doReturn(authorizationHeader).when(requestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
        doReturn(new MultivaluedHashMap<String, Object>()).when(responseContext).getHeaders();

        responseFilter.filter(requestContext, responseContext);

        verify(keyGenerator, times(1)).generateKey();
        verify(requestContext, times(1)).getHeaderString(HttpHeaders.AUTHORIZATION);
        verify(responseContext, times(1)).getHeaders();
        verify(jwsWrapper, times(1)).getClaimsJws(any(), any());
        verify(tokenIssuer, never()).issueToken(any());
    }
}