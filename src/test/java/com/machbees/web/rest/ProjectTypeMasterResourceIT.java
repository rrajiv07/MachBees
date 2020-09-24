package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectTypeMaster;
import com.machbees.repository.ProjectTypeMasterRepository;

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
 * Integration tests for the {@link ProjectTypeMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectTypeMasterResourceIT {

    private static final String DEFAULT_PROJECT_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_TYPE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_TYPE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ProjectTypeMasterRepository projectTypeMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectTypeMasterMockMvc;

    private ProjectTypeMaster projectTypeMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectTypeMaster createEntity(EntityManager em) {
        ProjectTypeMaster projectTypeMaster = new ProjectTypeMaster()
            .projectTypeCode(DEFAULT_PROJECT_TYPE_CODE)
            .projectTypeName(DEFAULT_PROJECT_TYPE_NAME)
            .projectTypeDescription(DEFAULT_PROJECT_TYPE_DESCRIPTION);
        return projectTypeMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectTypeMaster createUpdatedEntity(EntityManager em) {
        ProjectTypeMaster projectTypeMaster = new ProjectTypeMaster()
            .projectTypeCode(UPDATED_PROJECT_TYPE_CODE)
            .projectTypeName(UPDATED_PROJECT_TYPE_NAME)
            .projectTypeDescription(UPDATED_PROJECT_TYPE_DESCRIPTION);
        return projectTypeMaster;
    }

    @BeforeEach
    public void initTest() {
        projectTypeMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = projectTypeMasterRepository.findAll().size();
        // Create the ProjectTypeMaster
        restProjectTypeMasterMockMvc.perform(post("/api/project-type-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectTypeMaster)))
            .andExpect(status().isCreated());

        // Validate the ProjectTypeMaster in the database
        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectTypeMaster testProjectTypeMaster = projectTypeMasterList.get(projectTypeMasterList.size() - 1);
        assertThat(testProjectTypeMaster.getProjectTypeCode()).isEqualTo(DEFAULT_PROJECT_TYPE_CODE);
        assertThat(testProjectTypeMaster.getProjectTypeName()).isEqualTo(DEFAULT_PROJECT_TYPE_NAME);
        assertThat(testProjectTypeMaster.getProjectTypeDescription()).isEqualTo(DEFAULT_PROJECT_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createProjectTypeMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectTypeMasterRepository.findAll().size();

        // Create the ProjectTypeMaster with an existing ID
        projectTypeMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectTypeMasterMockMvc.perform(post("/api/project-type-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectTypeMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectTypeMaster in the database
        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProjectTypeCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectTypeMasterRepository.findAll().size();
        // set the field null
        projectTypeMaster.setProjectTypeCode(null);

        // Create the ProjectTypeMaster, which fails.


        restProjectTypeMasterMockMvc.perform(post("/api/project-type-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectTypeMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProjectTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectTypeMasterRepository.findAll().size();
        // set the field null
        projectTypeMaster.setProjectTypeName(null);

        // Create the ProjectTypeMaster, which fails.


        restProjectTypeMasterMockMvc.perform(post("/api/project-type-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectTypeMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProjectTypeDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectTypeMasterRepository.findAll().size();
        // set the field null
        projectTypeMaster.setProjectTypeDescription(null);

        // Create the ProjectTypeMaster, which fails.


        restProjectTypeMasterMockMvc.perform(post("/api/project-type-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectTypeMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectTypeMasters() throws Exception {
        // Initialize the database
        projectTypeMasterRepository.saveAndFlush(projectTypeMaster);

        // Get all the projectTypeMasterList
        restProjectTypeMasterMockMvc.perform(get("/api/project-type-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectTypeMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectTypeCode").value(hasItem(DEFAULT_PROJECT_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].projectTypeName").value(hasItem(DEFAULT_PROJECT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].projectTypeDescription").value(hasItem(DEFAULT_PROJECT_TYPE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getProjectTypeMaster() throws Exception {
        // Initialize the database
        projectTypeMasterRepository.saveAndFlush(projectTypeMaster);

        // Get the projectTypeMaster
        restProjectTypeMasterMockMvc.perform(get("/api/project-type-masters/{id}", projectTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.projectTypeCode").value(DEFAULT_PROJECT_TYPE_CODE))
            .andExpect(jsonPath("$.projectTypeName").value(DEFAULT_PROJECT_TYPE_NAME))
            .andExpect(jsonPath("$.projectTypeDescription").value(DEFAULT_PROJECT_TYPE_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingProjectTypeMaster() throws Exception {
        // Get the projectTypeMaster
        restProjectTypeMasterMockMvc.perform(get("/api/project-type-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectTypeMaster() throws Exception {
        // Initialize the database
        projectTypeMasterRepository.saveAndFlush(projectTypeMaster);

        int databaseSizeBeforeUpdate = projectTypeMasterRepository.findAll().size();

        // Update the projectTypeMaster
        ProjectTypeMaster updatedProjectTypeMaster = projectTypeMasterRepository.findById(projectTypeMaster.getId()).get();
        // Disconnect from session so that the updates on updatedProjectTypeMaster are not directly saved in db
        em.detach(updatedProjectTypeMaster);
        updatedProjectTypeMaster
            .projectTypeCode(UPDATED_PROJECT_TYPE_CODE)
            .projectTypeName(UPDATED_PROJECT_TYPE_NAME)
            .projectTypeDescription(UPDATED_PROJECT_TYPE_DESCRIPTION);

        restProjectTypeMasterMockMvc.perform(put("/api/project-type-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectTypeMaster)))
            .andExpect(status().isOk());

        // Validate the ProjectTypeMaster in the database
        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
        ProjectTypeMaster testProjectTypeMaster = projectTypeMasterList.get(projectTypeMasterList.size() - 1);
        assertThat(testProjectTypeMaster.getProjectTypeCode()).isEqualTo(UPDATED_PROJECT_TYPE_CODE);
        assertThat(testProjectTypeMaster.getProjectTypeName()).isEqualTo(UPDATED_PROJECT_TYPE_NAME);
        assertThat(testProjectTypeMaster.getProjectTypeDescription()).isEqualTo(UPDATED_PROJECT_TYPE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectTypeMaster() throws Exception {
        int databaseSizeBeforeUpdate = projectTypeMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectTypeMasterMockMvc.perform(put("/api/project-type-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectTypeMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectTypeMaster in the database
        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectTypeMaster() throws Exception {
        // Initialize the database
        projectTypeMasterRepository.saveAndFlush(projectTypeMaster);

        int databaseSizeBeforeDelete = projectTypeMasterRepository.findAll().size();

        // Delete the projectTypeMaster
        restProjectTypeMasterMockMvc.perform(delete("/api/project-type-masters/{id}", projectTypeMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectTypeMaster> projectTypeMasterList = projectTypeMasterRepository.findAll();
        assertThat(projectTypeMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
