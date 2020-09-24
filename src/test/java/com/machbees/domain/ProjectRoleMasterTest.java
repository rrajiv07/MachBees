package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectRoleMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectRoleMaster.class);
        ProjectRoleMaster projectRoleMaster1 = new ProjectRoleMaster();
        projectRoleMaster1.setId(1L);
        ProjectRoleMaster projectRoleMaster2 = new ProjectRoleMaster();
        projectRoleMaster2.setId(projectRoleMaster1.getId());
        assertThat(projectRoleMaster1).isEqualTo(projectRoleMaster2);
        projectRoleMaster2.setId(2L);
        assertThat(projectRoleMaster1).isNotEqualTo(projectRoleMaster2);
        projectRoleMaster1.setId(null);
        assertThat(projectRoleMaster1).isNotEqualTo(projectRoleMaster2);
    }
}
