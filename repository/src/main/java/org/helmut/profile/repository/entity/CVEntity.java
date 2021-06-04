package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_CV")
public class CVEntity extends BaseEntity {

    @Column(name = "short_about")
    private String shortAbout;

    @Column(name = "long_about", length = 2000)
    private String longAbout;

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

    public String getShortAbout() {
        return shortAbout;
    }

    public void setShortAbout(String shortAbout) {
        this.shortAbout = shortAbout;
    }

    public String getLongAbout() {
        return longAbout;
    }

    public void setLongAbout(String longAbout) {
        this.longAbout = longAbout;
    }

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
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
