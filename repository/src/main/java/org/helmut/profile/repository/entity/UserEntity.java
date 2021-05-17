package org.helmut.profile.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
public class UserEntity extends BaseEntity {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
