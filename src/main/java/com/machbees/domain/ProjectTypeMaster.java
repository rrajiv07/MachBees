package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProjectTypeMaster.
 */
@Entity
@Table(name = "project_type_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "project_type_code", length = 20, nullable = false)
    private String projectTypeCode;

    @NotNull
    @Size(max = 80)
    @Column(name = "project_type_name", length = 80, nullable = false)
    private String projectTypeName;

    @NotNull
    @Size(max = 80)
    @Column(name = "project_type_description", length = 80, nullable = false)
    private String projectTypeDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectTypeCode() {
        return projectTypeCode;
    }

    public ProjectTypeMaster projectTypeCode(String projectTypeCode) {
        this.projectTypeCode = projectTypeCode;
        return this;
    }

    public void setProjectTypeCode(String projectTypeCode) {
        this.projectTypeCode = projectTypeCode;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public ProjectTypeMaster projectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
        return this;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getProjectTypeDescription() {
        return projectTypeDescription;
    }

    public ProjectTypeMaster projectTypeDescription(String projectTypeDescription) {
        this.projectTypeDescription = projectTypeDescription;
        return this;
    }

    public void setProjectTypeDescription(String projectTypeDescription) {
        this.projectTypeDescription = projectTypeDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectTypeMaster)) {
            return false;
        }
        return id != null && id.equals(((ProjectTypeMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectTypeMaster{" +
            "id=" + getId() +
            ", projectTypeCode='" + getProjectTypeCode() + "'" +
            ", projectTypeName='" + getProjectTypeName() + "'" +
            ", projectTypeDescription='" + getProjectTypeDescription() + "'" +
            "}";
    }
}
