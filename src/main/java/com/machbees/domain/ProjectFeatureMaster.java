package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProjectFeatureMaster.
 */
@Entity
@Table(name = "project_feature_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectFeatureMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "feature_code", length = 40, nullable = false)
    private String featureCode;

    @NotNull
    @Size(max = 80)
    @Column(name = "feature_name", length = 80, nullable = false)
    private String featureName;

    @NotNull
    @Size(max = 80)
    @Column(name = "feature_description", length = 80, nullable = false)
    private String featureDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public ProjectFeatureMaster featureCode(String featureCode) {
        this.featureCode = featureCode;
        return this;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getFeatureName() {
        return featureName;
    }

    public ProjectFeatureMaster featureName(String featureName) {
        this.featureName = featureName;
        return this;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public ProjectFeatureMaster featureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
        return this;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectFeatureMaster)) {
            return false;
        }
        return id != null && id.equals(((ProjectFeatureMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectFeatureMaster{" +
            "id=" + getId() +
            ", featureCode='" + getFeatureCode() + "'" +
            ", featureName='" + getFeatureName() + "'" +
            ", featureDescription='" + getFeatureDescription() + "'" +
            "}";
    }
}
