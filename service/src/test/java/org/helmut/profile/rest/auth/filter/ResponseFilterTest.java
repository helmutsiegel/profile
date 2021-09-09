package org.helmut.profile.rest.auth.filter;

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
import javax.ws.rs.core.UriInfo;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResponseFilterTest {

    @InjectMocks
    private ResponseFilter responseFilter;

    @Spy
    private KeyGenerator keyGenerator;

    @Spy
    private TokenIssuer tokenIssuer;

    @Test
    void filter() {
        ContainerRequestContext requestContext = mock(ContainerRequestContext.class);
        ContainerResponseContext responseContext = mock(ContainerResponseContext.class);

        String authorizationHeader = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxtdXQuc2llZ2VsLmRldkBnbWFpbC5jb20iLCJpc3MiOiJodHRwOi8vdGhlLXByb2ZpbGUubmV0L3JzL3VzZXIvbG9naW4iLCJpYXQiOjE2MzExOTAwMTUsImV4cCI6MTYzMTE5MzYxNX0.XHBOGbhObIGtjAcDLNFKkMTTwpVGF_FgRap9J2ge82DT4vhdIQBFv9skNs2BaE2R4SLMnYWnR5n1b6oIQdEd4w";
        doReturn(authorizationHeader).when(requestContext).getHeaderString(HttpHeaders.AUTHORIZATION);
        doReturn(new MultivaluedHashMap<String, Object>()).when(responseContext).getHeaders();

        responseFilter.filter(requestContext, responseContext);

        verify(keyGenerator, times(1)).generateKey();
        verify(requestContext, times(2)).getHeaderString(HttpHeaders.AUTHORIZATION);
        verify(responseContext, times(1)).getHeaders();
    }
}