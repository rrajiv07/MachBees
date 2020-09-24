package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectBudgetDtlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectBudgetDtl.class);
        ProjectBudgetDtl projectBudgetDtl1 = new ProjectBudgetDtl();
        projectBudgetDtl1.setId(1L);
        ProjectBudgetDtl projectBudgetDtl2 = new ProjectBudgetDtl();
        projectBudgetDtl2.setId(projectBudgetDtl1.getId());
        assertThat(projectBudgetDtl1).isEqualTo(projectBudgetDtl2);
        projectBudgetDtl2.setId(2L);
        assertThat(projectBudgetDtl1).isNotEqualTo(projectBudgetDtl2);
        projectBudgetDtl1.setId(null);
        assertThat(projectBudgetDtl1).isNotEqualTo(projectBudgetDtl2);
    }
}
