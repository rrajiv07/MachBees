package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A UserCompanyDetails.
 */
@Entity
@Table(name = "user_company_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCompanyDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Size(max = 20)
    @Column(name = "website", length = 20)
    private String website;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @NotNull
    @Size(max = 80)
    @Column(name = "address", length = 80, nullable = false)
    private String address;

    @NotNull
    @Size(max = 40)
    @Column(name = "vat", length = 40, nullable = false)
    private String vat;

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

    public UserCompanyDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public UserCompanyDetails website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public UserCompanyDetails description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public UserCompanyDetails address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVat() {
        return vat;
    }

    public UserCompanyDetails vat(String vat) {
        this.vat = vat;
        return this;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getMobile() {
        return mobile;
    }

    public UserCompanyDetails mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public UserCompanyDetails linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public UserCompanyDetails twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSkype() {
        return skype;
    }

    public UserCompanyDetails skype(String skype) {
        this.skype = skype;
        return this;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public UserMaster getUser() {
        return user;
    }

    public UserCompanyDetails user(UserMaster userMaster) {
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
        if (!(o instanceof UserCompanyDetails)) {
            return false;
        }
        return id != null && id.equals(((UserCompanyDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCompanyDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", website='" + getWebsite() + "'" +
            ", description='" + getDescription() + "'" +
            ", address='" + getAddress() + "'" +
            ", vat='" + getVat() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", skype='" + getSkype() + "'" +
            "}";
    }
}
