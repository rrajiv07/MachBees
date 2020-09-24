package com.machbees.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProjectRoleMaster.
 */
@Entity
@Table(name = "project_role_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectRoleMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "role_code", length = 20, nullable = false)
    private String roleCode;

    @NotNull
    @Size(max = 80)
    @Column(name = "role_name", length = 80, nullable = false)
    private String roleName;

    @NotNull
    @Size(max = 80)
    @Column(name = "role_description", length = 80, nullable = false)
    private String roleDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public ProjectRoleMaster roleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public ProjectRoleMaster roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public ProjectRoleMaster roleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
        return this;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectRoleMaster)) {
            return false;
        }
        return id != null && id.equals(((ProjectRoleMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectRoleMaster{" +
            "id=" + getId() +
            ", roleCode='" + getRoleCode() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", roleDescription='" + getRoleDescription() + "'" +
            "}";
    }
}
