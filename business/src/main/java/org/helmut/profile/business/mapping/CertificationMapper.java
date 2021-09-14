package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.CertificationTO;
import org.helmut.profile.repository.entity.CertificationEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CertificationMapper {

    /**
     * Full mapping of certification
     */
    public CertificationTO mapToTO(CertificationEntity entity) {
        CertificationTO certificationTO = this.mapToSimpleTO(entity);
        certificationTO.setExpirationDate(entity.getExpirationDate());
        return certificationTO;
    }

    /**
     * Mapping for cv page
     * It contains less data than for resume page
     */
    public CertificationTO mapToSimpleTO(CertificationEntity entity) {
        CertificationTO certificationTO = new CertificationTO();
        certificationTO.setId(entity.getId());
        certificationTO.setName(entity.getName());
        certificationTO.setIssuedBy(entity.getIssuedBy());
        certificationTO.setDate(entity.getDate());
        return certificationTO;
    }

    public CertificationEntity mapToEntity(CertificationTO certificationTO) {
        CertificationEntity certificationEntity = new CertificationEntity();
        certificationEntity.setName(certificationTO.getName());
        certificationEntity.setIssuedBy(certificationTO.getIssuedBy());
        certificationEntity.setDate(certificationTO.getDate());
        certificationEntity.setExpirationDate(certificationTO.getExpirationDate());
        return certificationEntity;
    }

    public void mapUpdates(CertificationEntity certificationEntity, CertificationTO certificationTO) {
        certificationEntity.setName(certificationTO.getName());
        certificationEntity.setDate(certificationTO.getDate());
        certificationEntity.setExpirationDate(certificationTO.getExpirationDate());
        certificationEntity.setIssuedBy(certificationTO.getIssuedBy());
    }
}
