package org.helmut.profile.common.model;

import org.helmut.profile.common.validation.groups.Existing;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

public class PostTO {

    @NotNull(groups = Existing.class)
    private Long id;

    @NotNull
    @Size(min = 5, max = 255)
    private String title;

    @NotNull
    @Size(min = 50, max = 5000)
    private String content;

    @NotNull
    private String tags;
    private String dateCreated;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
