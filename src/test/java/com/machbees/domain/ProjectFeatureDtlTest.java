package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectFeatureDtlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectFeatureDtl.class);
        ProjectFeatureDtl projectFeatureDtl1 = new ProjectFeatureDtl();
        projectFeatureDtl1.setId(1L);
        ProjectFeatureDtl projectFeatureDtl2 = new ProjectFeatureDtl();
        projectFeatureDtl2.setId(projectFeatureDtl1.getId());
        assertThat(projectFeatureDtl1).isEqualTo(projectFeatureDtl2);
        projectFeatureDtl2.setId(2L);
        assertThat(projectFeatureDtl1).isNotEqualTo(projectFeatureDtl2);
        projectFeatureDtl1.setId(null);
        assertThat(projectFeatureDtl1).isNotEqualTo(projectFeatureDtl2);
    }
}
