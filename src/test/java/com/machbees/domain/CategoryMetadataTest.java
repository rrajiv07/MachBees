package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class CategoryMetadataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryMetadata.class);
        CategoryMetadata categoryMetadata1 = new CategoryMetadata();
        categoryMetadata1.setId(1L);
        CategoryMetadata categoryMetadata2 = new CategoryMetadata();
        categoryMetadata2.setId(categoryMetadata1.getId());
        assertThat(categoryMetadata1).isEqualTo(categoryMetadata2);
        categoryMetadata2.setId(2L);
        assertThat(categoryMetadata1).isNotEqualTo(categoryMetadata2);
        categoryMetadata1.setId(null);
        assertThat(categoryMetadata1).isNotEqualTo(categoryMetadata2);
    }
}
