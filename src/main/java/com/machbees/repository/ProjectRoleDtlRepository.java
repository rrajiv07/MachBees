package com.machbees.repository;

import com.machbees.domain.ProjectRoleDtl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectRoleDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRoleDtlRepository extends JpaRepository<ProjectRoleDtl, Long> {
}
