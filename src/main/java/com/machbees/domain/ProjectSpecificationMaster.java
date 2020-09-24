package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProjectSpecificationMaster.
 */
@Entity
@Table(name = "project_specification_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectSpecificationMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "specification_code", length = 20, nullable = false)
    private String specificationCode;

    @NotNull
    @Size(max = 100)
    @Column(name = "specification_name", length = 100, nullable = false)
    private String specificationName;

    @NotNull
    @Size(max = 100)
    @Column(name = "specification_description", length = 100, nullable = false)
    private String specificationDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecificationCode() {
        return specificationCode;
    }

    public ProjectSpecificationMaster specificationCode(String specificationCode) {
        this.specificationCode = specificationCode;
        return this;
    }

    public void setSpecificationCode(String specificationCode) {
        this.specificationCode = specificationCode;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public ProjectSpecificationMaster specificationName(String specificationName) {
        this.specificationName = specificationName;
        return this;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getSpecificationDescription() {
        return specificationDescription;
    }

    public ProjectSpecificationMaster specificationDescription(String specificationDescription) {
        this.specificationDescription = specificationDescription;
        return this;
    }

    public void setSpecificationDescription(String specificationDescription) {
        this.specificationDescription = specificationDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectSpecificationMaster)) {
            return false;
        }
        return id != null && id.equals(((ProjectSpecificationMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectSpecificationMaster{" +
            "id=" + getId() +
            ", specificationCode='" + getSpecificationCode() + "'" +
            ", specificationName='" + getSpecificationName() + "'" +
            ", specificationDescription='" + getSpecificationDescription() + "'" +
            "}";
    }
}
