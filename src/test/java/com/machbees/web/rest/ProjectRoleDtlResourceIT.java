package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectRoleDtl;
import com.machbees.repository.ProjectRoleDtlRepository;

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
 * Integration tests for the {@link ProjectRoleDtlResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectRoleDtlResourceIT {

    @Autowired
    private ProjectRoleDtlRepository projectRoleDtlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectRoleDtlMockMvc;

    private ProjectRoleDtl projectRoleDtl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectRoleDtl createEntity(EntityManager em) {
        ProjectRoleDtl projectRoleDtl = new ProjectRoleDtl();
        return projectRoleDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectRoleDtl createUpdatedEntity(EntityManager em) {
        ProjectRoleDtl projectRoleDtl = new ProjectRoleDtl();
        return projectRoleDtl;
    }

    @BeforeEach
    public void initTest() {
        projectRoleDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectRoleDtl() throws Exception {
        int databaseSizeBeforeCreate = projectRoleDtlRepository.findAll().size();
        // Create the ProjectRoleDtl
        restProjectRoleDtlMockMvc.perform(post("/api/project-role-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleDtl)))
            .andExpect(status().isCreated());

        // Validate the ProjectRoleDtl in the database
        List<ProjectRoleDtl> projectRoleDtlList = projectRoleDtlRepository.findAll();
        assertThat(projectRoleDtlList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectRoleDtl testProjectRoleDtl = projectRoleDtlList.get(projectRoleDtlList.size() - 1);
    }

    @Test
    @Transactional
    public void createProjectRoleDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectRoleDtlRepository.findAll().size();

        // Create the ProjectRoleDtl with an existing ID
        projectRoleDtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectRoleDtlMockMvc.perform(post("/api/project-role-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectRoleDtl in the database
        List<ProjectRoleDtl> projectRoleDtlList = projectRoleDtlRepository.findAll();
        assertThat(projectRoleDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProjectRoleDtls() throws Exception {
        // Initialize the database
        projectRoleDtlRepository.saveAndFlush(projectRoleDtl);

        // Get all the projectRoleDtlList
        restProjectRoleDtlMockMvc.perform(get("/api/project-role-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectRoleDtl.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getProjectRoleDtl() throws Exception {
        // Initialize the database
        projectRoleDtlRepository.saveAndFlush(projectRoleDtl);

        // Get the projectRoleDtl
        restProjectRoleDtlMockMvc.perform(get("/api/project-role-dtls/{id}", projectRoleDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectRoleDtl.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectRoleDtl() throws Exception {
        // Get the projectRoleDtl
        restProjectRoleDtlMockMvc.perform(get("/api/project-role-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectRoleDtl() throws Exception {
        // Initialize the database
        projectRoleDtlRepository.saveAndFlush(projectRoleDtl);

        int databaseSizeBeforeUpdate = projectRoleDtlRepository.findAll().size();

        // Update the projectRoleDtl
        ProjectRoleDtl updatedProjectRoleDtl = projectRoleDtlRepository.findById(projectRoleDtl.getId()).get();
        // Disconnect from session so that the updates on updatedProjectRoleDtl are not directly saved in db
        em.detach(updatedProjectRoleDtl);

        restProjectRoleDtlMockMvc.perform(put("/api/project-role-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectRoleDtl)))
            .andExpect(status().isOk());

        // Validate the ProjectRoleDtl in the database
        List<ProjectRoleDtl> projectRoleDtlList = projectRoleDtlRepository.findAll();
        assertThat(projectRoleDtlList).hasSize(databaseSizeBeforeUpdate);
        ProjectRoleDtl testProjectRoleDtl = projectRoleDtlList.get(projectRoleDtlList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectRoleDtl() throws Exception {
        int databaseSizeBeforeUpdate = projectRoleDtlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectRoleDtlMockMvc.perform(put("/api/project-role-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectRoleDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectRoleDtl in the database
        List<ProjectRoleDtl> projectRoleDtlList = projectRoleDtlRepository.findAll();
        assertThat(projectRoleDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectRoleDtl() throws Exception {
        // Initialize the database
        projectRoleDtlRepository.saveAndFlush(projectRoleDtl);

        int databaseSizeBeforeDelete = projectRoleDtlRepository.findAll().size();

        // Delete the projectRoleDtl
        restProjectRoleDtlMockMvc.perform(delete("/api/project-role-dtls/{id}", projectRoleDtl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectRoleDtl> projectRoleDtlList = projectRoleDtlRepository.findAll();
        assertThat(projectRoleDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
