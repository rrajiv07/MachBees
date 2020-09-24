package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A CategoryMetadata.
 */
@Entity
@Table(name = "category_metadata")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategoryMetadata implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "category_code", length = 40, nullable = false)
    private String categoryCode;

    @NotNull
    @Size(max = 40)
    @Column(name = "category_name", length = 40, nullable = false)
    private String categoryName;

    @NotNull
    @Size(max = 80)
    @Column(name = "category_description", length = 80, nullable = false)
    private String categoryDescription;

    @NotNull
    @Column(name = "sequence_number", nullable = false)
    private Integer sequenceNumber;

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

    public CategoryMetadata categoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
        return this;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryMetadata categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public CategoryMetadata categoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
        return this;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public CategoryMetadata sequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryMetadata)) {
            return false;
        }
        return id != null && id.equals(((CategoryMetadata) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryMetadata{" +
            "id=" + getId() +
            ", categoryCode='" + getCategoryCode() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", categoryDescription='" + getCategoryDescription() + "'" +
            ", sequenceNumber=" + getSequenceNumber() +
            "}";
    }
}
