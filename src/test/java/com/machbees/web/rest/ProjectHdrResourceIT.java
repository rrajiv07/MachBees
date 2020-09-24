package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectHdr;
import com.machbees.repository.ProjectHdrRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProjectHdrResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectHdrResourceIT {

    private static final String DEFAULT_OVERVIEW = "AAAAAAAAAA";
    private static final String UPDATED_OVERVIEW = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MODIFIED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MODIFIED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ProjectHdrRepository projectHdrRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectHdrMockMvc;

    private ProjectHdr projectHdr;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectHdr createEntity(EntityManager em) {
        ProjectHdr projectHdr = new ProjectHdr()
            .overview(DEFAULT_OVERVIEW)
            .projectDescription(DEFAULT_PROJECT_DESCRIPTION)
            .endDate(DEFAULT_END_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return projectHdr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectHdr createUpdatedEntity(EntityManager em) {
        ProjectHdr projectHdr = new ProjectHdr()
            .overview(UPDATED_OVERVIEW)
            .projectDescription(UPDATED_PROJECT_DESCRIPTION)
            .endDate(UPDATED_END_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return projectHdr;
    }

    @BeforeEach
    public void initTest() {
        projectHdr = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectHdr() throws Exception {
        int databaseSizeBeforeCreate = projectHdrRepository.findAll().size();
        // Create the ProjectHdr
        restProjectHdrMockMvc.perform(post("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectHdr)))
            .andExpect(status().isCreated());

        // Validate the ProjectHdr in the database
        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectHdr testProjectHdr = projectHdrList.get(projectHdrList.size() - 1);
        assertThat(testProjectHdr.getOverview()).isEqualTo(DEFAULT_OVERVIEW);
        assertThat(testProjectHdr.getProjectDescription()).isEqualTo(DEFAULT_PROJECT_DESCRIPTION);
        assertThat(testProjectHdr.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testProjectHdr.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testProjectHdr.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testProjectHdr.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testProjectHdr.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createProjectHdrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectHdrRepository.findAll().size();

        // Create the ProjectHdr with an existing ID
        projectHdr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectHdrMockMvc.perform(post("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectHdr)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectHdr in the database
        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOverviewIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectHdrRepository.findAll().size();
        // set the field null
        projectHdr.setOverview(null);

        // Create the ProjectHdr, which fails.


        restProjectHdrMockMvc.perform(post("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectHdr)))
            .andExpect(status().isBadRequest());

        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProjectDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectHdrRepository.findAll().size();
        // set the field null
        projectHdr.setProjectDescription(null);

        // Create the ProjectHdr, which fails.


        restProjectHdrMockMvc.perform(post("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectHdr)))
            .andExpect(status().isBadRequest());

        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectHdrRepository.findAll().size();
        // set the field null
        projectHdr.setCreatedBy(null);

        // Create the ProjectHdr, which fails.


        restProjectHdrMockMvc.perform(post("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectHdr)))
            .andExpect(status().isBadRequest());

        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectHdrRepository.findAll().size();
        // set the field null
        projectHdr.setCreatedDate(null);

        // Create the ProjectHdr, which fails.


        restProjectHdrMockMvc.perform(post("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectHdr)))
            .andExpect(status().isBadRequest());

        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectHdrs() throws Exception {
        // Initialize the database
        projectHdrRepository.saveAndFlush(projectHdr);

        // Get all the projectHdrList
        restProjectHdrMockMvc.perform(get("/api/project-hdrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectHdr.getId().intValue())))
            .andExpect(jsonPath("$.[*].overview").value(hasItem(DEFAULT_OVERVIEW)))
            .andExpect(jsonPath("$.[*].projectDescription").value(hasItem(DEFAULT_PROJECT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getProjectHdr() throws Exception {
        // Initialize the database
        projectHdrRepository.saveAndFlush(projectHdr);

        // Get the projectHdr
        restProjectHdrMockMvc.perform(get("/api/project-hdrs/{id}", projectHdr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectHdr.getId().intValue()))
            .andExpect(jsonPath("$.overview").value(DEFAULT_OVERVIEW))
            .andExpect(jsonPath("$.projectDescription").value(DEFAULT_PROJECT_DESCRIPTION))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProjectHdr() throws Exception {
        // Get the projectHdr
        restProjectHdrMockMvc.perform(get("/api/project-hdrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectHdr() throws Exception {
        // Initialize the database
        projectHdrRepository.saveAndFlush(projectHdr);

        int databaseSizeBeforeUpdate = projectHdrRepository.findAll().size();

        // Update the projectHdr
        ProjectHdr updatedProjectHdr = projectHdrRepository.findById(projectHdr.getId()).get();
        // Disconnect from session so that the updates on updatedProjectHdr are not directly saved in db
        em.detach(updatedProjectHdr);
        updatedProjectHdr
            .overview(UPDATED_OVERVIEW)
            .projectDescription(UPDATED_PROJECT_DESCRIPTION)
            .endDate(UPDATED_END_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restProjectHdrMockMvc.perform(put("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectHdr)))
            .andExpect(status().isOk());

        // Validate the ProjectHdr in the database
        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeUpdate);
        ProjectHdr testProjectHdr = projectHdrList.get(projectHdrList.size() - 1);
        assertThat(testProjectHdr.getOverview()).isEqualTo(UPDATED_OVERVIEW);
        assertThat(testProjectHdr.getProjectDescription()).isEqualTo(UPDATED_PROJECT_DESCRIPTION);
        assertThat(testProjectHdr.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testProjectHdr.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testProjectHdr.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testProjectHdr.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testProjectHdr.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectHdr() throws Exception {
        int databaseSizeBeforeUpdate = projectHdrRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectHdrMockMvc.perform(put("/api/project-hdrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectHdr)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectHdr in the database
        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectHdr() throws Exception {
        // Initialize the database
        projectHdrRepository.saveAndFlush(projectHdr);

        int databaseSizeBeforeDelete = projectHdrRepository.findAll().size();

        // Delete the projectHdr
        restProjectHdrMockMvc.perform(delete("/api/project-hdrs/{id}", projectHdr.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectHdr> projectHdrList = projectHdrRepository.findAll();
        assertThat(projectHdrList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
