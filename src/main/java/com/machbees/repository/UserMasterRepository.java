package com.machbees.repository;

import com.machbees.domain.CategoryMetadata;
import com.machbees.domain.UserMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the UserMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long> {

	UserMaster findByEmailId(String emailId);

	UserMaster findByEmailIdAndStatus(String emailId, CategoryMetadata categoryMetadata);

	UserMaster findById(int id);

	UserMaster findByIdAndStatus(long userId, CategoryMetadata byCategoryCodeAndCategoryName);
	
}
