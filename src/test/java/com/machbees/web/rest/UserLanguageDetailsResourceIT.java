package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.UserLanguageDetails;
import com.machbees.repository.UserLanguageDetailsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserLanguageDetailsResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserLanguageDetailsResourceIT {

    @Autowired
    private UserLanguageDetailsRepository userLanguageDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserLanguageDetailsMockMvc;

    private UserLanguageDetails userLanguageDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserLanguageDetails createEntity(EntityManager em) {
        UserLanguageDetails userLanguageDetails = new UserLanguageDetails();
        return userLanguageDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserLanguageDetails createUpdatedEntity(EntityManager em) {
        UserLanguageDetails userLanguageDetails = new UserLanguageDetails();
        return userLanguageDetails;
    }

    @BeforeEach
    public void initTest() {
        userLanguageDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserLanguageDetails() throws Exception {
        int databaseSizeBeforeCreate = userLanguageDetailsRepository.findAll().size();
        // Create the UserLanguageDetails
        restUserLanguageDetailsMockMvc.perform(post("/api/user-language-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLanguageDetails)))
            .andExpect(status().isCreated());

        // Validate the UserLanguageDetails in the database
        List<UserLanguageDetails> userLanguageDetailsList = userLanguageDetailsRepository.findAll();
        assertThat(userLanguageDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        UserLanguageDetails testUserLanguageDetails = userLanguageDetailsList.get(userLanguageDetailsList.size() - 1);
    }

    @Test
    @Transactional
    public void createUserLanguageDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userLanguageDetailsRepository.findAll().size();

        // Create the UserLanguageDetails with an existing ID
        userLanguageDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserLanguageDetailsMockMvc.perform(post("/api/user-language-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLanguageDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserLanguageDetails in the database
        List<UserLanguageDetails> userLanguageDetailsList = userLanguageDetailsRepository.findAll();
        assertThat(userLanguageDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserLanguageDetails() throws Exception {
        // Initialize the database
        userLanguageDetailsRepository.saveAndFlush(userLanguageDetails);

        // Get all the userLanguageDetailsList
        restUserLanguageDetailsMockMvc.perform(get("/api/user-language-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userLanguageDetails.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUserLanguageDetails() throws Exception {
        // Initialize the database
        userLanguageDetailsRepository.saveAndFlush(userLanguageDetails);

        // Get the userLanguageDetails
        restUserLanguageDetailsMockMvc.perform(get("/api/user-language-details/{id}", userLanguageDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userLanguageDetails.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUserLanguageDetails() throws Exception {
        // Get the userLanguageDetails
        restUserLanguageDetailsMockMvc.perform(get("/api/user-language-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserLanguageDetails() throws Exception {
        // Initialize the database
        userLanguageDetailsRepository.saveAndFlush(userLanguageDetails);

        int databaseSizeBeforeUpdate = userLanguageDetailsRepository.findAll().size();

        // Update the userLanguageDetails
        UserLanguageDetails updatedUserLanguageDetails = userLanguageDetailsRepository.findById(userLanguageDetails.getId()).get();
        // Disconnect from session so that the updates on updatedUserLanguageDetails are not directly saved in db
        em.detach(updatedUserLanguageDetails);

        restUserLanguageDetailsMockMvc.perform(put("/api/user-language-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserLanguageDetails)))
            .andExpect(status().isOk());

        // Validate the UserLanguageDetails in the database
        List<UserLanguageDetails> userLanguageDetailsList = userLanguageDetailsRepository.findAll();
        assertThat(userLanguageDetailsList).hasSize(databaseSizeBeforeUpdate);
        UserLanguageDetails testUserLanguageDetails = userLanguageDetailsList.get(userLanguageDetailsList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingUserLanguageDetails() throws Exception {
        int databaseSizeBeforeUpdate = userLanguageDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserLanguageDetailsMockMvc.perform(put("/api/user-language-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLanguageDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserLanguageDetails in the database
        List<UserLanguageDetails> userLanguageDetailsList = userLanguageDetailsRepository.findAll();
        assertThat(userLanguageDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserLanguageDetails() throws Exception {
        // Initialize the database
        userLanguageDetailsRepository.saveAndFlush(userLanguageDetails);

        int databaseSizeBeforeDelete = userLanguageDetailsRepository.findAll().size();

        // Delete the userLanguageDetails
        restUserLanguageDetailsMockMvc.perform(delete("/api/user-language-details/{id}", userLanguageDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserLanguageDetails> userLanguageDetailsList = userLanguageDetailsRepository.findAll();
        assertThat(userLanguageDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
