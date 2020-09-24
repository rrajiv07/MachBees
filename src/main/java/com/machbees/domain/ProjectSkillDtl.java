package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProjectSkillDtl.
 */
@Entity
@Table(name = "project_skill_dtl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectSkillDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectSkillDtls", allowSetters = true)
    private SkillMaster skill;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectskilldetails", allowSetters = true)
    private ProjectHdr project;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkillMaster getSkill() {
        return skill;
    }

    public ProjectSkillDtl skill(SkillMaster skillMaster) {
        this.skill = skillMaster;
        return this;
    }

    public void setSkill(SkillMaster skillMaster) {
        this.skill = skillMaster;
    }

    public ProjectHdr getProject() {
        return project;
    }

    public ProjectSkillDtl project(ProjectHdr projectHdr) {
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
        if (!(o instanceof ProjectSkillDtl)) {
            return false;
        }
        return id != null && id.equals(((ProjectSkillDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectSkillDtl{" +
            "id=" + getId() +
            "}";
    }
}
