package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectFeatureMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectFeatureMaster.class);
        ProjectFeatureMaster projectFeatureMaster1 = new ProjectFeatureMaster();
        projectFeatureMaster1.setId(1L);
        ProjectFeatureMaster projectFeatureMaster2 = new ProjectFeatureMaster();
        projectFeatureMaster2.setId(projectFeatureMaster1.getId());
        assertThat(projectFeatureMaster1).isEqualTo(projectFeatureMaster2);
        projectFeatureMaster2.setId(2L);
        assertThat(projectFeatureMaster1).isNotEqualTo(projectFeatureMaster2);
        projectFeatureMaster1.setId(null);
        assertThat(projectFeatureMaster1).isNotEqualTo(projectFeatureMaster2);
    }
}
