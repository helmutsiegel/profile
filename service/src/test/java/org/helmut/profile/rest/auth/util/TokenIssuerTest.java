package org.helmut.profile.rest.auth.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.UriInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TokenIssuerTest {

    @Mock
    private KeyGenerator keyGenerator;

    @Mock
    private UriInfo uriInfo;

    @InjectMocks
    private TokenIssuer tokenIssuer;

    @Test
    void testIssueToken() {

    }
}