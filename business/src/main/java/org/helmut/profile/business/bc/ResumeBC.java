package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.ResumeMapper;
import org.helmut.profile.common.model.ResumeTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ResumeBC {

    @Inject
    private ResumeMapper mapper;

    @Inject
    private CvRepository cvRepository;

    public ResumeTO getByEmail(String email) {
        return mapper.mapToTO(cvRepository.findByProperty("userEntity.email", email).get(0));
    }

    public void update(ResumeTO resumeTO) {
        CVEntity cvEntity = cvRepository.findByProperty("userEntity.email", resumeTO.getUserTO().getEmail()).get(0);
        cvEntity.setLongAbout(resumeTO.getAbout());
        cvRepository.update(cvEntity);
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("ResumeBC PostConstruct");
    }

    @PreDestroy
    private void preConstruct() {
        System.out.println("ResumeBC PreDestroy");
    }
}
