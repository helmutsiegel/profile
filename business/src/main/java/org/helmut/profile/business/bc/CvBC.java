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

    public CvTO getByUsername(String username) {
        return cvMapper.mapCvTO(cvRepository.findByProperty("userEntity.username", username).get(0));
    }

    public void update(CvTO cvTO) {
        CVEntity cvEntity = cvRepository.findByProperty("userEntity.username", cvTO.getUserTO().getUsername()).get(0);
        cvEntity.setShortAbout(cvTO.getAbout());
        cvRepository.update(cvEntity);
    }
}
