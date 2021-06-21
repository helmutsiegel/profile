package org.helmut.profile.business.model;

import java.util.List;

public class ChapterTO {
    private String title;
    private List<SectionTO> sections;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SectionTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionTO> sections) {
        this.sections = sections;
    }
}
