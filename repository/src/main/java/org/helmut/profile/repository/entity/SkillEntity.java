package org.helmut.profile.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_SKILL")
public class SkillEntity extends BaseEntity {

    @Column
    private String name;

    @Column(name = "description_of_experience")
    private String descriptionOfExperience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionOfExperience() {
        return descriptionOfExperience;
    }

    public void setDescriptionOfExperience(String descriptionOfExperience) {
        this.descriptionOfExperience = descriptionOfExperience;
    }
}
