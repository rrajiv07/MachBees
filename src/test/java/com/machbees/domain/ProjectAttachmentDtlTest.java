package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProjectAttachmentDtlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectAttachmentDtl.class);
        ProjectAttachmentDtl projectAttachmentDtl1 = new ProjectAttachmentDtl();
        projectAttachmentDtl1.setId(1L);
        ProjectAttachmentDtl projectAttachmentDtl2 = new ProjectAttachmentDtl();
        projectAttachmentDtl2.setId(projectAttachmentDtl1.getId());
        assertThat(projectAttachmentDtl1).isEqualTo(projectAttachmentDtl2);
        projectAttachmentDtl2.setId(2L);
        assertThat(projectAttachmentDtl1).isNotEqualTo(projectAttachmentDtl2);
        projectAttachmentDtl1.setId(null);
        assertThat(projectAttachmentDtl1).isNotEqualTo(projectAttachmentDtl2);
    }
}
