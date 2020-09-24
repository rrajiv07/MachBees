package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.SkillMaster;
import com.machbees.repository.SkillMasterRepository;

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
 * Integration tests for the {@link SkillMasterResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SkillMasterResourceIT {

    private static final String DEFAULT_SKILL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SkillMasterRepository skillMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSkillMasterMockMvc;

    private SkillMaster skillMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillMaster createEntity(EntityManager em) {
        SkillMaster skillMaster = new SkillMaster()
            .skillCode(DEFAULT_SKILL_CODE)
            .skillName(DEFAULT_SKILL_NAME)
            .skillDescription(DEFAULT_SKILL_DESCRIPTION);
        return skillMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillMaster createUpdatedEntity(EntityManager em) {
        SkillMaster skillMaster = new SkillMaster()
            .skillCode(UPDATED_SKILL_CODE)
            .skillName(UPDATED_SKILL_NAME)
            .skillDescription(UPDATED_SKILL_DESCRIPTION);
        return skillMaster;
    }

    @BeforeEach
    public void initTest() {
        skillMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkillMaster() throws Exception {
        int databaseSizeBeforeCreate = skillMasterRepository.findAll().size();
        // Create the SkillMaster
        restSkillMasterMockMvc.perform(post("/api/skill-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillMaster)))
            .andExpect(status().isCreated());

        // Validate the SkillMaster in the database
        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeCreate + 1);
        SkillMaster testSkillMaster = skillMasterList.get(skillMasterList.size() - 1);
        assertThat(testSkillMaster.getSkillCode()).isEqualTo(DEFAULT_SKILL_CODE);
        assertThat(testSkillMaster.getSkillName()).isEqualTo(DEFAULT_SKILL_NAME);
        assertThat(testSkillMaster.getSkillDescription()).isEqualTo(DEFAULT_SKILL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSkillMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillMasterRepository.findAll().size();

        // Create the SkillMaster with an existing ID
        skillMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillMasterMockMvc.perform(post("/api/skill-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SkillMaster in the database
        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSkillCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = skillMasterRepository.findAll().size();
        // set the field null
        skillMaster.setSkillCode(null);

        // Create the SkillMaster, which fails.


        restSkillMasterMockMvc.perform(post("/api/skill-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillMaster)))
            .andExpect(status().isBadRequest());

        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSkillNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = skillMasterRepository.findAll().size();
        // set the field null
        skillMaster.setSkillName(null);

        // Create the SkillMaster, which fails.


        restSkillMasterMockMvc.perform(post("/api/skill-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillMaster)))
            .andExpect(status().isBadRequest());

        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSkillDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = skillMasterRepository.findAll().size();
        // set the field null
        skillMaster.setSkillDescription(null);

        // Create the SkillMaster, which fails.


        restSkillMasterMockMvc.perform(post("/api/skill-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillMaster)))
            .andExpect(status().isBadRequest());

        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSkillMasters() throws Exception {
        // Initialize the database
        skillMasterRepository.saveAndFlush(skillMaster);

        // Get all the skillMasterList
        restSkillMasterMockMvc.perform(get("/api/skill-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skillMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].skillCode").value(hasItem(DEFAULT_SKILL_CODE)))
            .andExpect(jsonPath("$.[*].skillName").value(hasItem(DEFAULT_SKILL_NAME)))
            .andExpect(jsonPath("$.[*].skillDescription").value(hasItem(DEFAULT_SKILL_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getSkillMaster() throws Exception {
        // Initialize the database
        skillMasterRepository.saveAndFlush(skillMaster);

        // Get the skillMaster
        restSkillMasterMockMvc.perform(get("/api/skill-masters/{id}", skillMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(skillMaster.getId().intValue()))
            .andExpect(jsonPath("$.skillCode").value(DEFAULT_SKILL_CODE))
            .andExpect(jsonPath("$.skillName").value(DEFAULT_SKILL_NAME))
            .andExpect(jsonPath("$.skillDescription").value(DEFAULT_SKILL_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingSkillMaster() throws Exception {
        // Get the skillMaster
        restSkillMasterMockMvc.perform(get("/api/skill-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkillMaster() throws Exception {
        // Initialize the database
        skillMasterRepository.saveAndFlush(skillMaster);

        int databaseSizeBeforeUpdate = skillMasterRepository.findAll().size();

        // Update the skillMaster
        SkillMaster updatedSkillMaster = skillMasterRepository.findById(skillMaster.getId()).get();
        // Disconnect from session so that the updates on updatedSkillMaster are not directly saved in db
        em.detach(updatedSkillMaster);
        updatedSkillMaster
            .skillCode(UPDATED_SKILL_CODE)
            .skillName(UPDATED_SKILL_NAME)
            .skillDescription(UPDATED_SKILL_DESCRIPTION);

        restSkillMasterMockMvc.perform(put("/api/skill-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSkillMaster)))
            .andExpect(status().isOk());

        // Validate the SkillMaster in the database
        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeUpdate);
        SkillMaster testSkillMaster = skillMasterList.get(skillMasterList.size() - 1);
        assertThat(testSkillMaster.getSkillCode()).isEqualTo(UPDATED_SKILL_CODE);
        assertThat(testSkillMaster.getSkillName()).isEqualTo(UPDATED_SKILL_NAME);
        assertThat(testSkillMaster.getSkillDescription()).isEqualTo(UPDATED_SKILL_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSkillMaster() throws Exception {
        int databaseSizeBeforeUpdate = skillMasterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillMasterMockMvc.perform(put("/api/skill-masters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillMaster)))
            .andExpect(status().isBadRequest());

        // Validate the SkillMaster in the database
        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSkillMaster() throws Exception {
        // Initialize the database
        skillMasterRepository.saveAndFlush(skillMaster);

        int databaseSizeBeforeDelete = skillMasterRepository.findAll().size();

        // Delete the skillMaster
        restSkillMasterMockMvc.perform(delete("/api/skill-masters/{id}", skillMaster.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SkillMaster> skillMasterList = skillMasterRepository.findAll();
        assertThat(skillMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
