package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.CertificationMapper;
import org.helmut.profile.business.mapping.CvMapper;
import org.helmut.profile.business.mapping.ExperienceMapper;
import org.helmut.profile.business.mapping.LanguageMapper;
import org.helmut.profile.common.model.CertificationTO;
import org.helmut.profile.common.model.CvTO;
import org.helmut.profile.common.model.ExperienceTO;
import org.helmut.profile.common.model.LanguageTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;
import org.helmut.profile.repository.entity.CertificationEntity;
import org.helmut.profile.repository.entity.ExperienceEntity;
import org.helmut.profile.repository.entity.LanguageEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class CvBC {

    @Inject
    private CvRepository cvRepository;

    @Inject
    private CvMapper cvMapper;

    @Inject
    private ExperienceMapper experienceMapper;

    @Inject
    private LanguageMapper languageMapper;

    @Inject
    private CertificationMapper certificationMapper;

    public CvTO getByEmail(String email) {
        return cvMapper.mapCvTO(cvRepository.findByEmail(email));
    }

    public void update(CvTO cvTO) {
        CVEntity cvEntity = cvRepository.findByEmail(cvTO.getUserTO().getEmail());
        cvEntity.setShortAbout(cvTO.getAbout());
        cvRepository.update(cvEntity);
    }

    public List<ExperienceTO> updateExperiences(List<ExperienceTO> experienceTOs, String email) {
        CVEntity cvEntity = cvRepository.findByEmail(email);
        List<ExperienceEntity> experienceEntities = cvEntity.getExperiences();
        experienceTOs.forEach(experienceTO -> {
            if (Objects.isNull(experienceTO.getId())) {
                experienceEntities.add(this.experienceMapper.mapToEntity(experienceTO));
            } else {
                experienceEntities.stream()
                        .filter(experienceEntity -> experienceEntity.getId().equals(experienceTO.getId()))
                        .findAny()
                        .ifPresent(experienceEntity -> {
                            this.experienceMapper.mapUpdates(experienceEntity, experienceTO);
                        });
            }
        });
        return cvRepository.update(cvEntity).getExperiences()
                .stream()
                .map(this.experienceMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public List<LanguageTO> updateLanguages(List<LanguageTO> languageTOS, String email) {
        CVEntity cvEntity = cvRepository.findByEmail(email);
        List<LanguageEntity> languageEntities = cvEntity.getLanguages();
        List<LanguageEntity> newLanguages = new ArrayList<>();
        languageTOS.forEach(languageTO -> {
            Optional<LanguageEntity> foundLanguage = languageEntities
                    .stream()
                    .filter(languageEntity -> languageEntity.getLanguage().equals(languageTO.getLanguage()))
                    .findFirst();
            if (foundLanguage.isPresent()) {
                languageMapper.mapUpdates(foundLanguage.get(), languageTO);
            } else {
                newLanguages.add(languageMapper.mapToEntity(languageTO));
            }
        });
        languageEntities.addAll(newLanguages);
        return cvRepository.update(cvEntity).getLanguages()
                .stream()
                .map(this.languageMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public List<CertificationTO> updateCertifications(List<CertificationTO> certificationTOS, String email) {
        CVEntity cvEntity = cvRepository.findByEmail(email);
        List<CertificationEntity> certificationEntities = cvEntity.getCertifications();
        List<CertificationEntity> newCertifications = new ArrayList<>();
        certificationTOS.forEach(certificationTO -> {
            Optional<CertificationEntity> foundCertificationEntity = certificationEntities
                    .stream()
                    .filter(certificationEntity -> certificationEntity.getId().equals(certificationTO.getId()))
                    .findFirst();
            if (foundCertificationEntity.isPresent()) {
                certificationMapper.mapUpdates(foundCertificationEntity.get(), certificationTO);
            } else {
                newCertifications.add(certificationMapper.mapToEntity(certificationTO));
            }
        });
        certificationEntities.addAll(newCertifications);
        return cvRepository.update(cvEntity).getCertifications()
                .stream()
                .map(this.certificationMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
