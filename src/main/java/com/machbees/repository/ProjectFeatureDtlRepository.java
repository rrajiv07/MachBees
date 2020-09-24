package com.machbees.repository;

import com.machbees.domain.ProjectFeatureDtl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectFeatureDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectFeatureDtlRepository extends JpaRepository<ProjectFeatureDtl, Long> {
}
