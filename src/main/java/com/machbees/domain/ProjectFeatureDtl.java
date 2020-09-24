package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProjectFeatureDtl.
 */
@Entity
@Table(name = "project_feature_dtl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectFeatureDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectFeatureDtls", allowSetters = true)
    private ProjectFeatureMaster feature;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectfeaturedetails", allowSetters = true)
    private ProjectHdr project;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectFeatureMaster getFeature() {
        return feature;
    }

    public ProjectFeatureDtl feature(ProjectFeatureMaster projectFeatureMaster) {
        this.feature = projectFeatureMaster;
        return this;
    }

    public void setFeature(ProjectFeatureMaster projectFeatureMaster) {
        this.feature = projectFeatureMaster;
    }

    public ProjectHdr getProject() {
        return project;
    }

    public ProjectFeatureDtl project(ProjectHdr projectHdr) {
        this.project = projectHdr;
        return this;
    }

    public void setProject(ProjectHdr projectHdr) {
        this.project = projectHdr;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectFeatureDtl)) {
            return false;
        }
        return id != null && id.equals(((ProjectFeatureDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectFeatureDtl{" +
            "id=" + getId() +
            "}";
    }
}
