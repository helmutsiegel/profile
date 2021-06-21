package org.helmut.profile.business.model;

import java.util.List;

public class ProjectTO {
    private String name;
    private UserTO userTO;
    private List<ChapterTO> chapters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChapterTO> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterTO> chapters) {
        this.chapters = chapters;
    }

    public UserTO getUserTO() {
        return userTO;
    }

    public void setUserTO(UserTO userTO) {
        this.userTO = userTO;
    }
}
