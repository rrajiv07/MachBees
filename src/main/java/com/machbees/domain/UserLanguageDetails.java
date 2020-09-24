package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A UserLanguageDetails.
 */
@Entity
@Table(name = "user_language_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLanguageDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "userLanguageDetails", allowSetters = true)
    private CategoryMetadata language;

    @ManyToOne
    @JsonIgnoreProperties(value = "userLanguageDetails", allowSetters = true)
    private CategoryMetadata proficiency;

    @ManyToOne
    @JsonIgnoreProperties(value = "userlanguagedetails", allowSetters = true)
    private UserMaster user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryMetadata getLanguage() {
        return language;
    }

    public UserLanguageDetails language(CategoryMetadata categoryMetadata) {
        this.language = categoryMetadata;
        return this;
    }

    public void setLanguage(CategoryMetadata categoryMetadata) {
        this.language = categoryMetadata;
    }

    public CategoryMetadata getProficiency() {
        return proficiency;
    }

    public UserLanguageDetails proficiency(CategoryMetadata categoryMetadata) {
        this.proficiency = categoryMetadata;
        return this;
    }

    public void setProficiency(CategoryMetadata categoryMetadata) {
        this.proficiency = categoryMetadata;
    }

    public UserMaster getUser() {
        return user;
    }

    public UserLanguageDetails user(UserMaster userMaster) {
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
        if (!(o instanceof UserLanguageDetails)) {
            return false;
        }
        return id != null && id.equals(((UserLanguageDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserLanguageDetails{" +
            "id=" + getId() +
            "}";
    }
}
