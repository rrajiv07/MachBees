package com.machbees.repository;

import com.machbees.domain.ProjectCategoryMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectCategoryMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectCategoryMasterRepository extends JpaRepository<ProjectCategoryMaster, Long> {
}
