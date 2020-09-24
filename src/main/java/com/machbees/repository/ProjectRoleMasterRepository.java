package com.machbees.repository;

import com.machbees.domain.ProjectRoleMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectRoleMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRoleMasterRepository extends JpaRepository<ProjectRoleMaster, Long> {
}
