package org.helmut.profile.model;

import java.util.List;

public class CvTO {
    private UserTO userTO;
    private List<ExperienceTO> experiences;

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
}
