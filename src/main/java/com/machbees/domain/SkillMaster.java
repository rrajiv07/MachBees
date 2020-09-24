package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SkillMaster.
 */
@Entity
@Table(name = "skill_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SkillMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "skill_code", length = 40, nullable = false)
    private String skillCode;

    @NotNull
    @Size(max = 80)
    @Column(name = "skill_name", length = 80, nullable = false)
    private String skillName;

    @NotNull
    @Size(max = 80)
    @Column(name = "skill_description", length = 80, nullable = false)
    private String skillDescription;

    @ManyToOne
    @JsonIgnoreProperties(value = "skillMasters", allowSetters = true)
    private SkillCategoryMaster skillCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillCode() {
        return skillCode;
    }

    public SkillMaster skillCode(String skillCode) {
        this.skillCode = skillCode;
        return this;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }

    public String getSkillName() {
        return skillName;
    }

    public SkillMaster skillName(String skillName) {
        this.skillName = skillName;
        return this;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public SkillMaster skillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
        return this;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public SkillCategoryMaster getSkillCategory() {
        return skillCategory;
    }

    public SkillMaster skillCategory(SkillCategoryMaster skillCategoryMaster) {
        this.skillCategory = skillCategoryMaster;
        return this;
    }

    public void setSkillCategory(SkillCategoryMaster skillCategoryMaster) {
        this.skillCategory = skillCategoryMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SkillMaster)) {
            return false;
        }
        return id != null && id.equals(((SkillMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SkillMaster{" +
            "id=" + getId() +
            ", skillCode='" + getSkillCode() + "'" +
            ", skillName='" + getSkillName() + "'" +
            ", skillDescription='" + getSkillDescription() + "'" +
            "}";
    }
}
