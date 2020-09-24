package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectFeatureDtl;
import com.machbees.repository.ProjectFeatureDtlRepository;

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
 * Integration tests for the {@link ProjectFeatureDtlResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectFeatureDtlResourceIT {

    @Autowired
    private ProjectFeatureDtlRepository projectFeatureDtlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectFeatureDtlMockMvc;

    private ProjectFeatureDtl projectFeatureDtl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectFeatureDtl createEntity(EntityManager em) {
        ProjectFeatureDtl projectFeatureDtl = new ProjectFeatureDtl();
        return projectFeatureDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectFeatureDtl createUpdatedEntity(EntityManager em) {
        ProjectFeatureDtl projectFeatureDtl = new ProjectFeatureDtl();
        return projectFeatureDtl;
    }

    @BeforeEach
    public void initTest() {
        projectFeatureDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectFeatureDtl() throws Exception {
        int databaseSizeBeforeCreate = projectFeatureDtlRepository.findAll().size();
        // Create the ProjectFeatureDtl
        restProjectFeatureDtlMockMvc.perform(post("/api/project-feature-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureDtl)))
            .andExpect(status().isCreated());

        // Validate the ProjectFeatureDtl in the database
        List<ProjectFeatureDtl> projectFeatureDtlList = projectFeatureDtlRepository.findAll();
        assertThat(projectFeatureDtlList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectFeatureDtl testProjectFeatureDtl = projectFeatureDtlList.get(projectFeatureDtlList.size() - 1);
    }

    @Test
    @Transactional
    public void createProjectFeatureDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectFeatureDtlRepository.findAll().size();

        // Create the ProjectFeatureDtl with an existing ID
        projectFeatureDtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectFeatureDtlMockMvc.perform(post("/api/project-feature-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectFeatureDtl in the database
        List<ProjectFeatureDtl> projectFeatureDtlList = projectFeatureDtlRepository.findAll();
        assertThat(projectFeatureDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProjectFeatureDtls() throws Exception {
        // Initialize the database
        projectFeatureDtlRepository.saveAndFlush(projectFeatureDtl);

        // Get all the projectFeatureDtlList
        restProjectFeatureDtlMockMvc.perform(get("/api/project-feature-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectFeatureDtl.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getProjectFeatureDtl() throws Exception {
        // Initialize the database
        projectFeatureDtlRepository.saveAndFlush(projectFeatureDtl);

        // Get the projectFeatureDtl
        restProjectFeatureDtlMockMvc.perform(get("/api/project-feature-dtls/{id}", projectFeatureDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectFeatureDtl.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectFeatureDtl() throws Exception {
        // Get the projectFeatureDtl
        restProjectFeatureDtlMockMvc.perform(get("/api/project-feature-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectFeatureDtl() throws Exception {
        // Initialize the database
        projectFeatureDtlRepository.saveAndFlush(projectFeatureDtl);

        int databaseSizeBeforeUpdate = projectFeatureDtlRepository.findAll().size();

        // Update the projectFeatureDtl
        ProjectFeatureDtl updatedProjectFeatureDtl = projectFeatureDtlRepository.findById(projectFeatureDtl.getId()).get();
        // Disconnect from session so that the updates on updatedProjectFeatureDtl are not directly saved in db
        em.detach(updatedProjectFeatureDtl);

        restProjectFeatureDtlMockMvc.perform(put("/api/project-feature-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectFeatureDtl)))
            .andExpect(status().isOk());

        // Validate the ProjectFeatureDtl in the database
        List<ProjectFeatureDtl> projectFeatureDtlList = projectFeatureDtlRepository.findAll();
        assertThat(projectFeatureDtlList).hasSize(databaseSizeBeforeUpdate);
        ProjectFeatureDtl testProjectFeatureDtl = projectFeatureDtlList.get(projectFeatureDtlList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectFeatureDtl() throws Exception {
        int databaseSizeBeforeUpdate = projectFeatureDtlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectFeatureDtlMockMvc.perform(put("/api/project-feature-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectFeatureDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectFeatureDtl in the database
        List<ProjectFeatureDtl> projectFeatureDtlList = projectFeatureDtlRepository.findAll();
        assertThat(projectFeatureDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectFeatureDtl() throws Exception {
        // Initialize the database
        projectFeatureDtlRepository.saveAndFlush(projectFeatureDtl);

        int databaseSizeBeforeDelete = projectFeatureDtlRepository.findAll().size();

        // Delete the projectFeatureDtl
        restProjectFeatureDtlMockMvc.perform(delete("/api/project-feature-dtls/{id}", projectFeatureDtl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectFeatureDtl> projectFeatureDtlList = projectFeatureDtlRepository.findAll();
        assertThat(projectFeatureDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
