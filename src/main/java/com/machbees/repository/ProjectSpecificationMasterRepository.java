package com.machbees.repository;

import com.machbees.domain.ProjectSpecificationMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectSpecificationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectSpecificationMasterRepository extends JpaRepository<ProjectSpecificationMaster, Long> {
}
