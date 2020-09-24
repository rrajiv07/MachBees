package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.UserPersonalDetails;
import com.machbees.repository.UserPersonalDetailsRepository;

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
 * Integration tests for the {@link UserPersonalDetailsResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserPersonalDetailsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_SKYPE = "AAAAAAAAAA";
    private static final String UPDATED_SKYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SELF_PRESENTATION = "AAAAAAAAAA";
    private static final String UPDATED_SELF_PRESENTATION = "BBBBBBBBBB";

    private static final String DEFAULT_VIRTUALCV = "AAAAAAAAAA";
    private static final String UPDATED_VIRTUALCV = "BBBBBBBBBB";

    @Autowired
    private UserPersonalDetailsRepository userPersonalDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserPersonalDetailsMockMvc;

    private UserPersonalDetails userPersonalDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPersonalDetails createEntity(EntityManager em) {
        UserPersonalDetails userPersonalDetails = new UserPersonalDetails()
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .address(DEFAULT_ADDRESS)
            .country(DEFAULT_COUNTRY)
            .mobile(DEFAULT_MOBILE)
            .linkedin(DEFAULT_LINKEDIN)
            .twitter(DEFAULT_TWITTER)
            .skype(DEFAULT_SKYPE)
            .selfPresentation(DEFAULT_SELF_PRESENTATION)
            .virtualcv(DEFAULT_VIRTUALCV);
        return userPersonalDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPersonalDetails createUpdatedEntity(EntityManager em) {
        UserPersonalDetails userPersonalDetails = new UserPersonalDetails()
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .mobile(UPDATED_MOBILE)
            .linkedin(UPDATED_LINKEDIN)
            .twitter(UPDATED_TWITTER)
            .skype(UPDATED_SKYPE)
            .selfPresentation(UPDATED_SELF_PRESENTATION)
            .virtualcv(UPDATED_VIRTUALCV);
        return userPersonalDetails;
    }

    @BeforeEach
    public void initTest() {
        userPersonalDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserPersonalDetails() throws Exception {
        int databaseSizeBeforeCreate = userPersonalDetailsRepository.findAll().size();
        // Create the UserPersonalDetails
        restUserPersonalDetailsMockMvc.perform(post("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isCreated());

        // Validate the UserPersonalDetails in the database
        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        UserPersonalDetails testUserPersonalDetails = userPersonalDetailsList.get(userPersonalDetailsList.size() - 1);
        assertThat(testUserPersonalDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserPersonalDetails.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testUserPersonalDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testUserPersonalDetails.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testUserPersonalDetails.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testUserPersonalDetails.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testUserPersonalDetails.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testUserPersonalDetails.getSkype()).isEqualTo(DEFAULT_SKYPE);
        assertThat(testUserPersonalDetails.getSelfPresentation()).isEqualTo(DEFAULT_SELF_PRESENTATION);
        assertThat(testUserPersonalDetails.getVirtualcv()).isEqualTo(DEFAULT_VIRTUALCV);
    }

    @Test
    @Transactional
    public void createUserPersonalDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userPersonalDetailsRepository.findAll().size();

        // Create the UserPersonalDetails with an existing ID
        userPersonalDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserPersonalDetailsMockMvc.perform(post("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserPersonalDetails in the database
        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPersonalDetailsRepository.findAll().size();
        // set the field null
        userPersonalDetails.setName(null);

        // Create the UserPersonalDetails, which fails.


        restUserPersonalDetailsMockMvc.perform(post("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isBadRequest());

        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSurnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPersonalDetailsRepository.findAll().size();
        // set the field null
        userPersonalDetails.setSurname(null);

        // Create the UserPersonalDetails, which fails.


        restUserPersonalDetailsMockMvc.perform(post("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isBadRequest());

        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPersonalDetailsRepository.findAll().size();
        // set the field null
        userPersonalDetails.setAddress(null);

        // Create the UserPersonalDetails, which fails.


        restUserPersonalDetailsMockMvc.perform(post("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isBadRequest());

        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPersonalDetailsRepository.findAll().size();
        // set the field null
        userPersonalDetails.setCountry(null);

        // Create the UserPersonalDetails, which fails.


        restUserPersonalDetailsMockMvc.perform(post("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isBadRequest());

        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = userPersonalDetailsRepository.findAll().size();
        // set the field null
        userPersonalDetails.setMobile(null);

        // Create the UserPersonalDetails, which fails.


        restUserPersonalDetailsMockMvc.perform(post("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isBadRequest());

        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserPersonalDetails() throws Exception {
        // Initialize the database
        userPersonalDetailsRepository.saveAndFlush(userPersonalDetails);

        // Get all the userPersonalDetailsList
        restUserPersonalDetailsMockMvc.perform(get("/api/user-personal-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPersonalDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER)))
            .andExpect(jsonPath("$.[*].skype").value(hasItem(DEFAULT_SKYPE)))
            .andExpect(jsonPath("$.[*].selfPresentation").value(hasItem(DEFAULT_SELF_PRESENTATION)))
            .andExpect(jsonPath("$.[*].virtualcv").value(hasItem(DEFAULT_VIRTUALCV)));
    }
    
    @Test
    @Transactional
    public void getUserPersonalDetails() throws Exception {
        // Initialize the database
        userPersonalDetailsRepository.saveAndFlush(userPersonalDetails);

        // Get the userPersonalDetails
        restUserPersonalDetailsMockMvc.perform(get("/api/user-personal-details/{id}", userPersonalDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userPersonalDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER))
            .andExpect(jsonPath("$.skype").value(DEFAULT_SKYPE))
            .andExpect(jsonPath("$.selfPresentation").value(DEFAULT_SELF_PRESENTATION))
            .andExpect(jsonPath("$.virtualcv").value(DEFAULT_VIRTUALCV));
    }
    @Test
    @Transactional
    public void getNonExistingUserPersonalDetails() throws Exception {
        // Get the userPersonalDetails
        restUserPersonalDetailsMockMvc.perform(get("/api/user-personal-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserPersonalDetails() throws Exception {
        // Initialize the database
        userPersonalDetailsRepository.saveAndFlush(userPersonalDetails);

        int databaseSizeBeforeUpdate = userPersonalDetailsRepository.findAll().size();

        // Update the userPersonalDetails
        UserPersonalDetails updatedUserPersonalDetails = userPersonalDetailsRepository.findById(userPersonalDetails.getId()).get();
        // Disconnect from session so that the updates on updatedUserPersonalDetails are not directly saved in db
        em.detach(updatedUserPersonalDetails);
        updatedUserPersonalDetails
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .mobile(UPDATED_MOBILE)
            .linkedin(UPDATED_LINKEDIN)
            .twitter(UPDATED_TWITTER)
            .skype(UPDATED_SKYPE)
            .selfPresentation(UPDATED_SELF_PRESENTATION)
            .virtualcv(UPDATED_VIRTUALCV);

        restUserPersonalDetailsMockMvc.perform(put("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserPersonalDetails)))
            .andExpect(status().isOk());

        // Validate the UserPersonalDetails in the database
        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeUpdate);
        UserPersonalDetails testUserPersonalDetails = userPersonalDetailsList.get(userPersonalDetailsList.size() - 1);
        assertThat(testUserPersonalDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserPersonalDetails.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testUserPersonalDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testUserPersonalDetails.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testUserPersonalDetails.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testUserPersonalDetails.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testUserPersonalDetails.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testUserPersonalDetails.getSkype()).isEqualTo(UPDATED_SKYPE);
        assertThat(testUserPersonalDetails.getSelfPresentation()).isEqualTo(UPDATED_SELF_PRESENTATION);
        assertThat(testUserPersonalDetails.getVirtualcv()).isEqualTo(UPDATED_VIRTUALCV);
    }

    @Test
    @Transactional
    public void updateNonExistingUserPersonalDetails() throws Exception {
        int databaseSizeBeforeUpdate = userPersonalDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserPersonalDetailsMockMvc.perform(put("/api/user-personal-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonalDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserPersonalDetails in the database
        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserPersonalDetails() throws Exception {
        // Initialize the database
        userPersonalDetailsRepository.saveAndFlush(userPersonalDetails);

        int databaseSizeBeforeDelete = userPersonalDetailsRepository.findAll().size();

        // Delete the userPersonalDetails
        restUserPersonalDetailsMockMvc.perform(delete("/api/user-personal-details/{id}", userPersonalDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserPersonalDetails> userPersonalDetailsList = userPersonalDetailsRepository.findAll();
        assertThat(userPersonalDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
