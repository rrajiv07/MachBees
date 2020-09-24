package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectCategoryMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectCategoryMaster.class);
        ProjectCategoryMaster projectCategoryMaster1 = new ProjectCategoryMaster();
        projectCategoryMaster1.setId(1L);
        ProjectCategoryMaster projectCategoryMaster2 = new ProjectCategoryMaster();
        projectCategoryMaster2.setId(projectCategoryMaster1.getId());
        assertThat(projectCategoryMaster1).isEqualTo(projectCategoryMaster2);
        projectCategoryMaster2.setId(2L);
        assertThat(projectCategoryMaster1).isNotEqualTo(projectCategoryMaster2);
        projectCategoryMaster1.setId(null);
        assertThat(projectCategoryMaster1).isNotEqualTo(projectCategoryMaster2);
    }
}
