package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectRoleDtlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectRoleDtl.class);
        ProjectRoleDtl projectRoleDtl1 = new ProjectRoleDtl();
        projectRoleDtl1.setId(1L);
        ProjectRoleDtl projectRoleDtl2 = new ProjectRoleDtl();
        projectRoleDtl2.setId(projectRoleDtl1.getId());
        assertThat(projectRoleDtl1).isEqualTo(projectRoleDtl2);
        projectRoleDtl2.setId(2L);
        assertThat(projectRoleDtl1).isNotEqualTo(projectRoleDtl2);
        projectRoleDtl1.setId(null);
        assertThat(projectRoleDtl1).isNotEqualTo(projectRoleDtl2);
    }
}
