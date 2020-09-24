package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectSkillDtlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectSkillDtl.class);
        ProjectSkillDtl projectSkillDtl1 = new ProjectSkillDtl();
        projectSkillDtl1.setId(1L);
        ProjectSkillDtl projectSkillDtl2 = new ProjectSkillDtl();
        projectSkillDtl2.setId(projectSkillDtl1.getId());
        assertThat(projectSkillDtl1).isEqualTo(projectSkillDtl2);
        projectSkillDtl2.setId(2L);
        assertThat(projectSkillDtl1).isNotEqualTo(projectSkillDtl2);
        projectSkillDtl1.setId(null);
        assertThat(projectSkillDtl1).isNotEqualTo(projectSkillDtl2);
    }
}
