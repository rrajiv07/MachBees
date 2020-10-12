package com.machbees.web.rest;

import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.machbees.MachBeesApp;
import com.machbees.domain.UserMaster;
import com.machbees.repository.UserMasterRepository;
import com.machbees.service.dto.UserDetailsFromRequest;

@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RegistrationControllerIT {
	 private static final String DEFAULT_EMAIL = "test0001@gmail.com";
	 
	 
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private RegisterController register;
	@Autowired
	private UserMasterRepository userMaster;

	
	@Test
	@Transactional
	public void testSetEmailPassword() throws Exception {
		int databaseSizeBeforeCreate = userMaster.findAll().size();
		UserDetailsFromRequest setEmailPassword = new UserDetailsFromRequest();
		// setEmailPassword.setEmailId("k@gmail.com");
		setEmailPassword.setEmailId(DEFAULT_EMAIL);
		setEmailPassword.setPassword("123");

		//assertNotNull(mockMvc);
		
		
		mockMvc.perform(post("/api/common/registration/setEmailPassword")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(TestUtil.convertObjectToJsonBytes(setEmailPassword)))
	            .andExpect(status().isCreated());
		
		 // Validate the CategoryMetadata in the database
        List<UserMaster> userMetadataList = userMaster.findAll();
        assertThat(userMetadataList).hasSize(databaseSizeBeforeCreate + 1);
        UserMaster testCategoryMetadata = userMetadataList.get(userMetadataList.size() - 1);
        assertThat(testCategoryMetadata.getEmailId()).isEqualTo(DEFAULT_EMAIL);
        System.out.println("email: "+testCategoryMetadata.getEmailId());
        
	}

	@Test
	@Transactional
	public void testfetchProfileCategory() throws Exception {
		assertNotNull("pf", register.fetchProfileCategory(0));
	}

}
