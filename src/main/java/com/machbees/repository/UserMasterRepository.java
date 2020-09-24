package com.machbees.repository;

import com.machbees.domain.UserMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {
}
