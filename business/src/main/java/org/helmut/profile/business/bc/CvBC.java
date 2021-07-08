package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.CvMapper;
import org.helmut.profile.business.mapping.ExperienceMapper;
import org.helmut.profile.business.model.CvTO;
import org.helmut.profile.business.model.ExperienceTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.entity.CVEntity;
import org.helmut.profile.repository.entity.ExperienceEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestScoped
public class CvBC {

    @Inject
    private CvRepository cvRepository;

    @Inject
    private CvMapper cvMapper;

    @Inject
    private ExperienceMapper experienceMapper;

    public CvTO getByEmail(String email) {
        return cvMapper.mapCvTO(cvRepository.findByProperty("userEntity.email", email).get(0));
    }

    public void update(CvTO cvTO) {
        CVEntity cvEntity = cvRepository.findByProperty("userEntity.email", cvTO.getUserTO().getEmail()).get(0);
        cvEntity.setShortAbout(cvTO.getAbout());
        cvRepository.update(cvEntity);
    }

    public void updateExperiences(List<ExperienceTO> experienceTOs, String email) {
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
        cvRepository.update(cvEntity);
    }
}
