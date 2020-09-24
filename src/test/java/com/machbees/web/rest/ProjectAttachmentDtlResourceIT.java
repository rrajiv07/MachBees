package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.ProjectAttachmentDtl;
import com.machbees.repository.ProjectAttachmentDtlRepository;

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
 * Integration tests for the {@link ProjectAttachmentDtlResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectAttachmentDtlResourceIT {

    private static final String DEFAULT_FILE_ID = "AAAAAAAAAA";
    private static final String UPDATED_FILE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    @Autowired
    private ProjectAttachmentDtlRepository projectAttachmentDtlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectAttachmentDtlMockMvc;

    private ProjectAttachmentDtl projectAttachmentDtl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectAttachmentDtl createEntity(EntityManager em) {
        ProjectAttachmentDtl projectAttachmentDtl = new ProjectAttachmentDtl()
            .fileId(DEFAULT_FILE_ID)
            .fileName(DEFAULT_FILE_NAME)
            .fileType(DEFAULT_FILE_TYPE);
        return projectAttachmentDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectAttachmentDtl createUpdatedEntity(EntityManager em) {
        ProjectAttachmentDtl projectAttachmentDtl = new ProjectAttachmentDtl()
            .fileId(UPDATED_FILE_ID)
            .fileName(UPDATED_FILE_NAME)
            .fileType(UPDATED_FILE_TYPE);
        return projectAttachmentDtl;
    }

    @BeforeEach
    public void initTest() {
        projectAttachmentDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectAttachmentDtl() throws Exception {
        int databaseSizeBeforeCreate = projectAttachmentDtlRepository.findAll().size();
        // Create the ProjectAttachmentDtl
        restProjectAttachmentDtlMockMvc.perform(post("/api/project-attachment-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachmentDtl)))
            .andExpect(status().isCreated());

        // Validate the ProjectAttachmentDtl in the database
        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectAttachmentDtl testProjectAttachmentDtl = projectAttachmentDtlList.get(projectAttachmentDtlList.size() - 1);
        assertThat(testProjectAttachmentDtl.getFileId()).isEqualTo(DEFAULT_FILE_ID);
        assertThat(testProjectAttachmentDtl.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testProjectAttachmentDtl.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
    }

    @Test
    @Transactional
    public void createProjectAttachmentDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectAttachmentDtlRepository.findAll().size();

        // Create the ProjectAttachmentDtl with an existing ID
        projectAttachmentDtl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectAttachmentDtlMockMvc.perform(post("/api/project-attachment-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachmentDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectAttachmentDtl in the database
        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFileIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectAttachmentDtlRepository.findAll().size();
        // set the field null
        projectAttachmentDtl.setFileId(null);

        // Create the ProjectAttachmentDtl, which fails.


        restProjectAttachmentDtlMockMvc.perform(post("/api/project-attachment-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachmentDtl)))
            .andExpect(status().isBadRequest());

        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectAttachmentDtlRepository.findAll().size();
        // set the field null
        projectAttachmentDtl.setFileName(null);

        // Create the ProjectAttachmentDtl, which fails.


        restProjectAttachmentDtlMockMvc.perform(post("/api/project-attachment-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachmentDtl)))
            .andExpect(status().isBadRequest());

        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFileTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectAttachmentDtlRepository.findAll().size();
        // set the field null
        projectAttachmentDtl.setFileType(null);

        // Create the ProjectAttachmentDtl, which fails.


        restProjectAttachmentDtlMockMvc.perform(post("/api/project-attachment-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachmentDtl)))
            .andExpect(status().isBadRequest());

        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectAttachmentDtls() throws Exception {
        // Initialize the database
        projectAttachmentDtlRepository.saveAndFlush(projectAttachmentDtl);

        // Get all the projectAttachmentDtlList
        restProjectAttachmentDtlMockMvc.perform(get("/api/project-attachment-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectAttachmentDtl.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileId").value(hasItem(DEFAULT_FILE_ID)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE)));
    }
    
    @Test
    @Transactional
    public void getProjectAttachmentDtl() throws Exception {
        // Initialize the database
        projectAttachmentDtlRepository.saveAndFlush(projectAttachmentDtl);

        // Get the projectAttachmentDtl
        restProjectAttachmentDtlMockMvc.perform(get("/api/project-attachment-dtls/{id}", projectAttachmentDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectAttachmentDtl.getId().intValue()))
            .andExpect(jsonPath("$.fileId").value(DEFAULT_FILE_ID))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingProjectAttachmentDtl() throws Exception {
        // Get the projectAttachmentDtl
        restProjectAttachmentDtlMockMvc.perform(get("/api/project-attachment-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectAttachmentDtl() throws Exception {
        // Initialize the database
        projectAttachmentDtlRepository.saveAndFlush(projectAttachmentDtl);

        int databaseSizeBeforeUpdate = projectAttachmentDtlRepository.findAll().size();

        // Update the projectAttachmentDtl
        ProjectAttachmentDtl updatedProjectAttachmentDtl = projectAttachmentDtlRepository.findById(projectAttachmentDtl.getId()).get();
        // Disconnect from session so that the updates on updatedProjectAttachmentDtl are not directly saved in db
        em.detach(updatedProjectAttachmentDtl);
        updatedProjectAttachmentDtl
            .fileId(UPDATED_FILE_ID)
            .fileName(UPDATED_FILE_NAME)
            .fileType(UPDATED_FILE_TYPE);

        restProjectAttachmentDtlMockMvc.perform(put("/api/project-attachment-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectAttachmentDtl)))
            .andExpect(status().isOk());

        // Validate the ProjectAttachmentDtl in the database
        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeUpdate);
        ProjectAttachmentDtl testProjectAttachmentDtl = projectAttachmentDtlList.get(projectAttachmentDtlList.size() - 1);
        assertThat(testProjectAttachmentDtl.getFileId()).isEqualTo(UPDATED_FILE_ID);
        assertThat(testProjectAttachmentDtl.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testProjectAttachmentDtl.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectAttachmentDtl() throws Exception {
        int databaseSizeBeforeUpdate = projectAttachmentDtlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectAttachmentDtlMockMvc.perform(put("/api/project-attachment-dtls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectAttachmentDtl)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectAttachmentDtl in the database
        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectAttachmentDtl() throws Exception {
        // Initialize the database
        projectAttachmentDtlRepository.saveAndFlush(projectAttachmentDtl);

        int databaseSizeBeforeDelete = projectAttachmentDtlRepository.findAll().size();

        // Delete the projectAttachmentDtl
        restProjectAttachmentDtlMockMvc.perform(delete("/api/project-attachment-dtls/{id}", projectAttachmentDtl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectAttachmentDtl> projectAttachmentDtlList = projectAttachmentDtlRepository.findAll();
        assertThat(projectAttachmentDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
