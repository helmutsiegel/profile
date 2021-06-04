package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_CV")
public class CVEntity extends BaseEntity {

    @Column
    private String about;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cv_id")
    private List<ExperienceEntity> experiences;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cv_id")
    private List<LanguageEntity> languages;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cv_id")
    private List<CertificationEntity> certifications;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cv_id")
    private List<SkillEntity> skills;

    @OneToOne
    private UserEntity userEntity;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<ExperienceEntity> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceEntity> experiences) {
        this.experiences = experiences;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }

    public List<CertificationEntity> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationEntity> certifications) {
        this.certifications = certifications;
    }
}
