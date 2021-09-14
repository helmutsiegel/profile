package org.helmut.profile.common.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateChapterTO {

    @NotNull
    private String projectName;

    @NotNull
    @Size(min = 1)
    private String title;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
