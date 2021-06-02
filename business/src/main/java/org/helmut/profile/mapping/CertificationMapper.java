package org.helmut.profile.mapping;

import org.helmut.profile.model.CertificationTO;
import org.helmut.profile.repository.entity.CertificationEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CertificationMapper {
    public CertificationTO mapToTO(CertificationEntity entity) {
        CertificationTO certificationTO = new CertificationTO();
        certificationTO.setName(entity.getName());
        certificationTO.setIssuedBy(entity.getIssuedBy());
        certificationTO.setDate(entity.getDate());
        certificationTO.setExpirationDate(entity.getExpirationDate());
        return certificationTO;
    }
}
