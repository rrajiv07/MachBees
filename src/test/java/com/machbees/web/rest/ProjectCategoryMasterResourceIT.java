package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectCategoryMaster;
import com.machbees.repository.ProjectCategoryMasterRepository;

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
 * Integration tests for the {@link ProjectCategoryMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectCategoryMasterResourceIT {

    private static final String DEFAULT_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ProjectCategoryMasterRepository projectCategoryMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectCategoryMasterMockMvc;

    private ProjectCategoryMaster projectCategoryMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectCategoryMaster createEntity(EntityManager em) {
        ProjectCategoryMaster projectCategoryMaster = new ProjectCategoryMaster()
            .categoryCode(DEFAULT_CATEGORY_CODE)
            .categoryName(DEFAULT_CATEGORY_NAME)
            .categoryDescription(DEFAULT_CATEGORY_DESCRIPTION);
        return projectCategoryMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectCategoryMaster createUpdatedEntity(EntityManager em) {
        ProjectCategoryMaster projectCategoryMaster = new ProjectCategoryMaster()
            .categoryCode(UPDATED_CATEGORY_CODE)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryDescription(UPDATED_CATEGORY_DESCRIPTION);
        return projectCategoryMaster;
    }

    @BeforeEach
    public void initTest() {
        projectCategoryMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectCategoryMaster() throws Exception {
        int databaseSizeBeforeCreate = projectCategoryMasterRepository.findAll().size();
        // Create the ProjectCategoryMaster
        restProjectCategoryMasterMockMvc.perform(post("/api/project-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectCategoryMaster)))
            .andExpect(status().isCreated());

        // Validate the ProjectCategoryMaster in the database
        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectCategoryMaster testProjectCategoryMaster = projectCategoryMasterList.get(projectCategoryMasterList.size() - 1);
        assertThat(testProjectCategoryMaster.getCategoryCode()).isEqualTo(DEFAULT_CATEGORY_CODE);
        assertThat(testProjectCategoryMaster.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testProjectCategoryMaster.getCategoryDescription()).isEqualTo(DEFAULT_CATEGORY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createProjectCategoryMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectCategoryMasterRepository.findAll().size();

        // Create the ProjectCategoryMaster with an existing ID
        projectCategoryMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectCategoryMasterMockMvc.perform(post("/api/project-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectCategoryMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectCategoryMaster in the database
        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCategoryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectCategoryMasterRepository.findAll().size();
        // set the field null
        projectCategoryMaster.setCategoryCode(null);

        // Create the ProjectCategoryMaster, which fails.


        restProjectCategoryMasterMockMvc.perform(post("/api/project-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectCategoryMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectCategoryMasterRepository.findAll().size();
        // set the field null
        projectCategoryMaster.setCategoryName(null);

        // Create the ProjectCategoryMaster, which fails.


        restProjectCategoryMasterMockMvc.perform(post("/api/project-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectCategoryMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectCategoryMasterRepository.findAll().size();
        // set the field null
        projectCategoryMaster.setCategoryDescription(null);

        // Create the ProjectCategoryMaster, which fails.


        restProjectCategoryMasterMockMvc.perform(post("/api/project-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectCategoryMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectCategoryMasters() throws Exception {
        // Initialize the database
        projectCategoryMasterRepository.saveAndFlush(projectCategoryMaster);

        // Get all the projectCategoryMasterList
        restProjectCategoryMasterMockMvc.perform(get("/api/project-category-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectCategoryMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryCode").value(hasItem(DEFAULT_CATEGORY_CODE)))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].categoryDescription").value(hasItem(DEFAULT_CATEGORY_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getProjectCategoryMaster() throws Exception {
        // Initialize the database
        projectCategoryMasterRepository.saveAndFlush(projectCategoryMaster);

        // Get the projectCategoryMaster
        restProjectCategoryMasterMockMvc.perform(get("/api/project-category-masters/{id}", projectCategoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectCategoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.categoryCode").value(DEFAULT_CATEGORY_CODE))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.categoryDescription").value(DEFAULT_CATEGORY_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingProjectCategoryMaster() throws Exception {
        // Get the projectCategoryMaster
        restProjectCategoryMasterMockMvc.perform(get("/api/project-category-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectCategoryMaster() throws Exception {
        // Initialize the database
        projectCategoryMasterRepository.saveAndFlush(projectCategoryMaster);

        int databaseSizeBeforeUpdate = projectCategoryMasterRepository.findAll().size();

        // Update the projectCategoryMaster
        ProjectCategoryMaster updatedProjectCategoryMaster = projectCategoryMasterRepository.findById(projectCategoryMaster.getId()).get();
        // Disconnect from session so that the updates on updatedProjectCategoryMaster are not directly saved in db
        em.detach(updatedProjectCategoryMaster);
        updatedProjectCategoryMaster
            .categoryCode(UPDATED_CATEGORY_CODE)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryDescription(UPDATED_CATEGORY_DESCRIPTION);

        restProjectCategoryMasterMockMvc.perform(put("/api/project-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectCategoryMaster)))
            .andExpect(status().isOk());

        // Validate the ProjectCategoryMaster in the database
        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeUpdate);
        ProjectCategoryMaster testProjectCategoryMaster = projectCategoryMasterList.get(projectCategoryMasterList.size() - 1);
        assertThat(testProjectCategoryMaster.getCategoryCode()).isEqualTo(UPDATED_CATEGORY_CODE);
        assertThat(testProjectCategoryMaster.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testProjectCategoryMaster.getCategoryDescription()).isEqualTo(UPDATED_CATEGORY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectCategoryMaster() throws Exception {
        int databaseSizeBeforeUpdate = projectCategoryMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectCategoryMasterMockMvc.perform(put("/api/project-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectCategoryMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectCategoryMaster in the database
        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectCategoryMaster() throws Exception {
        // Initialize the database
        projectCategoryMasterRepository.saveAndFlush(projectCategoryMaster);

        int databaseSizeBeforeDelete = projectCategoryMasterRepository.findAll().size();

        // Delete the projectCategoryMaster
        restProjectCategoryMasterMockMvc.perform(delete("/api/project-category-masters/{id}", projectCategoryMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectCategoryMaster> projectCategoryMasterList = projectCategoryMasterRepository.findAll();
        assertThat(projectCategoryMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
