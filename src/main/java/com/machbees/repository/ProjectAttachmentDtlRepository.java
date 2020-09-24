package com.machbees.repository;

import com.machbees.domain.ProjectAttachmentDtl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectAttachmentDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectAttachmentDtlRepository extends JpaRepository<ProjectAttachmentDtl, Long> {
}
