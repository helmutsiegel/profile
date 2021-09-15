package org.helmut.profile.rest.auth.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TokenIssuerTest {

    @Spy
    private KeyGenerator keyGenerator;

    @Mock
    private UriInfo uriInfo;

    @InjectMocks
    private TokenIssuer tokenIssuer;

    @Test
    void testIssueToken() {
        URI uri = URI.create("uri");
        doReturn(uri).when(uriInfo).getAbsolutePath();

        assertNotNull(tokenIssuer.issueToken("Subject"));
    }
}