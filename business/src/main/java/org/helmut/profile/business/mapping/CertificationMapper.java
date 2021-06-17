package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.CertificationTO;
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
    public CertificationTO mapToSimpleTO(CertificationEntity entity){
        CertificationTO certificationTO = new CertificationTO();
        certificationTO.setName(entity.getName());
        certificationTO.setIssuedBy(entity.getIssuedBy());
        certificationTO.setDate(entity.getDate());
        return certificationTO;
    }
}
