package org.helmut.profile.common.model;

import org.helmut.profile.common.validation.groups.Existing;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SectionTO {

    @NotNull(groups = Existing.class)
    private Long id;

    @NotNull
    @Size(min = 5)
    private String title;

    @NotNull
    @Size(min = 50)
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
