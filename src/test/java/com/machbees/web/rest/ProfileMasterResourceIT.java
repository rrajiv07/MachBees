package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProfileMaster;
import com.machbees.repository.ProfileMasterRepository;

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
 * Integration tests for the {@link ProfileMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProfileMasterResourceIT {

    private static final String DEFAULT_PROFILE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROFILE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROFILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROFILE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PROFILE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ProfileMasterRepository profileMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfileMasterMockMvc;

    private ProfileMaster profileMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileMaster createEntity(EntityManager em) {
        ProfileMaster profileMaster = new ProfileMaster()
            .profileCode(DEFAULT_PROFILE_CODE)
            .profileName(DEFAULT_PROFILE_NAME)
            .profileDescription(DEFAULT_PROFILE_DESCRIPTION);
        return profileMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileMaster createUpdatedEntity(EntityManager em) {
        ProfileMaster profileMaster = new ProfileMaster()
            .profileCode(UPDATED_PROFILE_CODE)
            .profileName(UPDATED_PROFILE_NAME)
            .profileDescription(UPDATED_PROFILE_DESCRIPTION);
        return profileMaster;
    }

    @BeforeEach
    public void initTest() {
        profileMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfileMaster() throws Exception {
        int databaseSizeBeforeCreate = profileMasterRepository.findAll().size();
        // Create the ProfileMaster
        restProfileMasterMockMvc.perform(post("/api/profile-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileMaster)))
            .andExpect(status().isCreated());

        // Validate the ProfileMaster in the database
        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProfileMaster testProfileMaster = profileMasterList.get(profileMasterList.size() - 1);
        assertThat(testProfileMaster.getProfileCode()).isEqualTo(DEFAULT_PROFILE_CODE);
        assertThat(testProfileMaster.getProfileName()).isEqualTo(DEFAULT_PROFILE_NAME);
        assertThat(testProfileMaster.getProfileDescription()).isEqualTo(DEFAULT_PROFILE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createProfileMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profileMasterRepository.findAll().size();

        // Create the ProfileMaster with an existing ID
        profileMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfileMasterMockMvc.perform(post("/api/profile-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProfileMaster in the database
        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProfileCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileMasterRepository.findAll().size();
        // set the field null
        profileMaster.setProfileCode(null);

        // Create the ProfileMaster, which fails.


        restProfileMasterMockMvc.perform(post("/api/profile-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileMaster)))
            .andExpect(status().isBadRequest());

        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProfileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileMasterRepository.findAll().size();
        // set the field null
        profileMaster.setProfileName(null);

        // Create the ProfileMaster, which fails.


        restProfileMasterMockMvc.perform(post("/api/profile-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileMaster)))
            .andExpect(status().isBadRequest());

        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProfileDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileMasterRepository.findAll().size();
        // set the field null
        profileMaster.setProfileDescription(null);

        // Create the ProfileMaster, which fails.


        restProfileMasterMockMvc.perform(post("/api/profile-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileMaster)))
            .andExpect(status().isBadRequest());

        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfileMasters() throws Exception {
        // Initialize the database
        profileMasterRepository.saveAndFlush(profileMaster);

        // Get all the profileMasterList
        restProfileMasterMockMvc.perform(get("/api/profile-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profileMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].profileCode").value(hasItem(DEFAULT_PROFILE_CODE)))
            .andExpect(jsonPath("$.[*].profileName").value(hasItem(DEFAULT_PROFILE_NAME)))
            .andExpect(jsonPath("$.[*].profileDescription").value(hasItem(DEFAULT_PROFILE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getProfileMaster() throws Exception {
        // Initialize the database
        profileMasterRepository.saveAndFlush(profileMaster);

        // Get the profileMaster
        restProfileMasterMockMvc.perform(get("/api/profile-masters/{id}", profileMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(profileMaster.getId().intValue()))
            .andExpect(jsonPath("$.profileCode").value(DEFAULT_PROFILE_CODE))
            .andExpect(jsonPath("$.profileName").value(DEFAULT_PROFILE_NAME))
            .andExpect(jsonPath("$.profileDescription").value(DEFAULT_PROFILE_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingProfileMaster() throws Exception {
        // Get the profileMaster
        restProfileMasterMockMvc.perform(get("/api/profile-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfileMaster() throws Exception {
        // Initialize the database
        profileMasterRepository.saveAndFlush(profileMaster);

        int databaseSizeBeforeUpdate = profileMasterRepository.findAll().size();

        // Update the profileMaster
        ProfileMaster updatedProfileMaster = profileMasterRepository.findById(profileMaster.getId()).get();
        // Disconnect from session so that the updates on updatedProfileMaster are not directly saved in db
        em.detach(updatedProfileMaster);
        updatedProfileMaster
            .profileCode(UPDATED_PROFILE_CODE)
            .profileName(UPDATED_PROFILE_NAME)
            .profileDescription(UPDATED_PROFILE_DESCRIPTION);

        restProfileMasterMockMvc.perform(put("/api/profile-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfileMaster)))
            .andExpect(status().isOk());

        // Validate the ProfileMaster in the database
        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeUpdate);
        ProfileMaster testProfileMaster = profileMasterList.get(profileMasterList.size() - 1);
        assertThat(testProfileMaster.getProfileCode()).isEqualTo(UPDATED_PROFILE_CODE);
        assertThat(testProfileMaster.getProfileName()).isEqualTo(UPDATED_PROFILE_NAME);
        assertThat(testProfileMaster.getProfileDescription()).isEqualTo(UPDATED_PROFILE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingProfileMaster() throws Exception {
        int databaseSizeBeforeUpdate = profileMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfileMasterMockMvc.perform(put("/api/profile-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProfileMaster in the database
        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfileMaster() throws Exception {
        // Initialize the database
        profileMasterRepository.saveAndFlush(profileMaster);

        int databaseSizeBeforeDelete = profileMasterRepository.findAll().size();

        // Delete the profileMaster
        restProfileMasterMockMvc.perform(delete("/api/profile-masters/{id}", profileMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProfileMaster> profileMasterList = profileMasterRepository.findAll();
        assertThat(profileMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
