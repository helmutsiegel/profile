package org.helmut.profile.mapping;

import org.helmut.profile.model.CvTO;
import org.helmut.profile.repository.entity.CVEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.stream.Collectors;

@RequestScoped
public class CvMapper {

    @Inject
    private UserMapper userMapper;

    @Inject
    private ExperienceMapper experienceMapper;

    @Inject
    LanguageMapper languageMapper;

    @Inject
    CertificationMapper certificationMapper;

    public CvTO mapCvTO(CVEntity entity) {
        CvTO cvTO = new CvTO();
        cvTO.setUserTO(userMapper.mapUserTO(entity.getUserEntity()));
        cvTO.setAbout(entity.getAbout());
        cvTO.setExperiences(entity.getExperiences()
                .stream()
                .map(experienceMapper::mapExperienceTO)
                .collect(Collectors.toList()));
        cvTO.setCertifications(entity.getCertifications()
                .stream()
                .map(certificationMapper::mapToTO)
                .collect(Collectors.toList()));
        cvTO.setLanguages(entity.getLanguages()
                .stream()
                .map(languageMapper::mapToTO)
                .collect(Collectors.toList()));

        return cvTO;
    }
}
