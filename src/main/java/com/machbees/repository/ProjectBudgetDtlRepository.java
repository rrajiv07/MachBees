package com.machbees.repository;

import com.machbees.domain.ProjectBudgetDtl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectBudgetDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectBudgetDtlRepository extends JpaRepository<ProjectBudgetDtl, Long> {
}
