package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A ProjectHdr.
 */
@Entity
@Table(name = "project_hdr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectHdr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 80)
    @Column(name = "overview", length = 80, nullable = false)
    private String overview;

    @NotNull
    @Size(max = 125)
    @Column(name = "project_description", length = 125, nullable = false)
    private String projectDescription;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Size(max = 40)
    @Column(name = "created_by", length = 40, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Size(max = 40)
    @Column(name = "modified_by", length = 40)
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProjectAttachmentDtl> projectattachmentdetails = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProjectFeatureDtl> projectfeaturedetails = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProjectRoleDtl> projectroledetails = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProjectSkillDtl> projectskilldetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private ProjectTypeMaster projectType;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private ProjectSpecificationMaster projectSpecification;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private CategoryMetadata applicationType;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private CategoryMetadata model;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private ProjectCategoryMaster projectCategory;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private CategoryMetadata visibility;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private CategoryMetadata preferContactedBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectHdrs", allowSetters = true)
    private CategoryMetadata status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public ProjectHdr overview(String overview) {
        this.overview = overview;
        return this;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public ProjectHdr projectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
        return this;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ProjectHdr endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ProjectHdr createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ProjectHdr createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public ProjectHdr modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public ProjectHdr modifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<ProjectAttachmentDtl> getProjectattachmentdetails() {
        return projectattachmentdetails;
    }

    public ProjectHdr projectattachmentdetails(Set<ProjectAttachmentDtl> projectAttachmentDtls) {
        this.projectattachmentdetails = projectAttachmentDtls;
        return this;
    }

    public ProjectHdr addProjectattachmentdetails(ProjectAttachmentDtl projectAttachmentDtl) {
        this.projectattachmentdetails.add(projectAttachmentDtl);
        projectAttachmentDtl.setProject(this);
        return this;
    }

    public ProjectHdr removeProjectattachmentdetails(ProjectAttachmentDtl projectAttachmentDtl) {
        this.projectattachmentdetails.remove(projectAttachmentDtl);
        projectAttachmentDtl.setProject(null);
        return this;
    }

    public void setProjectattachmentdetails(Set<ProjectAttachmentDtl> projectAttachmentDtls) {
        this.projectattachmentdetails = projectAttachmentDtls;
    }

    public Set<ProjectFeatureDtl> getProjectfeaturedetails() {
        return projectfeaturedetails;
    }

    public ProjectHdr projectfeaturedetails(Set<ProjectFeatureDtl> projectFeatureDtls) {
        this.projectfeaturedetails = projectFeatureDtls;
        return this;
    }

    public ProjectHdr addProjectfeaturedetails(ProjectFeatureDtl projectFeatureDtl) {
        this.projectfeaturedetails.add(projectFeatureDtl);
        projectFeatureDtl.setProject(this);
        return this;
    }

    public ProjectHdr removeProjectfeaturedetails(ProjectFeatureDtl projectFeatureDtl) {
        this.projectfeaturedetails.remove(projectFeatureDtl);
        projectFeatureDtl.setProject(null);
        return this;
    }

    public void setProjectfeaturedetails(Set<ProjectFeatureDtl> projectFeatureDtls) {
        this.projectfeaturedetails = projectFeatureDtls;
    }

    public Set<ProjectRoleDtl> getProjectroledetails() {
        return projectroledetails;
    }

    public ProjectHdr projectroledetails(Set<ProjectRoleDtl> projectRoleDtls) {
        this.projectroledetails = projectRoleDtls;
        return this;
    }

    public ProjectHdr addProjectroledetails(ProjectRoleDtl projectRoleDtl) {
        this.projectroledetails.add(projectRoleDtl);
        projectRoleDtl.setProject(this);
        return this;
    }

    public ProjectHdr removeProjectroledetails(ProjectRoleDtl projectRoleDtl) {
        this.projectroledetails.remove(projectRoleDtl);
        projectRoleDtl.setProject(null);
        return this;
    }

    public void setProjectroledetails(Set<ProjectRoleDtl> projectRoleDtls) {
        this.projectroledetails = projectRoleDtls;
    }

    public Set<ProjectSkillDtl> getProjectskilldetails() {
        return projectskilldetails;
    }

    public ProjectHdr projectskilldetails(Set<ProjectSkillDtl> projectSkillDtls) {
        this.projectskilldetails = projectSkillDtls;
        return this;
    }

    public ProjectHdr addProjectskilldetails(ProjectSkillDtl projectSkillDtl) {
        this.projectskilldetails.add(projectSkillDtl);
        projectSkillDtl.setProject(this);
        return this;
    }

    public ProjectHdr removeProjectskilldetails(ProjectSkillDtl projectSkillDtl) {
        this.projectskilldetails.remove(projectSkillDtl);
        projectSkillDtl.setProject(null);
        return this;
    }

    public void setProjectskilldetails(Set<ProjectSkillDtl> projectSkillDtls) {
        this.projectskilldetails = projectSkillDtls;
    }

    public ProjectTypeMaster getProjectType() {
        return projectType;
    }

    public ProjectHdr projectType(ProjectTypeMaster projectTypeMaster) {
        this.projectType = projectTypeMaster;
        return this;
    }

    public void setProjectType(ProjectTypeMaster projectTypeMaster) {
        this.projectType = projectTypeMaster;
    }

    public ProjectSpecificationMaster getProjectSpecification() {
        return projectSpecification;
    }

    public ProjectHdr projectSpecification(ProjectSpecificationMaster projectSpecificationMaster) {
        this.projectSpecification = projectSpecificationMaster;
        return this;
    }

    public void setProjectSpecification(ProjectSpecificationMaster projectSpecificationMaster) {
        this.projectSpecification = projectSpecificationMaster;
    }

    public CategoryMetadata getApplicationType() {
        return applicationType;
    }

    public ProjectHdr applicationType(CategoryMetadata categoryMetadata) {
        this.applicationType = categoryMetadata;
        return this;
    }

    public void setApplicationType(CategoryMetadata categoryMetadata) {
        this.applicationType = categoryMetadata;
    }

    public CategoryMetadata getModel() {
        return model;
    }

    public ProjectHdr model(CategoryMetadata categoryMetadata) {
        this.model = categoryMetadata;
        return this;
    }

    public void setModel(CategoryMetadata categoryMetadata) {
        this.model = categoryMetadata;
    }

    public ProjectCategoryMaster getProjectCategory() {
        return projectCategory;
    }

    public ProjectHdr projectCategory(ProjectCategoryMaster projectCategoryMaster) {
        this.projectCategory = projectCategoryMaster;
        return this;
    }

    public void setProjectCategory(ProjectCategoryMaster projectCategoryMaster) {
        this.projectCategory = projectCategoryMaster;
    }

    public CategoryMetadata getVisibility() {
        return visibility;
    }

    public ProjectHdr visibility(CategoryMetadata categoryMetadata) {
        this.visibility = categoryMetadata;
        return this;
    }

    public void setVisibility(CategoryMetadata categoryMetadata) {
        this.visibility = categoryMetadata;
    }

    public CategoryMetadata getPreferContactedBy() {
        return preferContactedBy;
    }

    public ProjectHdr preferContactedBy(CategoryMetadata categoryMetadata) {
        this.preferContactedBy = categoryMetadata;
        return this;
    }

    public void setPreferContactedBy(CategoryMetadata categoryMetadata) {
        this.preferContactedBy = categoryMetadata;
    }

    public CategoryMetadata getStatus() {
        return status;
    }

    public ProjectHdr status(CategoryMetadata categoryMetadata) {
        this.status = categoryMetadata;
        return this;
    }

    public void setStatus(CategoryMetadata categoryMetadata) {
        this.status = categoryMetadata;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectHdr)) {
            return false;
        }
        return id != null && id.equals(((ProjectHdr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectHdr{" +
            "id=" + getId() +
            ", overview='" + getOverview() + "'" +
            ", projectDescription='" + getProjectDescription() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
