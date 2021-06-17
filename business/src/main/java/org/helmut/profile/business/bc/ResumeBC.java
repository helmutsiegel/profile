package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.ResumeMapper;
import org.helmut.profile.business.model.ResumeTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ResumeBC {

    @Inject
    private ResumeMapper mapper;

    @Inject
    private CvRepository cvRepository;

    public ResumeTO getByUsername(String username) {
        return mapper.mapToTO(cvRepository.findByProperty("userEntity.username", username).get(0));
    }

    public void update(ResumeTO resumeTO) {
        CVEntity cvEntity = cvRepository.findByProperty("userEntity.username", resumeTO.getUserTO().getUsername()).get(0);
        cvEntity.setLongAbout(resumeTO.getAbout());
        cvRepository.update(cvEntity);
    }
}
