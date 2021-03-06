package org.helmut.profile.common.model;

import java.util.List;

public class ResumeTO {
    private String about;
    private UserTO userTO;
    private List<ExperienceTO> experiences;
    private List<LanguageTO> languages;
    private List<CertificationTO> certifications;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public UserTO getUserTO() {
        return userTO;
    }

    public void setUserTO(UserTO userTO) {
        this.userTO = userTO;
    }

    public List<ExperienceTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceTO> experiences) {
        this.experiences = experiences;
    }

    public List<LanguageTO> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageTO> languages) {
        this.languages = languages;
    }

    public List<CertificationTO> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationTO> certifications) {
        this.certifications = certifications;
    }
}
