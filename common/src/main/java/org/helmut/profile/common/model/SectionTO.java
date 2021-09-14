package org.helmut.profile.common.model;

import org.helmut.profile.common.validation.groups.Existing;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

public class SectionTO {

    @NotNull(groups = Existing.class)
    private Long id;

    @NotNull(groups = {Default.class, Existing.class})
    @Size(min = 5, groups = {Default.class, Existing.class})
    private String title;

    @NotNull(groups = {Default.class, Existing.class})
    @Size(min = 50, groups = {Default.class, Existing.class})
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
