package com.machbees.repository;

import com.machbees.domain.CategoryMetadata;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the CategoryMetadata entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryMetadataRepository extends JpaRepository<CategoryMetadata, Long> {

	List<CategoryMetadata> findByCategoryName(String categoryName);

	CategoryMetadata getByCategoryName(String categoryName);

	CategoryMetadata getByCategoryCodeAndCategoryName(String code, String name);
}
