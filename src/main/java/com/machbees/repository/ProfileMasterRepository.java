package com.machbees.repository;

import com.machbees.domain.ProfileMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProfileMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileMasterRepository extends JpaRepository<ProfileMaster, Long> {
}
