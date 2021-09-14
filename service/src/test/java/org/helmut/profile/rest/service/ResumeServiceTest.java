package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.ResumeBC;
import org.helmut.profile.common.model.ResumeTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResumeServiceTest {

    @InjectMocks
    private ResumeService resumeService;

    @Mock
    private ResumeBC resumeBC;

    @Test
    void getByEmail() {
        String email = "mail@mail.com";
        ResumeTO resumeFromBC = new ResumeTO();
        doReturn(resumeFromBC).when(resumeBC).getByEmail(email);

        ResumeTO resumeTO = resumeService.getByEmail(email);

        assertSame(resumeFromBC, resumeTO);
        verify(resumeBC, times(1)).getByEmail(email);
    }

    @Test
    void update() {
        ResumeTO resumeTO = new ResumeTO();
        Response response = resumeService.update(resumeTO);

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        verify(resumeBC, times(1)).update(resumeTO);
    }
}