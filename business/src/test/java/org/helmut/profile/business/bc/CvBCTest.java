package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.CertificationMapper;
import org.helmut.profile.business.mapping.CvMapper;
import org.helmut.profile.business.mapping.ExperienceMapper;
import org.helmut.profile.business.mapping.LanguageMapper;
import org.helmut.profile.common.model.*;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;
import org.helmut.profile.repository.entity.CertificationEntity;
import org.helmut.profile.repository.entity.ExperienceEntity;
import org.helmut.profile.repository.entity.LanguageEntity;
import org.helmut.profile.common.enums.LanguageLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CvBCTest {

    @InjectMocks
    private CvBC cvBC;

    @Mock
    private CertificationMapper certificationMapper;

    @Mock
    private ExperienceMapper experienceMapper;

    @Mock
    private CvRepository cvRepository;

    @Mock
    private CvMapper cvMapper;

    @Mock
    private LanguageMapper languageMapper;

    @Test
    @DisplayName("Cv repository should be called and the received result should be mapped and passed back")
    void getByEmail() {
        CVEntity cvEntity = new CVEntity();
        String email = "email";
        doReturn(cvEntity).when(cvRepository).findByEmail(email);
        CvTO cvTO = new CvTO();
        doReturn(cvTO).when(cvMapper).mapCvTO(cvEntity);

        assertEquals(cvBC.getByEmail(email), cvTO);
        verify(cvRepository, times(1)).findByEmail(email);
        verify(cvMapper, times(1)).mapCvTO(cvEntity);
    }

    @Test
    @DisplayName("Update cv")
    void update() {
        CvTO cvTO = new CvTO();
        UserTO userTO = new UserTO.Builder().build();
        userTO.setEmail("email");
        cvTO.setUserTO(userTO);
        cvTO.setAbout("Short about");
        CVEntity cvEntity = new CVEntity();
        doReturn(cvEntity).when(cvRepository).findByEmail(cvTO.getUserTO().getEmail());

        cvBC.update(cvTO);

        assertEquals(cvEntity.getShortAbout(), cvTO.getAbout());
        verify(cvRepository, times(1)).findByEmail(cvTO.getUserTO().getEmail());
        verify(cvRepository, times(1)).update(cvEntity);
    }

    @Test
    @DisplayName("Update experiences")
    void updateExperiences() {
        ArrayList<ExperienceTO> experienceTOS = createExperienceTOsForUpdate();
        CVEntity cvEntity = createCVEntityForUpdateExperiences();
        String email = "email@mail.com";
        doReturn(cvEntity).when(cvRepository).findByEmail(email);
        doReturn(cvEntity).when(cvRepository).update(cvEntity);

        cvBC.updateExperiences(experienceTOS, email);

        verify(cvRepository, times(1)).findByEmail(email);
        verify(experienceMapper, times(1)).mapToEntity(any());
        verify(experienceMapper, times(2)).mapToTO(any());
        verify(experienceMapper, times(1)).mapUpdates(any(), any());
        assertEquals(cvEntity.getExperiences().size(), 2);
    }

    @Test
    @DisplayName("Update languages")
    void updateLanguages() {
        CVEntity cvEntity = createCVEntityForUpdateLanguages();
        String email = "email@mail.com";

        doReturn(cvEntity).when(cvRepository).findByEmail(email);
        doReturn(cvEntity).when(cvRepository).update(cvEntity);

        ArrayList<LanguageTO> languageTOS = new ArrayList<>();
        languageTOS.add(new LanguageTO("German", LanguageLevel.ADVANCED));
        languageTOS.add(new LanguageTO("English", LanguageLevel.BEGINNER));

        cvBC.updateLanguages(languageTOS, email);

        verify(cvRepository, times(1)).findByEmail(email);
        verify(languageMapper, times(1)).mapToEntity(any());
        verify(languageMapper, times(2)).mapToTO(any());

    }

    @Test
    @DisplayName("Update certifications")
    void updateCertifications() {
        ArrayList<CertificationTO> certificationTOS = createCertificationTOSForUpdate();
        CVEntity cvEntity = createCVEntityForUpdateCertifications();
        String email = "email@mail.com";
        doReturn(cvEntity).when(cvRepository).findByEmail(email);
        doReturn(cvEntity).when(cvRepository).update(cvEntity);

        cvBC.updateCertifications(certificationTOS, email);

        verify(cvRepository, times(1)).findByEmail(email);
        verify(certificationMapper, times(1)).mapToEntity(any());
        verify(certificationMapper, times(2)).mapToTO(any());
        verify(certificationMapper, times(1)).mapUpdates(any(), any());
        assertEquals(cvEntity.getCertifications().size(), 2);
    }

    private CVEntity createCVEntityForUpdateLanguages() {
        CVEntity cvEntity = new CVEntity();
        ArrayList<LanguageEntity> languages = new ArrayList<>();
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguage("German");
        languageEntity.setLevel(LanguageLevel.INTERMEDIATE);
        languages.add(languageEntity);
        cvEntity.setLanguages(languages);
        return cvEntity;
    }

    private CVEntity createCVEntityForUpdateExperiences() {
        CVEntity cvEntity = new CVEntity();
        ArrayList<ExperienceEntity> certifications = new ArrayList<>();
        ExperienceEntity experienceEntity = new ExperienceEntity();
        experienceEntity.setId(1L);
        certifications.add(experienceEntity);
        cvEntity.setExperiences(certifications);
        return cvEntity;
    }

    private ArrayList<ExperienceTO> createExperienceTOsForUpdate() {
        ArrayList<ExperienceTO> experienceTOS = new ArrayList<>();
        ExperienceTO experienceTO1 = new ExperienceTO();
        experienceTO1.setId(1L);
        experienceTOS.add(experienceTO1);
        experienceTOS.add(new ExperienceTO());
        return experienceTOS;
    }

    private CVEntity createCVEntityForUpdateCertifications() {
        CVEntity cvEntity = new CVEntity();
        ArrayList<CertificationEntity> certifications = new ArrayList<>();
        CertificationEntity certificationEntity = new CertificationEntity();
        certificationEntity.setId(1L);
        certifications.add(certificationEntity);
        cvEntity.setCertifications(certifications);
        return cvEntity;
    }

    private ArrayList<CertificationTO> createCertificationTOSForUpdate() {
        ArrayList<CertificationTO> certificationTOS = new ArrayList<>();
        CertificationTO certificationTO1 = new CertificationTO();
        certificationTO1.setId(1L);
        certificationTOS.add(certificationTO1);
        certificationTOS.add(new CertificationTO());
        return certificationTOS;
    }
}