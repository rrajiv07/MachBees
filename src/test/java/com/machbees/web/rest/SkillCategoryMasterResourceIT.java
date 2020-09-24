package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.SkillCategoryMaster;
import com.machbees.repository.SkillCategoryMasterRepository;

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
 * Integration tests for the {@link SkillCategoryMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SkillCategoryMasterResourceIT {

    private static final String DEFAULT_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SkillCategoryMasterRepository skillCategoryMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSkillCategoryMasterMockMvc;

    private SkillCategoryMaster skillCategoryMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillCategoryMaster createEntity(EntityManager em) {
        SkillCategoryMaster skillCategoryMaster = new SkillCategoryMaster()
            .categoryCode(DEFAULT_CATEGORY_CODE)
            .categoryName(DEFAULT_CATEGORY_NAME)
            .categoryDescription(DEFAULT_CATEGORY_DESCRIPTION);
        return skillCategoryMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillCategoryMaster createUpdatedEntity(EntityManager em) {
        SkillCategoryMaster skillCategoryMaster = new SkillCategoryMaster()
            .categoryCode(UPDATED_CATEGORY_CODE)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryDescription(UPDATED_CATEGORY_DESCRIPTION);
        return skillCategoryMaster;
    }

    @BeforeEach
    public void initTest() {
        skillCategoryMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkillCategoryMaster() throws Exception {
        int databaseSizeBeforeCreate = skillCategoryMasterRepository.findAll().size();
        // Create the SkillCategoryMaster
        restSkillCategoryMasterMockMvc.perform(post("/api/skill-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillCategoryMaster)))
            .andExpect(status().isCreated());

        // Validate the SkillCategoryMaster in the database
        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SkillCategoryMaster testSkillCategoryMaster = skillCategoryMasterList.get(skillCategoryMasterList.size() - 1);
        assertThat(testSkillCategoryMaster.getCategoryCode()).isEqualTo(DEFAULT_CATEGORY_CODE);
        assertThat(testSkillCategoryMaster.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testSkillCategoryMaster.getCategoryDescription()).isEqualTo(DEFAULT_CATEGORY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSkillCategoryMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillCategoryMasterRepository.findAll().size();

        // Create the SkillCategoryMaster with an existing ID
        skillCategoryMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillCategoryMasterMockMvc.perform(post("/api/skill-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillCategoryMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SkillCategoryMaster in the database
        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCategoryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = skillCategoryMasterRepository.findAll().size();
        // set the field null
        skillCategoryMaster.setCategoryCode(null);

        // Create the SkillCategoryMaster, which fails.


        restSkillCategoryMasterMockMvc.perform(post("/api/skill-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillCategoryMaster)))
            .andExpect(status().isBadRequest());

        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = skillCategoryMasterRepository.findAll().size();
        // set the field null
        skillCategoryMaster.setCategoryName(null);

        // Create the SkillCategoryMaster, which fails.


        restSkillCategoryMasterMockMvc.perform(post("/api/skill-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillCategoryMaster)))
            .andExpect(status().isBadRequest());

        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = skillCategoryMasterRepository.findAll().size();
        // set the field null
        skillCategoryMaster.setCategoryDescription(null);

        // Create the SkillCategoryMaster, which fails.


        restSkillCategoryMasterMockMvc.perform(post("/api/skill-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillCategoryMaster)))
            .andExpect(status().isBadRequest());

        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSkillCategoryMasters() throws Exception {
        // Initialize the database
        skillCategoryMasterRepository.saveAndFlush(skillCategoryMaster);

        // Get all the skillCategoryMasterList
        restSkillCategoryMasterMockMvc.perform(get("/api/skill-category-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skillCategoryMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryCode").value(hasItem(DEFAULT_CATEGORY_CODE)))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].categoryDescription").value(hasItem(DEFAULT_CATEGORY_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getSkillCategoryMaster() throws Exception {
        // Initialize the database
        skillCategoryMasterRepository.saveAndFlush(skillCategoryMaster);

        // Get the skillCategoryMaster
        restSkillCategoryMasterMockMvc.perform(get("/api/skill-category-masters/{id}", skillCategoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(skillCategoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.categoryCode").value(DEFAULT_CATEGORY_CODE))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.categoryDescription").value(DEFAULT_CATEGORY_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingSkillCategoryMaster() throws Exception {
        // Get the skillCategoryMaster
        restSkillCategoryMasterMockMvc.perform(get("/api/skill-category-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkillCategoryMaster() throws Exception {
        // Initialize the database
        skillCategoryMasterRepository.saveAndFlush(skillCategoryMaster);

        int databaseSizeBeforeUpdate = skillCategoryMasterRepository.findAll().size();

        // Update the skillCategoryMaster
        SkillCategoryMaster updatedSkillCategoryMaster = skillCategoryMasterRepository.findById(skillCategoryMaster.getId()).get();
        // Disconnect from session so that the updates on updatedSkillCategoryMaster are not directly saved in db
        em.detach(updatedSkillCategoryMaster);
        updatedSkillCategoryMaster
            .categoryCode(UPDATED_CATEGORY_CODE)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryDescription(UPDATED_CATEGORY_DESCRIPTION);

        restSkillCategoryMasterMockMvc.perform(put("/api/skill-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSkillCategoryMaster)))
            .andExpect(status().isOk());

        // Validate the SkillCategoryMaster in the database
        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeUpdate);
        SkillCategoryMaster testSkillCategoryMaster = skillCategoryMasterList.get(skillCategoryMasterList.size() - 1);
        assertThat(testSkillCategoryMaster.getCategoryCode()).isEqualTo(UPDATED_CATEGORY_CODE);
        assertThat(testSkillCategoryMaster.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testSkillCategoryMaster.getCategoryDescription()).isEqualTo(UPDATED_CATEGORY_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSkillCategoryMaster() throws Exception {
        int databaseSizeBeforeUpdate = skillCategoryMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillCategoryMasterMockMvc.perform(put("/api/skill-category-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillCategoryMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SkillCategoryMaster in the database
        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSkillCategoryMaster() throws Exception {
        // Initialize the database
        skillCategoryMasterRepository.saveAndFlush(skillCategoryMaster);

        int databaseSizeBeforeDelete = skillCategoryMasterRepository.findAll().size();

        // Delete the skillCategoryMaster
        restSkillCategoryMasterMockMvc.perform(delete("/api/skill-category-masters/{id}", skillCategoryMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SkillCategoryMaster> skillCategoryMasterList = skillCategoryMasterRepository.findAll();
        assertThat(skillCategoryMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
