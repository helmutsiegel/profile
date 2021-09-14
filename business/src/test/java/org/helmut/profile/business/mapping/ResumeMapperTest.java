package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.*;
import org.helmut.profile.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ResumeMapperTest {

    @InjectMocks
    private ResumeMapper resumeMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ExperienceMapper experienceMapper;

    @Mock
    private CertificationMapper certificationMapper;

    @Mock
    private LanguageMapper languageMapper;

    @Test
    void mapToTO() {
        CVEntity cvEntity = new CVEntity();
        cvEntity.setLongAbout("Long about");
        UserEntity userEntity = new UserEntity();
        cvEntity.setUserEntity(userEntity);
        ExperienceEntity experienceEntity = new ExperienceEntity();
        cvEntity.setExperiences(Collections.singletonList(experienceEntity));
        CertificationEntity certificationEntity = new CertificationEntity();
        cvEntity.setCertifications(Collections.singletonList(certificationEntity));
        LanguageEntity languageEntity = new LanguageEntity();
        cvEntity.setLanguages(Collections.singletonList(languageEntity));

        UserTO returnedUserTO = new UserTO();
        doReturn(returnedUserTO).when(userMapper).mapToTO(userEntity);
        ExperienceTO returnedExperienceTO = new ExperienceTO();
        doReturn(returnedExperienceTO).when(experienceMapper).mapToTO(experienceEntity);
        CertificationTO returnedCertificationTO = new CertificationTO();
        doReturn(returnedCertificationTO).when(certificationMapper).mapToTO(certificationEntity);
        LanguageTO returnedLanguageTO = new LanguageTO();
        doReturn(returnedLanguageTO).when(languageMapper).mapToTO(languageEntity);

        ResumeTO resumeTO = resumeMapper.mapToTO(cvEntity);

        assertEquals(resumeTO.getAbout(), cvEntity.getLongAbout());
        assertSame(resumeTO.getUserTO(), returnedUserTO);
        assertSame(resumeTO.getCertifications().get(0), returnedCertificationTO);
        assertSame(resumeTO.getExperiences().get(0), returnedExperienceTO);
        assertSame(resumeTO.getLanguages().get(0), returnedLanguageTO);
    }
}