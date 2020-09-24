package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectSkillDtl;
import com.machbees.repository.ProjectSkillDtlRepository;

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
 * Integration tests for the {@link ProjectSkillDtlResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectSkillDtlResourceIT {

    @Autowired
    private ProjectSkillDtlRepository projectSkillDtlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectSkillDtlMockMvc;

    private ProjectSkillDtl projectSkillDtl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectSkillDtl createEntity(EntityManager em) {
        ProjectSkillDtl projectSkillDtl = new ProjectSkillDtl();
        return projectSkillDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectSkillDtl createUpdatedEntity(EntityManager em) {
        ProjectSkillDtl projectSkillDtl = new ProjectSkillDtl();
        return projectSkillDtl;
    }

    @BeforeEach
    public void initTest() {
        projectSkillDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectSkillDtl() throws Exception {
        int databaseSizeBeforeCreate = projectSkillDtlRepository.findAll().size();
        // Create the ProjectSkillDtl
        restProjectSkillDtlMockMvc.perform(post("/api/project-skill-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSkillDtl)))
            .andExpect(status().isCreated());

        // Validate the ProjectSkillDtl in the database
        List<ProjectSkillDtl> projectSkillDtlList = projectSkillDtlRepository.findAll();
        assertThat(projectSkillDtlList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectSkillDtl testProjectSkillDtl = projectSkillDtlList.get(projectSkillDtlList.size() - 1);
    }

    @Test
    @Transactional
    public void createProjectSkillDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectSkillDtlRepository.findAll().size();

        // Create the ProjectSkillDtl with an existing ID
        projectSkillDtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectSkillDtlMockMvc.perform(post("/api/project-skill-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSkillDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectSkillDtl in the database
        List<ProjectSkillDtl> projectSkillDtlList = projectSkillDtlRepository.findAll();
        assertThat(projectSkillDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProjectSkillDtls() throws Exception {
        // Initialize the database
        projectSkillDtlRepository.saveAndFlush(projectSkillDtl);

        // Get all the projectSkillDtlList
        restProjectSkillDtlMockMvc.perform(get("/api/project-skill-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectSkillDtl.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getProjectSkillDtl() throws Exception {
        // Initialize the database
        projectSkillDtlRepository.saveAndFlush(projectSkillDtl);

        // Get the projectSkillDtl
        restProjectSkillDtlMockMvc.perform(get("/api/project-skill-dtls/{id}", projectSkillDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectSkillDtl.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectSkillDtl() throws Exception {
        // Get the projectSkillDtl
        restProjectSkillDtlMockMvc.perform(get("/api/project-skill-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectSkillDtl() throws Exception {
        // Initialize the database
        projectSkillDtlRepository.saveAndFlush(projectSkillDtl);

        int databaseSizeBeforeUpdate = projectSkillDtlRepository.findAll().size();

        // Update the projectSkillDtl
        ProjectSkillDtl updatedProjectSkillDtl = projectSkillDtlRepository.findById(projectSkillDtl.getId()).get();
        // Disconnect from session so that the updates on updatedProjectSkillDtl are not directly saved in db
        em.detach(updatedProjectSkillDtl);

        restProjectSkillDtlMockMvc.perform(put("/api/project-skill-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectSkillDtl)))
            .andExpect(status().isOk());

        // Validate the ProjectSkillDtl in the database
        List<ProjectSkillDtl> projectSkillDtlList = projectSkillDtlRepository.findAll();
        assertThat(projectSkillDtlList).hasSize(databaseSizeBeforeUpdate);
        ProjectSkillDtl testProjectSkillDtl = projectSkillDtlList.get(projectSkillDtlList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectSkillDtl() throws Exception {
        int databaseSizeBeforeUpdate = projectSkillDtlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectSkillDtlMockMvc.perform(put("/api/project-skill-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectSkillDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectSkillDtl in the database
        List<ProjectSkillDtl> projectSkillDtlList = projectSkillDtlRepository.findAll();
        assertThat(projectSkillDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectSkillDtl() throws Exception {
        // Initialize the database
        projectSkillDtlRepository.saveAndFlush(projectSkillDtl);

        int databaseSizeBeforeDelete = projectSkillDtlRepository.findAll().size();

        // Delete the projectSkillDtl
        restProjectSkillDtlMockMvc.perform(delete("/api/project-skill-dtls/{id}", projectSkillDtl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectSkillDtl> projectSkillDtlList = projectSkillDtlRepository.findAll();
        assertThat(projectSkillDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
