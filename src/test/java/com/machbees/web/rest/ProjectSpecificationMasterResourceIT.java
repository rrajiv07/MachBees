package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectSpecificationMaster;
import com.machbees.repository.ProjectSpecificationMasterRepository;

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
 * Integration tests for the {@link ProjectSpecificationMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectSpecificationMasterResourceIT {

    private static final String DEFAULT_SPECIFICATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIFICATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIFICATION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATION_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ProjectSpecificationMasterRepository projectSpecificationMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectSpecificationMasterMockMvc;

    private ProjectSpecificationMaster projectSpecificationMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectSpecificationMaster createEntity(EntityManager em) {
        ProjectSpecificationMaster projectSpecificationMaster = new ProjectSpecificationMaster()
            .specificationCode(DEFAULT_SPECIFICATION_CODE)
            .specificationName(DEFAULT_SPECIFICATION_NAME)
            .specificationDescription(DEFAULT_SPECIFICATION_DESCRIPTION);
        return projectSpecificationMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectSpecificationMaster createUpdatedEntity(EntityManager em) {
        ProjectSpecificationMaster projectSpecificationMaster = new ProjectSpecificationMaster()
            .specificationCode(UPDATED_SPECIFICATION_CODE)
            .specificationName(UPDATED_SPECIFICATION_NAME)
            .specificationDescription(UPDATED_SPECIFICATION_DESCRIPTION);
        return projectSpecificationMaster;
    }

    @BeforeEach
    public void initTest() {
        projectSpecificationMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectSpecificationMaster() throws Exception {
        int databaseSizeBeforeCreate = projectSpecificationMasterRepository.findAll().size();
        // Create the ProjectSpecificationMaster
        restProjectSpecificationMasterMockMvc.perform(post("/api/project-specification-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecificationMaster)))
            .andExpect(status().isCreated());

        // Validate the ProjectSpecificationMaster in the database
        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectSpecificationMaster testProjectSpecificationMaster = projectSpecificationMasterList.get(projectSpecificationMasterList.size() - 1);
        assertThat(testProjectSpecificationMaster.getSpecificationCode()).isEqualTo(DEFAULT_SPECIFICATION_CODE);
        assertThat(testProjectSpecificationMaster.getSpecificationName()).isEqualTo(DEFAULT_SPECIFICATION_NAME);
        assertThat(testProjectSpecificationMaster.getSpecificationDescription()).isEqualTo(DEFAULT_SPECIFICATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createProjectSpecificationMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectSpecificationMasterRepository.findAll().size();

        // Create the ProjectSpecificationMaster with an existing ID
        projectSpecificationMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectSpecificationMasterMockMvc.perform(post("/api/project-specification-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecificationMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectSpecificationMaster in the database
        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSpecificationCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectSpecificationMasterRepository.findAll().size();
        // set the field null
        projectSpecificationMaster.setSpecificationCode(null);

        // Create the ProjectSpecificationMaster, which fails.


        restProjectSpecificationMasterMockMvc.perform(post("/api/project-specification-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecificationMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecificationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectSpecificationMasterRepository.findAll().size();
        // set the field null
        projectSpecificationMaster.setSpecificationName(null);

        // Create the ProjectSpecificationMaster, which fails.


        restProjectSpecificationMasterMockMvc.perform(post("/api/project-specification-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecificationMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpecificationDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectSpecificationMasterRepository.findAll().size();
        // set the field null
        projectSpecificationMaster.setSpecificationDescription(null);

        // Create the ProjectSpecificationMaster, which fails.


        restProjectSpecificationMasterMockMvc.perform(post("/api/project-specification-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecificationMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectSpecificationMasters() throws Exception {
        // Initialize the database
        projectSpecificationMasterRepository.saveAndFlush(projectSpecificationMaster);

        // Get all the projectSpecificationMasterList
        restProjectSpecificationMasterMockMvc.perform(get("/api/project-specification-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectSpecificationMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].specificationCode").value(hasItem(DEFAULT_SPECIFICATION_CODE)))
            .andExpect(jsonPath("$.[*].specificationName").value(hasItem(DEFAULT_SPECIFICATION_NAME)))
            .andExpect(jsonPath("$.[*].specificationDescription").value(hasItem(DEFAULT_SPECIFICATION_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getProjectSpecificationMaster() throws Exception {
        // Initialize the database
        projectSpecificationMasterRepository.saveAndFlush(projectSpecificationMaster);

        // Get the projectSpecificationMaster
        restProjectSpecificationMasterMockMvc.perform(get("/api/project-specification-masters/{id}", projectSpecificationMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectSpecificationMaster.getId().intValue()))
            .andExpect(jsonPath("$.specificationCode").value(DEFAULT_SPECIFICATION_CODE))
            .andExpect(jsonPath("$.specificationName").value(DEFAULT_SPECIFICATION_NAME))
            .andExpect(jsonPath("$.specificationDescription").value(DEFAULT_SPECIFICATION_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingProjectSpecificationMaster() throws Exception {
        // Get the projectSpecificationMaster
        restProjectSpecificationMasterMockMvc.perform(get("/api/project-specification-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectSpecificationMaster() throws Exception {
        // Initialize the database
        projectSpecificationMasterRepository.saveAndFlush(projectSpecificationMaster);

        int databaseSizeBeforeUpdate = projectSpecificationMasterRepository.findAll().size();

        // Update the projectSpecificationMaster
        ProjectSpecificationMaster updatedProjectSpecificationMaster = projectSpecificationMasterRepository.findById(projectSpecificationMaster.getId()).get();
        // Disconnect from session so that the updates on updatedProjectSpecificationMaster are not directly saved in db
        em.detach(updatedProjectSpecificationMaster);
        updatedProjectSpecificationMaster
            .specificationCode(UPDATED_SPECIFICATION_CODE)
            .specificationName(UPDATED_SPECIFICATION_NAME)
            .specificationDescription(UPDATED_SPECIFICATION_DESCRIPTION);

        restProjectSpecificationMasterMockMvc.perform(put("/api/project-specification-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectSpecificationMaster)))
            .andExpect(status().isOk());

        // Validate the ProjectSpecificationMaster in the database
        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeUpdate);
        ProjectSpecificationMaster testProjectSpecificationMaster = projectSpecificationMasterList.get(projectSpecificationMasterList.size() - 1);
        assertThat(testProjectSpecificationMaster.getSpecificationCode()).isEqualTo(UPDATED_SPECIFICATION_CODE);
        assertThat(testProjectSpecificationMaster.getSpecificationName()).isEqualTo(UPDATED_SPECIFICATION_NAME);
        assertThat(testProjectSpecificationMaster.getSpecificationDescription()).isEqualTo(UPDATED_SPECIFICATION_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectSpecificationMaster() throws Exception {
        int databaseSizeBeforeUpdate = projectSpecificationMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectSpecificationMasterMockMvc.perform(put("/api/project-specification-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSpecificationMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectSpecificationMaster in the database
        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectSpecificationMaster() throws Exception {
        // Initialize the database
        projectSpecificationMasterRepository.saveAndFlush(projectSpecificationMaster);

        int databaseSizeBeforeDelete = projectSpecificationMasterRepository.findAll().size();

        // Delete the projectSpecificationMaster
        restProjectSpecificationMasterMockMvc.perform(delete("/api/project-specification-masters/{id}", projectSpecificationMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectSpecificationMaster> projectSpecificationMasterList = projectSpecificationMasterRepository.findAll();
        assertThat(projectSpecificationMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
