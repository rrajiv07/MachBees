package com.machbees.repository;

import com.machbees.domain.ProjectFeatureMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectFeatureMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectFeatureMasterRepository extends JpaRepository<ProjectFeatureMaster, Long> {
}
