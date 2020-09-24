package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.UserMaster;
import com.machbees.repository.UserMasterRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserMasterResourceIT {

    private static final String DEFAULT_EMAIL_ID = ":7TGFc@z6.fY";
    private static final String UPDATED_EMAIL_ID = "4qW@g.n>";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserMasterMockMvc;

    private UserMaster userMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserMaster createEntity(EntityManager em) {
        UserMaster userMaster = new UserMaster()
            .emailId(DEFAULT_EMAIL_ID)
            .password(DEFAULT_PASSWORD)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedDate(DEFAULT_UPDATED_DATE);
        return userMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserMaster createUpdatedEntity(EntityManager em) {
        UserMaster userMaster = new UserMaster()
            .emailId(UPDATED_EMAIL_ID)
            .password(UPDATED_PASSWORD)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE);
        return userMaster;
    }

    @BeforeEach
    public void initTest() {
        userMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserMaster() throws Exception {
        int databaseSizeBeforeCreate = userMasterRepository.findAll().size();
        // Create the UserMaster
        restUserMasterMockMvc.perform(post("/api/user-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMaster)))
            .andExpect(status().isCreated());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeCreate + 1);
        UserMaster testUserMaster = userMasterList.get(userMasterList.size() - 1);
        assertThat(testUserMaster.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testUserMaster.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUserMaster.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testUserMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void createUserMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userMasterRepository.findAll().size();

        // Create the UserMaster with an existing ID
        userMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserMasterMockMvc.perform(post("/api/user-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMaster)))
            .andExpect(status().isBadRequest());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEmailIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userMasterRepository.findAll().size();
        // set the field null
        userMaster.setEmailId(null);

        // Create the UserMaster, which fails.


        restUserMasterMockMvc.perform(post("/api/user-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMaster)))
            .andExpect(status().isBadRequest());

        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = userMasterRepository.findAll().size();
        // set the field null
        userMaster.setPassword(null);

        // Create the UserMaster, which fails.


        restUserMasterMockMvc.perform(post("/api/user-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMaster)))
            .andExpect(status().isBadRequest());

        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserMasters() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        // Get all the userMasterList
        restUserMasterMockMvc.perform(get("/api/user-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].emailId").value(hasItem(DEFAULT_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getUserMaster() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        // Get the userMaster
        restUserMasterMockMvc.perform(get("/api/user-masters/{id}", userMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userMaster.getId().intValue()))
            .andExpect(jsonPath("$.emailId").value(DEFAULT_EMAIL_ID))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUserMaster() throws Exception {
        // Get the userMaster
        restUserMasterMockMvc.perform(get("/api/user-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserMaster() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();

        // Update the userMaster
        UserMaster updatedUserMaster = userMasterRepository.findById(userMaster.getId()).get();
        // Disconnect from session so that the updates on updatedUserMaster are not directly saved in db
        em.detach(updatedUserMaster);
        updatedUserMaster
            .emailId(UPDATED_EMAIL_ID)
            .password(UPDATED_PASSWORD)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedDate(UPDATED_UPDATED_DATE);

        restUserMasterMockMvc.perform(put("/api/user-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserMaster)))
            .andExpect(status().isOk());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
        UserMaster testUserMaster = userMasterList.get(userMasterList.size() - 1);
        assertThat(testUserMaster.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testUserMaster.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUserMaster.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testUserMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserMaster() throws Exception {
        int databaseSizeBeforeUpdate = userMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserMasterMockMvc.perform(put("/api/user-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userMaster)))
            .andExpect(status().isBadRequest());

        // Validate the UserMaster in the database
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserMaster() throws Exception {
        // Initialize the database
        userMasterRepository.saveAndFlush(userMaster);

        int databaseSizeBeforeDelete = userMasterRepository.findAll().size();

        // Delete the userMaster
        restUserMasterMockMvc.perform(delete("/api/user-masters/{id}", userMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserMaster> userMasterList = userMasterRepository.findAll();
        assertThat(userMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
