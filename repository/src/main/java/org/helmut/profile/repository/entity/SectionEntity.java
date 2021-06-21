package org.helmut.profile.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_SECTION")
public class SectionEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
