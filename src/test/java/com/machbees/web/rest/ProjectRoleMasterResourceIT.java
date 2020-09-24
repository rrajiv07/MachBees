package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectRoleMaster;
import com.machbees.repository.ProjectRoleMasterRepository;

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
 * Integration tests for the {@link ProjectRoleMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectRoleMasterResourceIT {

    private static final String DEFAULT_ROLE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ProjectRoleMasterRepository projectRoleMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectRoleMasterMockMvc;

    private ProjectRoleMaster projectRoleMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectRoleMaster createEntity(EntityManager em) {
        ProjectRoleMaster projectRoleMaster = new ProjectRoleMaster()
            .roleCode(DEFAULT_ROLE_CODE)
            .roleName(DEFAULT_ROLE_NAME)
            .roleDescription(DEFAULT_ROLE_DESCRIPTION);
        return projectRoleMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectRoleMaster createUpdatedEntity(EntityManager em) {
        ProjectRoleMaster projectRoleMaster = new ProjectRoleMaster()
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION);
        return projectRoleMaster;
    }

    @BeforeEach
    public void initTest() {
        projectRoleMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectRoleMaster() throws Exception {
        int databaseSizeBeforeCreate = projectRoleMasterRepository.findAll().size();
        // Create the ProjectRoleMaster
        restProjectRoleMasterMockMvc.perform(post("/api/project-role-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleMaster)))
            .andExpect(status().isCreated());

        // Validate the ProjectRoleMaster in the database
        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectRoleMaster testProjectRoleMaster = projectRoleMasterList.get(projectRoleMasterList.size() - 1);
        assertThat(testProjectRoleMaster.getRoleCode()).isEqualTo(DEFAULT_ROLE_CODE);
        assertThat(testProjectRoleMaster.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testProjectRoleMaster.getRoleDescription()).isEqualTo(DEFAULT_ROLE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createProjectRoleMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectRoleMasterRepository.findAll().size();

        // Create the ProjectRoleMaster with an existing ID
        projectRoleMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectRoleMasterMockMvc.perform(post("/api/project-role-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectRoleMaster in the database
        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRoleCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRoleMasterRepository.findAll().size();
        // set the field null
        projectRoleMaster.setRoleCode(null);

        // Create the ProjectRoleMaster, which fails.


        restProjectRoleMasterMockMvc.perform(post("/api/project-role-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRoleMasterRepository.findAll().size();
        // set the field null
        projectRoleMaster.setRoleName(null);

        // Create the ProjectRoleMaster, which fails.


        restProjectRoleMasterMockMvc.perform(post("/api/project-role-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRoleMasterRepository.findAll().size();
        // set the field null
        projectRoleMaster.setRoleDescription(null);

        // Create the ProjectRoleMaster, which fails.


        restProjectRoleMasterMockMvc.perform(post("/api/project-role-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleMaster)))
            .andExpect(status().isBadRequest());

        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectRoleMasters() throws Exception {
        // Initialize the database
        projectRoleMasterRepository.saveAndFlush(projectRoleMaster);

        // Get all the projectRoleMasterList
        restProjectRoleMasterMockMvc.perform(get("/api/project-role-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectRoleMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleCode").value(hasItem(DEFAULT_ROLE_CODE)))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
            .andExpect(jsonPath("$.[*].roleDescription").value(hasItem(DEFAULT_ROLE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getProjectRoleMaster() throws Exception {
        // Initialize the database
        projectRoleMasterRepository.saveAndFlush(projectRoleMaster);

        // Get the projectRoleMaster
        restProjectRoleMasterMockMvc.perform(get("/api/project-role-masters/{id}", projectRoleMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectRoleMaster.getId().intValue()))
            .andExpect(jsonPath("$.roleCode").value(DEFAULT_ROLE_CODE))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME))
            .andExpect(jsonPath("$.roleDescription").value(DEFAULT_ROLE_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingProjectRoleMaster() throws Exception {
        // Get the projectRoleMaster
        restProjectRoleMasterMockMvc.perform(get("/api/project-role-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectRoleMaster() throws Exception {
        // Initialize the database
        projectRoleMasterRepository.saveAndFlush(projectRoleMaster);

        int databaseSizeBeforeUpdate = projectRoleMasterRepository.findAll().size();

        // Update the projectRoleMaster
        ProjectRoleMaster updatedProjectRoleMaster = projectRoleMasterRepository.findById(projectRoleMaster.getId()).get();
        // Disconnect from session so that the updates on updatedProjectRoleMaster are not directly saved in db
        em.detach(updatedProjectRoleMaster);
        updatedProjectRoleMaster
            .roleCode(UPDATED_ROLE_CODE)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION);

        restProjectRoleMasterMockMvc.perform(put("/api/project-role-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectRoleMaster)))
            .andExpect(status().isOk());

        // Validate the ProjectRoleMaster in the database
        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeUpdate);
        ProjectRoleMaster testProjectRoleMaster = projectRoleMasterList.get(projectRoleMasterList.size() - 1);
        assertThat(testProjectRoleMaster.getRoleCode()).isEqualTo(UPDATED_ROLE_CODE);
        assertThat(testProjectRoleMaster.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testProjectRoleMaster.getRoleDescription()).isEqualTo(UPDATED_ROLE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectRoleMaster() throws Exception {
        int databaseSizeBeforeUpdate = projectRoleMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectRoleMasterMockMvc.perform(put("/api/project-role-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleMaster)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectRoleMaster in the database
        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectRoleMaster() throws Exception {
        // Initialize the database
        projectRoleMasterRepository.saveAndFlush(projectRoleMaster);

        int databaseSizeBeforeDelete = projectRoleMasterRepository.findAll().size();

        // Delete the projectRoleMaster
        restProjectRoleMasterMockMvc.perform(delete("/api/project-role-masters/{id}", projectRoleMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectRoleMaster> projectRoleMasterList = projectRoleMasterRepository.findAll();
        assertThat(projectRoleMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
