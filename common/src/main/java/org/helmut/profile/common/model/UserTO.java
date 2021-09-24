package org.helmut.profile.common.model;


import org.helmut.profile.common.enums.Seniority;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class UserTO {
    private String email;
    private String firstName;
    private String lastName;
    private String title;
    private String birthDate;
    private Seniority seniority;

    private UserTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public static class Builder {

        private String email;
        private String firstName;
        private String lastName;
        private String title;
        private String birthDate;
        private Seniority seniority;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder seniority(Seniority seniority) {
            this.seniority = seniority;
            return this;
        }

        public UserTO build() {
            UserTO userTO = new UserTO();
            userTO.setEmail(email);
            userTO.setFirstName(firstName);
            userTO.setLastName(lastName);
            userTO.setBirthDate(birthDate);
            userTO.setTitle(title);
            userTO.setSeniority(seniority);
            return userTO;
        }

    }
}
