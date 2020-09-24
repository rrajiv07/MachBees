package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class ProfileMasterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfileMaster.class);
        ProfileMaster profileMaster1 = new ProfileMaster();
        profileMaster1.setId(1L);
        ProfileMaster profileMaster2 = new ProfileMaster();
        profileMaster2.setId(profileMaster1.getId());
        assertThat(profileMaster1).isEqualTo(profileMaster2);
        profileMaster2.setId(2L);
        assertThat(profileMaster1).isNotEqualTo(profileMaster2);
        profileMaster1.setId(null);
        assertThat(profileMaster1).isNotEqualTo(profileMaster2);
    }
}
