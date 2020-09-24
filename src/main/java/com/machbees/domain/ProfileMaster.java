package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProfileMaster.
 */
@Entity
@Table(name = "profile_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProfileMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "profile_code", length = 20, nullable = false)
    private String profileCode;

    @NotNull
    @Size(max = 40)
    @Column(name = "profile_name", length = 40, nullable = false)
    private String profileName;

    @NotNull
    @Size(max = 80)
    @Column(name = "profile_description", length = 80, nullable = false)
    private String profileDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileCode() {
        return profileCode;
    }

    public ProfileMaster profileCode(String profileCode) {
        this.profileCode = profileCode;
        return this;
    }

    public void setProfileCode(String profileCode) {
        this.profileCode = profileCode;
    }

    public String getProfileName() {
        return profileName;
    }

    public ProfileMaster profileName(String profileName) {
        this.profileName = profileName;
        return this;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public ProfileMaster profileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
        return this;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileMaster)) {
            return false;
        }
        return id != null && id.equals(((ProfileMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileMaster{" +
            "id=" + getId() +
            ", profileCode='" + getProfileCode() + "'" +
            ", profileName='" + getProfileName() + "'" +
            ", profileDescription='" + getProfileDescription() + "'" +
            "}";
    }
}
