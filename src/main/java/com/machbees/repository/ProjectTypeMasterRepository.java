package com.machbees.repository;

import com.machbees.domain.ProjectTypeMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectTypeMasterRepository extends JpaRepository<ProjectTypeMaster, Long> {
}
