package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.CertificationTO;
import org.helmut.profile.repository.entity.CertificationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CertificationMapperTest {

    @InjectMocks
    private CertificationMapper certificationMapper;

    @Test
    public void mapToTO() {
        CertificationEntity certificationEntity = new CertificationEntity();
        certificationEntity.setId(1L);
        certificationEntity.setDate(LocalDate.now());
        certificationEntity.setIssuedBy("Issued by");
        certificationEntity.setExpirationDate(LocalDate.MIN);

        CertificationTO certificationTO = certificationMapper.mapToTO(certificationEntity);

        assertEquals(certificationTO.getId(), certificationEntity.getId());
        assertEquals(certificationTO.getName(), certificationEntity.getName());
        assertEquals(certificationTO.getIssuedBy(), certificationEntity.getIssuedBy());
        assertEquals(certificationTO.getExpirationDate(), certificationEntity.getExpirationDate());
    }

    @Test
    public void mapToEntity() {
        CertificationTO certificationTO = new CertificationTO();
        certificationTO.setName("Name");
        certificationTO.setIssuedBy("IssuedBy");
        LocalDate now = LocalDate.now();
        certificationTO.setDate(now);
        certificationTO.setExpirationDate(LocalDate.of(2009, Month.APRIL, 30));

        CertificationEntity certificationEntity = certificationMapper.mapToEntity(certificationTO);

        assertAll(
                () -> assertEquals(certificationEntity.getName(), certificationTO.getName()),
                () -> assertEquals(certificationEntity.getDate(), certificationTO.getDate()),
                () -> assertEquals(certificationEntity.getIssuedBy(), certificationTO.getIssuedBy()),
                () -> assertEquals(certificationEntity.getExpirationDate(), certificationTO.getExpirationDate()));
    }

    @Test
    public void mapUpdates() {
        CertificationTO certificationTO = new CertificationTO();
        certificationTO.setName("Name");
        certificationTO.setIssuedBy("IssuedBy");
        LocalDate now = LocalDate.now();
        certificationTO.setDate(now);
        certificationTO.setExpirationDate(LocalDate.of(2009, Month.APRIL, 30));

        CertificationEntity certificationEntity = new CertificationEntity();
        certificationEntity.setId(1L);
        certificationEntity.setName("Old name");
        certificationEntity.setIssuedBy("Old issuedBy");
        certificationEntity.setDate(LocalDate.MAX);
        certificationEntity.setExpirationDate(LocalDate.MIN);

        certificationMapper.mapUpdates(certificationEntity, certificationTO);

        assertAll(
                () -> assertEquals(certificationEntity.getId(), 1L),
                () -> assertEquals(certificationEntity.getName(), certificationTO.getName()),
                () -> assertEquals(certificationEntity.getDate(), certificationTO.getDate()),
                () -> assertEquals(certificationEntity.getIssuedBy(), certificationTO.getIssuedBy()),
                () -> assertEquals(certificationEntity.getExpirationDate(), certificationTO.getExpirationDate()));
    }
}