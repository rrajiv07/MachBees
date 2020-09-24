package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A ProjectBudgetDtl.
 */
@Entity
@Table(name = "project_budget_dtl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectBudgetDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "budget", precision = 21, scale = 2)
    private BigDecimal budget;

    @Column(name = "ip_ownership", precision = 21, scale = 2)
    private BigDecimal ipOwnership;

    @OneToOne
    @JoinColumn(unique = true)
    private ProjectHdr project;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public ProjectBudgetDtl budget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getIpOwnership() {
        return ipOwnership;
    }

    public ProjectBudgetDtl ipOwnership(BigDecimal ipOwnership) {
        this.ipOwnership = ipOwnership;
        return this;
    }

    public void setIpOwnership(BigDecimal ipOwnership) {
        this.ipOwnership = ipOwnership;
    }

    public ProjectHdr getProject() {
        return project;
    }

    public ProjectBudgetDtl project(ProjectHdr projectHdr) {
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
        if (!(o instanceof ProjectBudgetDtl)) {
            return false;
        }
        return id != null && id.equals(((ProjectBudgetDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectBudgetDtl{" +
            "id=" + getId() +
            ", budget=" + getBudget() +
            ", ipOwnership=" + getIpOwnership() +
            "}";
    }
}
