package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.ResumeMapper;
import org.helmut.profile.business.model.ResumeTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResumeBCTest {

    @InjectMocks
    private ResumeBC resumeBC;

    @Mock
    private ResumeMapper resumeMapper;

    @Mock
    private CvRepository cvRepository;

    @Test
    void getByEmail() {
        String email = "mail@mail.com";
        CVEntity cvEntity = new CVEntity();
        doReturn(Collections.singletonList(cvEntity)).when(cvRepository).findByProperty("userEntity.email", email);
        ResumeTO mappedResumeTO = new ResumeTO();
        doReturn(mappedResumeTO).when(resumeMapper).mapToTO(cvEntity);

        ResumeTO resumeTO = resumeBC.getByEmail(email);

        assertSame(resumeTO, mappedResumeTO);
        verify(cvRepository, times(1)).findByProperty("userEntity.email", email);
        verify(resumeMapper, times(1)).mapToTO(cvEntity);
    }

    @Test
    void update() {
        ResumeTO resumeTO = new ResumeTO();
        UserTO userTO = new UserTO();
        userTO.setEmail("mail@mail.com");
        resumeTO.setUserTO(userTO);
        resumeTO.setAbout("Long about");
        CVEntity cvEntity = new CVEntity();
        cvEntity.setLongAbout("LLong about");
        doReturn(Collections.singletonList(cvEntity)).when(cvRepository)
                .findByProperty("userEntity.email", resumeTO.getUserTO().getEmail());

        resumeBC.update(resumeTO);

        assertEquals(cvEntity.getLongAbout(), resumeTO.getAbout());
        verify(cvRepository, times(1)).findByProperty("userEntity.email", resumeTO.getUserTO().getEmail());
        verify(cvRepository, times(1)).update(cvEntity);
    }
}