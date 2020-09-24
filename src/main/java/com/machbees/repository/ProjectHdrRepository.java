package com.machbees.repository;

import com.machbees.domain.ProjectHdr;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectHdr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectHdrRepository extends JpaRepository<ProjectHdr, Long> {
}
