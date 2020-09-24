package com.machbees.repository;

import com.machbees.domain.UserCompanyDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserCompanyDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCompanyDetailsRepository extends JpaRepository<UserCompanyDetails, Long> {

	UserCompanyDetails findByUserId(long userId);
}
