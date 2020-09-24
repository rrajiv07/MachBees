package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class UserPersonalDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPersonalDetails.class);
        UserPersonalDetails userPersonalDetails1 = new UserPersonalDetails();
        userPersonalDetails1.setId(1L);
        UserPersonalDetails userPersonalDetails2 = new UserPersonalDetails();
        userPersonalDetails2.setId(userPersonalDetails1.getId());
        assertThat(userPersonalDetails1).isEqualTo(userPersonalDetails2);
        userPersonalDetails2.setId(2L);
        assertThat(userPersonalDetails1).isNotEqualTo(userPersonalDetails2);
        userPersonalDetails1.setId(null);
        assertThat(userPersonalDetails1).isNotEqualTo(userPersonalDetails2);
    }
}
