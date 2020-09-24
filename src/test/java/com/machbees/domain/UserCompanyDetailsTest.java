package com.machbees.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.machbees.web.rest.TestUtil;

public class UserCompanyDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCompanyDetails.class);
        UserCompanyDetails userCompanyDetails1 = new UserCompanyDetails();
        userCompanyDetails1.setId(1L);
        UserCompanyDetails userCompanyDetails2 = new UserCompanyDetails();
        userCompanyDetails2.setId(userCompanyDetails1.getId());
        assertThat(userCompanyDetails1).isEqualTo(userCompanyDetails2);
        userCompanyDetails2.setId(2L);
        assertThat(userCompanyDetails1).isNotEqualTo(userCompanyDetails2);
        userCompanyDetails1.setId(null);
        assertThat(userCompanyDetails1).isNotEqualTo(userCompanyDetails2);
    }
}
