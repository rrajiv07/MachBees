package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectFeatureMaster;
import com.machbees.repository.ProjectFeatureMasterRepository;

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
 * Integration tests for the {@link ProjectFeatureMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectFeatureMasterResourceIT {

    private static final String DEFAULT_FEATURE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FEATURE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FEATURE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FEATURE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FEATURE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_FEATURE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ProjectFeatureMasterRepository projectFeatureMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectFeatureMasterMockMvc;

    private ProjectFeatureMaster projectFeatureMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectFeatureMaster createEntity(EntityManager em) {
        ProjectFeatureMaster projectFeatureMaster = new ProjectFeatureMaster()
            .featureCode(DEFAULT_FEATURE_CODE)
            .featureName(DEFAULT_FEATURE_NAME)
            .featureDescription(DEFAULT_FEATURE_DESCRIPTION);
        return projectFeatureMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectFeatureMaster createUpdatedEntity(EntityManager em) {
        ProjectFeatureMaster projectFeatureMaster = new ProjectFeatureMaster()
            .featureCode(UPDATED_FEATURE_CODE)
            .featureName(UPDATED_FEATURE_NAME)
            .featureDescription(UPDATED_FEATURE_DESCRIPTION);
        return projectFeatureMaster;
    }

    @BeforeEach
    public void initTest() {
        projectFeatureMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectFeatureMaster() throws Exception {
        int databaseSizeBeforeCreate = projectFeatureMasterRepository.findAll().size();
        // Create the ProjectFeatureMaster
        restProjectFeatureMasterMockMvc.perform(post("/api/project-feature-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureMaster)))
            .andExpect(status().isCreated());

        // Validate the ProjectFeatureMaster in the database
        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectFeatureMaster testProjectFeatureMaster = projectFeatureMasterList.get(projectFeatureMasterList.size() - 1);
        assertThat(testProjectFeatureMaster.getFeatureCode()).isEqualTo(DEFAULT_FEATURE_CODE);
        assertThat(testProjectFeatureMaster.getFeatureName()).isEqualTo(DEFAULT_FEATURE_NAME);
        assertThat(testProjectFeatureMaster.getFeatureDescription()).isEqualTo(DEFAULT_FEATURE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createProjectFeatureMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectFeatureMasterRepository.findAll().size();

        // Create the ProjectFeatureMaster with an existing ID
        projectFeatureMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectFeatureMasterMockMvc.perform(post("/api/project-feature-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectFeatureMaster in the database
        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFeatureCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectFeatureMasterRepository.findAll().size();
        // set the field null
        projectFeatureMaster.setFeatureCode(null);

        // Create the ProjectFeatureMaster, which fails.


        restProjectFeatureMasterMockMvc.perform(post("/api/project-feature-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFeatureNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectFeatureMasterRepository.findAll().size();
        // set the field null
        projectFeatureMaster.setFeatureName(null);

        // Create the ProjectFeatureMaster, which fails.


        restProjectFeatureMasterMockMvc.perform(post("/api/project-feature-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFeatureDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectFeatureMasterRepository.findAll().size();
        // set the field null
        projectFeatureMaster.setFeatureDescription(null);

        // Create the ProjectFeatureMaster, which fails.


        restProjectFeatureMasterMockMvc.perform(post("/api/project-feature-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectFeatureMasters() throws Exception {
        // Initialize the database
        projectFeatureMasterRepository.saveAndFlush(projectFeatureMaster);

        // Get all the projectFeatureMasterList
        restProjectFeatureMasterMockMvc.perform(get("/api/project-feature-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectFeatureMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].featureCode").value(hasItem(DEFAULT_FEATURE_CODE)))
            .andExpect(jsonPath("$.[*].featureName").value(hasItem(DEFAULT_FEATURE_NAME)))
            .andExpect(jsonPath("$.[*].featureDescription").value(hasItem(DEFAULT_FEATURE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getProjectFeatureMaster() throws Exception {
        // Initialize the database
        projectFeatureMasterRepository.saveAndFlush(projectFeatureMaster);

        // Get the projectFeatureMaster
        restProjectFeatureMasterMockMvc.perform(get("/api/project-feature-masters/{id}", projectFeatureMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectFeatureMaster.getId().intValue()))
            .andExpect(jsonPath("$.featureCode").value(DEFAULT_FEATURE_CODE))
            .andExpect(jsonPath("$.featureName").value(DEFAULT_FEATURE_NAME))
            .andExpect(jsonPath("$.featureDescription").value(DEFAULT_FEATURE_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingProjectFeatureMaster() throws Exception {
        // Get the projectFeatureMaster
        restProjectFeatureMasterMockMvc.perform(get("/api/project-feature-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectFeatureMaster() throws Exception {
        // Initialize the database
        projectFeatureMasterRepository.saveAndFlush(projectFeatureMaster);

        int databaseSizeBeforeUpdate = projectFeatureMasterRepository.findAll().size();

        // Update the projectFeatureMaster
        ProjectFeatureMaster updatedProjectFeatureMaster = projectFeatureMasterRepository.findById(projectFeatureMaster.getId()).get();
        // Disconnect from session so that the updates on updatedProjectFeatureMaster are not directly saved in db
        em.detach(updatedProjectFeatureMaster);
        updatedProjectFeatureMaster
            .featureCode(UPDATED_FEATURE_CODE)
            .featureName(UPDATED_FEATURE_NAME)
            .featureDescription(UPDATED_FEATURE_DESCRIPTION);

        restProjectFeatureMasterMockMvc.perform(put("/api/project-feature-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectFeatureMaster)))
            .andExpect(status().isOk());

        // Validate the ProjectFeatureMaster in the database
        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeUpdate);
        ProjectFeatureMaster testProjectFeatureMaster = projectFeatureMasterList.get(projectFeatureMasterList.size() - 1);
        assertThat(testProjectFeatureMaster.getFeatureCode()).isEqualTo(UPDATED_FEATURE_CODE);
        assertThat(testProjectFeatureMaster.getFeatureName()).isEqualTo(UPDATED_FEATURE_NAME);
        assertThat(testProjectFeatureMaster.getFeatureDescription()).isEqualTo(UPDATED_FEATURE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectFeatureMaster() throws Exception {
        int databaseSizeBeforeUpdate = projectFeatureMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectFeatureMasterMockMvc.perform(put("/api/project-feature-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectFeatureMaster in the database
        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectFeatureMaster() throws Exception {
        // Initialize the database
        projectFeatureMasterRepository.saveAndFlush(projectFeatureMaster);

        int databaseSizeBeforeDelete = projectFeatureMasterRepository.findAll().size();

        // Delete the projectFeatureMaster
        restProjectFeatureMasterMockMvc.perform(delete("/api/project-feature-masters/{id}", projectFeatureMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectFeatureMaster> projectFeatureMasterList = projectFeatureMasterRepository.findAll();
        assertThat(projectFeatureMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
