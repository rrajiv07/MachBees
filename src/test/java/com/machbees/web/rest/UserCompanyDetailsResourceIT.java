package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.UserCompanyDetails;
import com.machbees.repository.UserCompanyDetailsRepository;

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
 * Integration tests for the {@link UserCompanyDetailsResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserCompanyDetailsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_VAT = "AAAAAAAAAA";
    private static final String UPDATED_VAT = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_SKYPE = "AAAAAAAAAA";
    private static final String UPDATED_SKYPE = "BBBBBBBBBB";

    @Autowired
    private UserCompanyDetailsRepository userCompanyDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserCompanyDetailsMockMvc;

    private UserCompanyDetails userCompanyDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCompanyDetails createEntity(EntityManager em) {
        UserCompanyDetails userCompanyDetails = new UserCompanyDetails()
            .name(DEFAULT_NAME)
            .website(DEFAULT_WEBSITE)
            .description(DEFAULT_DESCRIPTION)
            .address(DEFAULT_ADDRESS)
            .vat(DEFAULT_VAT)
            .mobile(DEFAULT_MOBILE)
            .linkedin(DEFAULT_LINKEDIN)
            .twitter(DEFAULT_TWITTER)
            .skype(DEFAULT_SKYPE);
        return userCompanyDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCompanyDetails createUpdatedEntity(EntityManager em) {
        UserCompanyDetails userCompanyDetails = new UserCompanyDetails()
            .name(UPDATED_NAME)
            .website(UPDATED_WEBSITE)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS)
            .vat(UPDATED_VAT)
            .mobile(UPDATED_MOBILE)
            .linkedin(UPDATED_LINKEDIN)
            .twitter(UPDATED_TWITTER)
            .skype(UPDATED_SKYPE);
        return userCompanyDetails;
    }

    @BeforeEach
    public void initTest() {
        userCompanyDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserCompanyDetails() throws Exception {
        int databaseSizeBeforeCreate = userCompanyDetailsRepository.findAll().size();
        // Create the UserCompanyDetails
        restUserCompanyDetailsMockMvc.perform(post("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompanyDetails)))
            .andExpect(status().isCreated());

        // Validate the UserCompanyDetails in the database
        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        UserCompanyDetails testUserCompanyDetails = userCompanyDetailsList.get(userCompanyDetailsList.size() - 1);
        assertThat(testUserCompanyDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserCompanyDetails.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testUserCompanyDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testUserCompanyDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testUserCompanyDetails.getVat()).isEqualTo(DEFAULT_VAT);
        assertThat(testUserCompanyDetails.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testUserCompanyDetails.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testUserCompanyDetails.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testUserCompanyDetails.getSkype()).isEqualTo(DEFAULT_SKYPE);
    }

    @Test
    @Transactional
    public void createUserCompanyDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCompanyDetailsRepository.findAll().size();

        // Create the UserCompanyDetails with an existing ID
        userCompanyDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCompanyDetailsMockMvc.perform(post("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompanyDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserCompanyDetails in the database
        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCompanyDetailsRepository.findAll().size();
        // set the field null
        userCompanyDetails.setName(null);

        // Create the UserCompanyDetails, which fails.


        restUserCompanyDetailsMockMvc.perform(post("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompanyDetails)))
            .andExpect(status().isBadRequest());

        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCompanyDetailsRepository.findAll().size();
        // set the field null
        userCompanyDetails.setAddress(null);

        // Create the UserCompanyDetails, which fails.


        restUserCompanyDetailsMockMvc.perform(post("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompanyDetails)))
            .andExpect(status().isBadRequest());

        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVatIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCompanyDetailsRepository.findAll().size();
        // set the field null
        userCompanyDetails.setVat(null);

        // Create the UserCompanyDetails, which fails.


        restUserCompanyDetailsMockMvc.perform(post("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompanyDetails)))
            .andExpect(status().isBadRequest());

        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCompanyDetailsRepository.findAll().size();
        // set the field null
        userCompanyDetails.setMobile(null);

        // Create the UserCompanyDetails, which fails.


        restUserCompanyDetailsMockMvc.perform(post("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompanyDetails)))
            .andExpect(status().isBadRequest());

        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserCompanyDetails() throws Exception {
        // Initialize the database
        userCompanyDetailsRepository.saveAndFlush(userCompanyDetails);

        // Get all the userCompanyDetailsList
        restUserCompanyDetailsMockMvc.perform(get("/api/user-company-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCompanyDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].vat").value(hasItem(DEFAULT_VAT)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER)))
            .andExpect(jsonPath("$.[*].skype").value(hasItem(DEFAULT_SKYPE)));
    }
    
    @Test
    @Transactional
    public void getUserCompanyDetails() throws Exception {
        // Initialize the database
        userCompanyDetailsRepository.saveAndFlush(userCompanyDetails);

        // Get the userCompanyDetails
        restUserCompanyDetailsMockMvc.perform(get("/api/user-company-details/{id}", userCompanyDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userCompanyDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.vat").value(DEFAULT_VAT))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER))
            .andExpect(jsonPath("$.skype").value(DEFAULT_SKYPE));
    }
    @Test
    @Transactional
    public void getNonExistingUserCompanyDetails() throws Exception {
        // Get the userCompanyDetails
        restUserCompanyDetailsMockMvc.perform(get("/api/user-company-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserCompanyDetails() throws Exception {
        // Initialize the database
        userCompanyDetailsRepository.saveAndFlush(userCompanyDetails);

        int databaseSizeBeforeUpdate = userCompanyDetailsRepository.findAll().size();

        // Update the userCompanyDetails
        UserCompanyDetails updatedUserCompanyDetails = userCompanyDetailsRepository.findById(userCompanyDetails.getId()).get();
        // Disconnect from session so that the updates on updatedUserCompanyDetails are not directly saved in db
        em.detach(updatedUserCompanyDetails);
        updatedUserCompanyDetails
            .name(UPDATED_NAME)
            .website(UPDATED_WEBSITE)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS)
            .vat(UPDATED_VAT)
            .mobile(UPDATED_MOBILE)
            .linkedin(UPDATED_LINKEDIN)
            .twitter(UPDATED_TWITTER)
            .skype(UPDATED_SKYPE);

        restUserCompanyDetailsMockMvc.perform(put("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserCompanyDetails)))
            .andExpect(status().isOk());

        // Validate the UserCompanyDetails in the database
        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeUpdate);
        UserCompanyDetails testUserCompanyDetails = userCompanyDetailsList.get(userCompanyDetailsList.size() - 1);
        assertThat(testUserCompanyDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserCompanyDetails.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testUserCompanyDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUserCompanyDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testUserCompanyDetails.getVat()).isEqualTo(UPDATED_VAT);
        assertThat(testUserCompanyDetails.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testUserCompanyDetails.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testUserCompanyDetails.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testUserCompanyDetails.getSkype()).isEqualTo(UPDATED_SKYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserCompanyDetails() throws Exception {
        int databaseSizeBeforeUpdate = userCompanyDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserCompanyDetailsMockMvc.perform(put("/api/user-company-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCompanyDetails)))
            .andExpect(status().isBadRequest());

        // Validate the UserCompanyDetails in the database
        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserCompanyDetails() throws Exception {
        // Initialize the database
        userCompanyDetailsRepository.saveAndFlush(userCompanyDetails);

        int databaseSizeBeforeDelete = userCompanyDetailsRepository.findAll().size();

        // Delete the userCompanyDetails
        restUserCompanyDetailsMockMvc.perform(delete("/api/user-company-details/{id}", userCompanyDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserCompanyDetails> userCompanyDetailsList = userCompanyDetailsRepository.findAll();
        assertThat(userCompanyDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
