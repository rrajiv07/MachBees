package com.machbees.repository;

import com.machbees.domain.UserLanguageDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserLanguageDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserLanguageDetailsRepository extends JpaRepository<UserLanguageDetails, Long> {
}
