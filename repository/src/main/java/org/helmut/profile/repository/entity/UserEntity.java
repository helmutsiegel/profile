package org.helmut.profile.repository.entity;

import org.helmut.profile.repository.enums.Seniority;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "T_USER")
@NamedQuery(name = UserEntity.BY_EMAIL_AND_PASSWORD, query = "select u from UserEntity u where u.email like :email and u.password like :password")
public class UserEntity extends BaseEntity {
    public static final String BY_EMAIL_AND_PASSWORD = "User.byEmailAndPassword";

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String password;

    @Column(name = "title")
    private String title;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    @Enumerated(EnumType.ORDINAL) // This stores seniority as number
//  @Enumerated(EnumType.STRING)  // This stores as string
    private Seniority seniority;

    @Transient //Will not be mapped
    private Integer age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateAge() {
        if (Objects.isNull(birthDate)) {
            age = null;
            return;
        }
        age = Period.between(birthDate, LocalDate.now()).getYears();
    }

    @PrePersist
    @PreUpdate
    private void validate() {
        if (Objects.isNull(firstName) || "".equals(firstName)) {
            throw new IllegalArgumentException("Invalid first name!");
        }
        if (Objects.isNull(lastName) || "".equals(lastName)) {
            throw new IllegalArgumentException("Invalid last name!");
        }
    }
}
