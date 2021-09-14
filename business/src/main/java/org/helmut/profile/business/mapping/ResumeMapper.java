package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.ResumeTO;
import org.helmut.profile.repository.entity.CVEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.stream.Collectors;

@RequestScoped
public class ResumeMapper {

    @Inject
    private UserMapper userMapper;

    @Inject
    private ExperienceMapper experienceMapper;

    @Inject
    LanguageMapper languageMapper;

    @Inject
    CertificationMapper certificationMapper;

    public ResumeTO mapToTO(CVEntity entity){
        ResumeTO resumeTO = new ResumeTO();
        resumeTO.setUserTO(userMapper.mapToTO(entity.getUserEntity()));
        resumeTO.setAbout(entity.getLongAbout());
        resumeTO.setExperiences(entity.getExperiences()
                .stream()
                .map(experienceMapper::mapToTO)
                .collect(Collectors.toList()));
        resumeTO.setCertifications(entity.getCertifications()
                .stream()
                .map(certificationMapper::mapToTO)
                .collect(Collectors.toList()));
        resumeTO.setLanguages(entity.getLanguages()
                .stream()
                .map(languageMapper::mapToTO)
                .collect(Collectors.toList()));
        return resumeTO;
    }
}
