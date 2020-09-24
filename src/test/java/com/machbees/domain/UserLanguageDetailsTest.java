package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class UserLanguageDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserLanguageDetails.class);
        UserLanguageDetails userLanguageDetails1 = new UserLanguageDetails();
        userLanguageDetails1.setId(1L);
        UserLanguageDetails userLanguageDetails2 = new UserLanguageDetails();
        userLanguageDetails2.setId(userLanguageDetails1.getId());
        assertThat(userLanguageDetails1).isEqualTo(userLanguageDetails2);
        userLanguageDetails2.setId(2L);
        assertThat(userLanguageDetails1).isNotEqualTo(userLanguageDetails2);
        userLanguageDetails1.setId(null);
        assertThat(userLanguageDetails1).isNotEqualTo(userLanguageDetails2);
    }
}
