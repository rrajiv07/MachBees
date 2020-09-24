package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class SkillMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillMaster.class);
        SkillMaster skillMaster1 = new SkillMaster();
        skillMaster1.setId(1L);
        SkillMaster skillMaster2 = new SkillMaster();
        skillMaster2.setId(skillMaster1.getId());
        assertThat(skillMaster1).isEqualTo(skillMaster2);
        skillMaster2.setId(2L);
        assertThat(skillMaster1).isNotEqualTo(skillMaster2);
        skillMaster1.setId(null);
        assertThat(skillMaster1).isNotEqualTo(skillMaster2);
    }
}
