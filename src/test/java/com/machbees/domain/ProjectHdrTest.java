package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectHdrTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectHdr.class);
        ProjectHdr projectHdr1 = new ProjectHdr();
        projectHdr1.setId(1L);
        ProjectHdr projectHdr2 = new ProjectHdr();
        projectHdr2.setId(projectHdr1.getId());
        assertThat(projectHdr1).isEqualTo(projectHdr2);
        projectHdr2.setId(2L);
        assertThat(projectHdr1).isNotEqualTo(projectHdr2);
        projectHdr1.setId(null);
        assertThat(projectHdr1).isNotEqualTo(projectHdr2);
    }
}
