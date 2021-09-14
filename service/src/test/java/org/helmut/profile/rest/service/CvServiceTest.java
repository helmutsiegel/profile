package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.CvBC;
import org.helmut.profile.common.model.CertificationTO;
import org.helmut.profile.common.model.CvTO;
import org.helmut.profile.common.model.ExperienceTO;
import org.helmut.profile.common.model.LanguageTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CvServiceTest {

    @Mock
    private CvBC cvBC;

    @Mock
    private HttpHeaders httpHeaders;

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
    @DisplayName("Update cv")
    public void updateCV() {
        CvTO cvTO = new CvTO();
        Response response = cvService.updateCV(cvTO);
        verify(cvBC, times(1)).update(cvTO);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    @DisplayName("Update experiences")
    void updateExperiences() {
        //setup
        String email = "email@mail.com";
        List<ExperienceTO> experiencesToUpdate = Collections.singletonList(new ExperienceTO());
        List<ExperienceTO> updatedExperiences = Collections.singletonList(new ExperienceTO());
        doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);
        doReturn(updatedExperiences).when(cvBC).updateExperiences(experiencesToUpdate, email);
        //Test call
        Response response = cvService.updateExperiences(experiencesToUpdate);
        //asserts
        verify(cvBC, times(1)).updateExperiences(experiencesToUpdate, email);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertSame(response.getEntity(), updatedExperiences);
    }

    @Test
    @DisplayName("Update certifications")
    void updateCertifications() {
        //setup
        String email = "email@mail.com";
        List<CertificationTO> certificationsToUpdate = Collections.singletonList(new CertificationTO());
        List<CertificationTO> updatedCertifications = Collections.singletonList(new CertificationTO());
        doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);
        doReturn(updatedCertifications).when(cvBC).updateCertifications(certificationsToUpdate, email);
        //Test call
        Response response = cvService.updateCertifications(certificationsToUpdate);
        //asserts
        verify(cvBC, times(1)).updateCertifications(certificationsToUpdate, email);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertSame(response.getEntity(), updatedCertifications);
    }

    @Test
    void updateLanguages() {
        //setup
        String email = "email@mail.com";
        List<LanguageTO> languagesToUpdate = Collections.singletonList(new LanguageTO());
        List<LanguageTO> updatedLanguages = Collections.singletonList(new LanguageTO());
        doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);
        doReturn(updatedLanguages).when(cvBC).updateLanguages(languagesToUpdate, email);
        //Test call
        Response response = cvService.updateLanguages(languagesToUpdate);
        //asserts
        verify(cvBC, times(1)).updateLanguages(languagesToUpdate, email);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertSame(response.getEntity(), updatedLanguages);
    }
}