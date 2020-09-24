package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A UserPersonalDetails.
 */
@Entity
@Table(name = "user_personal_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPersonalDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @NotNull
    @Size(max = 20)
    @Column(name = "surname", length = 20, nullable = false)
    private String surname;

    @NotNull
    @Size(max = 80)
    @Column(name = "address", length = 80, nullable = false)
    private String address;

    @NotNull
    @Size(max = 40)
    @Column(name = "country", length = 40, nullable = false)
    private String country;

    @NotNull
    @Size(max = 40)
    @Column(name = "mobile", length = 40, nullable = false)
    private String mobile;

    @Size(max = 40)
    @Column(name = "linkedin", length = 40)
    private String linkedin;

    @Size(max = 40)
    @Column(name = "twitter", length = 40)
    private String twitter;

    @Size(max = 40)
    @Column(name = "skype", length = 40)
    private String skype;

    @Size(max = 100)
    @Column(name = "self_presentation", length = 100)
    private String selfPresentation;

    @Size(max = 100)
    @Column(name = "virtualcv", length = 100)
    private String virtualcv;

    @OneToOne
    @JoinColumn(unique = true)
    private UserMaster user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserPersonalDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public UserPersonalDetails surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public UserPersonalDetails address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public UserPersonalDetails country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public UserPersonalDetails mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public UserPersonalDetails linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public UserPersonalDetails twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSkype() {
        return skype;
    }

    public UserPersonalDetails skype(String skype) {
        this.skype = skype;
        return this;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getSelfPresentation() {
        return selfPresentation;
    }

    public UserPersonalDetails selfPresentation(String selfPresentation) {
        this.selfPresentation = selfPresentation;
        return this;
    }

    public void setSelfPresentation(String selfPresentation) {
        this.selfPresentation = selfPresentation;
    }

    public String getVirtualcv() {
        return virtualcv;
    }

    public UserPersonalDetails virtualcv(String virtualcv) {
        this.virtualcv = virtualcv;
        return this;
    }

    public void setVirtualcv(String virtualcv) {
        this.virtualcv = virtualcv;
    }

    public UserMaster getUser() {
        return user;
    }

    public UserPersonalDetails user(UserMaster userMaster) {
        this.user = userMaster;
        return this;
    }

    public void setUser(UserMaster userMaster) {
        this.user = userMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPersonalDetails)) {
            return false;
        }
        return id != null && id.equals(((UserPersonalDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPersonalDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", address='" + getAddress() + "'" +
            ", country='" + getCountry() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", skype='" + getSkype() + "'" +
            ", selfPresentation='" + getSelfPresentation() + "'" +
            ", virtualcv='" + getVirtualcv() + "'" +
            "}";
    }
}
