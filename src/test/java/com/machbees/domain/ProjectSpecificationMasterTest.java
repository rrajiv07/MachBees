package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectSpecificationMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectSpecificationMaster.class);
        ProjectSpecificationMaster projectSpecificationMaster1 = new ProjectSpecificationMaster();
        projectSpecificationMaster1.setId(1L);
        ProjectSpecificationMaster projectSpecificationMaster2 = new ProjectSpecificationMaster();
        projectSpecificationMaster2.setId(projectSpecificationMaster1.getId());
        assertThat(projectSpecificationMaster1).isEqualTo(projectSpecificationMaster2);
        projectSpecificationMaster2.setId(2L);
        assertThat(projectSpecificationMaster1).isNotEqualTo(projectSpecificationMaster2);
        projectSpecificationMaster1.setId(null);
        assertThat(projectSpecificationMaster1).isNotEqualTo(projectSpecificationMaster2);
    }
}
