package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProjectRoleDtl.
 */
@Entity
@Table(name = "project_role_dtl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectRoleDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectRoleDtls", allowSetters = true)
    private ProjectRoleMaster role;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectroledetails", allowSetters = true)
    private ProjectHdr project;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectRoleMaster getRole() {
        return role;
    }

    public ProjectRoleDtl role(ProjectRoleMaster projectRoleMaster) {
        this.role = projectRoleMaster;
        return this;
    }

    public void setRole(ProjectRoleMaster projectRoleMaster) {
        this.role = projectRoleMaster;
    }

    public ProjectHdr getProject() {
        return project;
    }

    public ProjectRoleDtl project(ProjectHdr projectHdr) {
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
        if (!(o instanceof ProjectRoleDtl)) {
            return false;
        }
        return id != null && id.equals(((ProjectRoleDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectRoleDtl{" +
            "id=" + getId() +
            "}";
    }
}
