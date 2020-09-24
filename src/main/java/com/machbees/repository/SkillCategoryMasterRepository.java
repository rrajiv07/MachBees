package com.machbees.repository;

import com.machbees.domain.SkillCategoryMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SkillCategoryMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillCategoryMasterRepository extends JpaRepository<SkillCategoryMaster, Long> {
}
