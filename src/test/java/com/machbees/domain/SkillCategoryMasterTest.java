package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class SkillCategoryMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillCategoryMaster.class);
        SkillCategoryMaster skillCategoryMaster1 = new SkillCategoryMaster();
        skillCategoryMaster1.setId(1L);
        SkillCategoryMaster skillCategoryMaster2 = new SkillCategoryMaster();
        skillCategoryMaster2.setId(skillCategoryMaster1.getId());
        assertThat(skillCategoryMaster1).isEqualTo(skillCategoryMaster2);
        skillCategoryMaster2.setId(2L);
        assertThat(skillCategoryMaster1).isNotEqualTo(skillCategoryMaster2);
        skillCategoryMaster1.setId(null);
        assertThat(skillCategoryMaster1).isNotEqualTo(skillCategoryMaster2);
    }
}
