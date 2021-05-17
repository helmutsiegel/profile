package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_CV")
public class CVEntity extends BaseEntity {

    @Column
    private String about;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienceEntity> experiences;
}
