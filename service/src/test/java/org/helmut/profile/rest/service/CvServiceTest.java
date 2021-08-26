package org.helmut.profile.rest.service;

import org.checkerframework.checker.units.qual.C;
import org.helmut.profile.business.bc.CvBC;
import org.helmut.profile.business.model.CvTO;
import org.junit.jupiter.api.DisplayName;
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
class CvServiceTest {

    @Mock
    private CvBC cvBC;

    @InjectMocks
    private CvService cvService;

    @Test
    @DisplayName("Get cv by email")
    public void getByEmail() {
        //setup
        String email = "email@mail.com";
        CvTO cvTO = new CvTO();
        //test call
        doReturn(cvTO).when(cvBC).getByEmail(email);
        //asserts
        assertSame(cvService.getByEmail(email), cvTO);
    }

    @Test
    public void updateCV() {
        CvTO cvTO = new CvTO();
        Response response = cvService.updateCV(cvTO);
        verify(cvBC, times(1)).update(cvTO);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

}