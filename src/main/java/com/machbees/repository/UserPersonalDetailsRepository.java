package com.machbees.repository;

import com.machbees.domain.UserMaster;
import com.machbees.domain.UserPersonalDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserPersonalDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetails, Long> {

	UserPersonalDetails findByUserId(long userId);
}
