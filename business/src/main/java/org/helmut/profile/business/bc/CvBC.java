package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.CvMapper;
import org.helmut.profile.business.mapping.ExperienceMapper;
import org.helmut.profile.business.mapping.LanguageMapper;
import org.helmut.profile.business.model.CvTO;
import org.helmut.profile.business.model.ExperienceTO;
import org.helmut.profile.business.model.LanguageTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;
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

    public CvTO getByEmail(String email) {
        return cvMapper.mapCvTO(cvRepository.findByProperty("userEntity.email", email).get(0));
    }

    public void update(CvTO cvTO) {
        CVEntity cvEntity = cvRepository.findByProperty("userEntity.email", cvTO.getUserTO().getEmail()).get(0);
        cvEntity.setShortAbout(cvTO.getAbout());
        cvRepository.update(cvEntity);
    }

    public List<ExperienceTO> updateExperiences(List<ExperienceTO> experienceTOs, String email) {
        CVEntity cvEntity = cvRepository.findByUniqueProperty("userEntity.email", email);
        List<ExperienceEntity> experienceEntities = cvEntity.getExperiences();
        experienceTOs.forEach(experienceTO -> {
            if (Objects.isNull(experienceTO.getId())) {
                experienceEntities.add(this.experienceMapper.mapToEntity(experienceTO));
            } else {
                experienceEntities.stream()
                        .filter(experienceEntity -> experienceEntity.getId().equals(experienceTO.getId()))
                        .findAny()
                        .ifPresent(experienceEntity -> {
                            this.experienceMapper.updateExperience(experienceEntity, experienceTO);
                        });
            }
        });
        return cvRepository.update(cvEntity).getExperiences()
                .stream()
                .map(this.experienceMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public List<LanguageTO> updateLanguages(List<LanguageTO> languageTOS, String email) {
        CVEntity cvEntity = cvRepository.findByUniqueProperty("userEntity.email", email);
        List<LanguageEntity> languageEntities = cvEntity.getLanguages();
        List<LanguageEntity> newLanguages = new ArrayList<>();
        languageTOS.forEach(languageTO -> {
            Optional<LanguageEntity> foundLanguage = languageEntities
                    .stream()
                    .filter(languageEntity -> languageEntity.getLanguage().equals(languageTO.getLanguage()))
                    .findFirst();
            if (foundLanguage.isPresent()) {
                foundLanguage.get().setLevel(languageTO.getLevel());
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
}
