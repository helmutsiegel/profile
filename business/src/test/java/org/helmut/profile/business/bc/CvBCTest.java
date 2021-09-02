package org.helmut.profile.business.bc;

import org.helmut.profile.business.bc.CvBC;
import org.helmut.profile.business.mapping.CertificationMapper;
import org.helmut.profile.business.mapping.CvMapper;
import org.helmut.profile.business.model.CertificationTO;
import org.helmut.profile.business.model.CvTO;
import org.helmut.profile.business.model.UserTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;
import org.helmut.profile.repository.entity.CertificationEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
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
    private CvRepository cvRepository;

    @Mock
    private CvMapper cvMapper;

    @Test
    void getByEmail() {
        CVEntity cvEntity = new CVEntity();
        String email = "email";
        doReturn(cvEntity).when(cvRepository).findByEmail(email);
        CvTO cvTO = new CvTO();
        doReturn(cvTO).when(cvMapper).mapCvTO(cvEntity);

        assertEquals(cvBC.getByEmail(email), cvTO);
        verify(cvRepository, times(1)).findByEmail(email);
        verify(cvMapper,times(1)).mapCvTO(cvEntity);
    }

    @Test
    @DisplayName("Update cv")
    void update() {
        CvTO cvTO = new CvTO();
        UserTO userTO = new UserTO();
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
    void updateExperiences() {
    }

    @Test
    void updateLanguages() {
    }

    @Test
    @DisplayName("Update certifications")
    void updateCertifications() {
        ArrayList<CertificationTO> certificationTOS = createCertificationTOSForUpdate();
        CVEntity cvEntity = createCVEntityForUpdate();
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

    private CVEntity createCVEntityForUpdate() {
        CVEntity cvEntity = new CVEntity();
        ArrayList<CertificationEntity> certifications = new ArrayList<>();
        CertificationEntity certificationEntity = new CertificationEntity();
        certificationEntity.setId(1L);
        certificationEntity.setName("Cert1");
        certificationEntity.setExpirationDate(LocalDate.now());
        certifications.add(certificationEntity);
        cvEntity.setCertifications(certifications);
        return cvEntity;
    }

    private ArrayList<CertificationTO> createCertificationTOSForUpdate() {
        ArrayList<CertificationTO> certificationTOS = new ArrayList<>();
        CertificationTO certificationTO1 = new CertificationTO();
        certificationTO1.setId(1L);
        certificationTO1.setName("Cert1");
        certificationTO1.setDate(LocalDate.of(2021, Month.JANUARY, 1));
        certificationTO1.setExpirationDate(null);
        certificationTO1.setIssuedBy("Cert1Issuer");

        CertificationTO certificationTO2 = new CertificationTO();
        certificationTO2.setName("Cert2");
        certificationTO2.setDate(LocalDate.of(2021, Month.JANUARY, 1));
        certificationTO2.setExpirationDate(LocalDate.of(2022, Month.FEBRUARY, 28));
        certificationTO2.setIssuedBy("Cert2Issuer");

        certificationTOS.add(certificationTO1);
        certificationTOS.add(certificationTO2);
        return certificationTOS;
    }
}