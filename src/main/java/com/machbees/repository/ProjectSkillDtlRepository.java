package com.machbees.repository;

import com.machbees.domain.ProjectSkillDtl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectSkillDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectSkillDtlRepository extends JpaRepository<ProjectSkillDtl, Long> {
}
