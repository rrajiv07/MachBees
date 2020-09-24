package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProjectCategoryMaster.
 */
@Entity
@Table(name = "project_category_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectCategoryMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "category_code", length = 20, nullable = false)
    private String categoryCode;

    @NotNull
    @Size(max = 80)
    @Column(name = "category_name", length = 80, nullable = false)
    private String categoryName;

    @NotNull
    @Size(max = 80)
    @Column(name = "category_description", length = 80, nullable = false)
    private String categoryDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public ProjectCategoryMaster categoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
        return this;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ProjectCategoryMaster categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public ProjectCategoryMaster categoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        return this;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectCategoryMaster)) {
            return false;
        }
        return id != null && id.equals(((ProjectCategoryMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectCategoryMaster{" +
            "id=" + getId() +
            ", categoryCode='" + getCategoryCode() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", categoryDescription='" + getCategoryDescription() + "'" +
            "}";
    }
}
