package com.machbees.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProjectAttachmentDtl.
 */
@Entity
@Table(name = "project_attachment_dtl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectAttachmentDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "file_id", length = 40, nullable = false)
    private String fileId;

    @NotNull
    @Size(max = 255)
    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    @NotNull
    @Size(max = 10)
    @Column(name = "file_type", length = 10, nullable = false)
    private String fileType;

    @ManyToOne
    @JsonIgnoreProperties(value = "projectattachmentdetails", allowSetters = true)
    private ProjectHdr project;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public ProjectAttachmentDtl fileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public ProjectAttachmentDtl fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public ProjectAttachmentDtl fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public ProjectHdr getProject() {
        return project;
    }

    public ProjectAttachmentDtl project(ProjectHdr projectHdr) {
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
        if (!(o instanceof ProjectAttachmentDtl)) {
            return false;
        }
        return id != null && id.equals(((ProjectAttachmentDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectAttachmentDtl{" +
            "id=" + getId() +
            ", fileId='" + getFileId() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", fileType='" + getFileType() + "'" +
            "}";
    }
}
