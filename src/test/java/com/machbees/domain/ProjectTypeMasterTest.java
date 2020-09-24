package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectTypeMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectTypeMaster.class);
        ProjectTypeMaster projectTypeMaster1 = new ProjectTypeMaster();
        projectTypeMaster1.setId(1L);
        ProjectTypeMaster projectTypeMaster2 = new ProjectTypeMaster();
        projectTypeMaster2.setId(projectTypeMaster1.getId());
        assertThat(projectTypeMaster1).isEqualTo(projectTypeMaster2);
        projectTypeMaster2.setId(2L);
        assertThat(projectTypeMaster1).isNotEqualTo(projectTypeMaster2);
        projectTypeMaster1.setId(null);
        assertThat(projectTypeMaster1).isNotEqualTo(projectTypeMaster2);
    }
}
