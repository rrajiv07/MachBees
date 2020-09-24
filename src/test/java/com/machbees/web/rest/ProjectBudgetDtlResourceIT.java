package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectBudgetDtl;
import com.machbees.repository.ProjectBudgetDtlRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProjectBudgetDtlResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectBudgetDtlResourceIT {

    private static final BigDecimal DEFAULT_BUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_IP_OWNERSHIP = new BigDecimal(1);
    private static final BigDecimal UPDATED_IP_OWNERSHIP = new BigDecimal(2);

    @Autowired
    private ProjectBudgetDtlRepository projectBudgetDtlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectBudgetDtlMockMvc;

    private ProjectBudgetDtl projectBudgetDtl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectBudgetDtl createEntity(EntityManager em) {
        ProjectBudgetDtl projectBudgetDtl = new ProjectBudgetDtl()
            .budget(DEFAULT_BUDGET)
            .ipOwnership(DEFAULT_IP_OWNERSHIP);
        return projectBudgetDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectBudgetDtl createUpdatedEntity(EntityManager em) {
        ProjectBudgetDtl projectBudgetDtl = new ProjectBudgetDtl()
            .budget(UPDATED_BUDGET)
            .ipOwnership(UPDATED_IP_OWNERSHIP);
        return projectBudgetDtl;
    }

    @BeforeEach
    public void initTest() {
        projectBudgetDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectBudgetDtl() throws Exception {
        int databaseSizeBeforeCreate = projectBudgetDtlRepository.findAll().size();
        // Create the ProjectBudgetDtl
        restProjectBudgetDtlMockMvc.perform(post("/api/project-budget-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectBudgetDtl)))
            .andExpect(status().isCreated());

        // Validate the ProjectBudgetDtl in the database
        List<ProjectBudgetDtl> projectBudgetDtlList = projectBudgetDtlRepository.findAll();
        assertThat(projectBudgetDtlList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectBudgetDtl testProjectBudgetDtl = projectBudgetDtlList.get(projectBudgetDtlList.size() - 1);
        assertThat(testProjectBudgetDtl.getBudget()).isEqualTo(DEFAULT_BUDGET);
        assertThat(testProjectBudgetDtl.getIpOwnership()).isEqualTo(DEFAULT_IP_OWNERSHIP);
    }

    @Test
    @Transactional
    public void createProjectBudgetDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectBudgetDtlRepository.findAll().size();

        // Create the ProjectBudgetDtl with an existing ID
        projectBudgetDtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectBudgetDtlMockMvc.perform(post("/api/project-budget-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectBudgetDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectBudgetDtl in the database
        List<ProjectBudgetDtl> projectBudgetDtlList = projectBudgetDtlRepository.findAll();
        assertThat(projectBudgetDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProjectBudgetDtls() throws Exception {
        // Initialize the database
        projectBudgetDtlRepository.saveAndFlush(projectBudgetDtl);

        // Get all the projectBudgetDtlList
        restProjectBudgetDtlMockMvc.perform(get("/api/project-budget-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectBudgetDtl.getId().intValue())))
            .andExpect(jsonPath("$.[*].budget").value(hasItem(DEFAULT_BUDGET.intValue())))
            .andExpect(jsonPath("$.[*].ipOwnership").value(hasItem(DEFAULT_IP_OWNERSHIP.intValue())));
    }
    
    @Test
    @Transactional
    public void getProjectBudgetDtl() throws Exception {
        // Initialize the database
        projectBudgetDtlRepository.saveAndFlush(projectBudgetDtl);

        // Get the projectBudgetDtl
        restProjectBudgetDtlMockMvc.perform(get("/api/project-budget-dtls/{id}", projectBudgetDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectBudgetDtl.getId().intValue()))
            .andExpect(jsonPath("$.budget").value(DEFAULT_BUDGET.intValue()))
            .andExpect(jsonPath("$.ipOwnership").value(DEFAULT_IP_OWNERSHIP.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectBudgetDtl() throws Exception {
        // Get the projectBudgetDtl
        restProjectBudgetDtlMockMvc.perform(get("/api/project-budget-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectBudgetDtl() throws Exception {
        // Initialize the database
        projectBudgetDtlRepository.saveAndFlush(projectBudgetDtl);

        int databaseSizeBeforeUpdate = projectBudgetDtlRepository.findAll().size();

        // Update the projectBudgetDtl
        ProjectBudgetDtl updatedProjectBudgetDtl = projectBudgetDtlRepository.findById(projectBudgetDtl.getId()).get();
        // Disconnect from session so that the updates on updatedProjectBudgetDtl are not directly saved in db
        em.detach(updatedProjectBudgetDtl);
        updatedProjectBudgetDtl
            .budget(UPDATED_BUDGET)
            .ipOwnership(UPDATED_IP_OWNERSHIP);

        restProjectBudgetDtlMockMvc.perform(put("/api/project-budget-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectBudgetDtl)))
            .andExpect(status().isOk());

        // Validate the ProjectBudgetDtl in the database
        List<ProjectBudgetDtl> projectBudgetDtlList = projectBudgetDtlRepository.findAll();
        assertThat(projectBudgetDtlList).hasSize(databaseSizeBeforeUpdate);
        ProjectBudgetDtl testProjectBudgetDtl = projectBudgetDtlList.get(projectBudgetDtlList.size() - 1);
        assertThat(testProjectBudgetDtl.getBudget()).isEqualTo(UPDATED_BUDGET);
        assertThat(testProjectBudgetDtl.getIpOwnership()).isEqualTo(UPDATED_IP_OWNERSHIP);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectBudgetDtl() throws Exception {
        int databaseSizeBeforeUpdate = projectBudgetDtlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectBudgetDtlMockMvc.perform(put("/api/project-budget-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectBudgetDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectBudgetDtl in the database
        List<ProjectBudgetDtl> projectBudgetDtlList = projectBudgetDtlRepository.findAll();
        assertThat(projectBudgetDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectBudgetDtl() throws Exception {
        // Initialize the database
        projectBudgetDtlRepository.saveAndFlush(projectBudgetDtl);

        int databaseSizeBeforeDelete = projectBudgetDtlRepository.findAll().size();

        // Delete the projectBudgetDtl
        restProjectBudgetDtlMockMvc.perform(delete("/api/project-budget-dtls/{id}", projectBudgetDtl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectBudgetDtl> projectBudgetDtlList = projectBudgetDtlRepository.findAll();
        assertThat(projectBudgetDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
