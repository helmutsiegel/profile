package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.CvMapper;
import org.helmut.profile.business.model.CvTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CvBC {

    @Inject
    private CvRepository cvRepository;

    @Inject
    private CvMapper cvMapper;

    public CvTO getByEmail(String email) {
        return cvMapper.mapCvTO(cvRepository.findByProperty("userEntity.email", email).get(0));
    }

    public void update(CvTO cvTO) {
        CVEntity cvEntity = cvRepository.findByProperty("userEntity.email", cvTO.getUserTO().getEmail()).get(0);
        cvEntity.setShortAbout(cvTO.getAbout());
        cvRepository.update(cvEntity);
    }
}
