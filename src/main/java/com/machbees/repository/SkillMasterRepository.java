package com.machbees.repository;

import com.machbees.domain.SkillMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SkillMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillMasterRepository extends JpaRepository<SkillMaster, Long> {
}
