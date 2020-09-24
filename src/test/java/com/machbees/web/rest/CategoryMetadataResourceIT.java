package com.machbees.web.rest;

import com.machbees.MachBeesApp;
import com.machbees.domain.CategoryMetadata;
import com.machbees.repository.CategoryMetadataRepository;

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
 * Integration tests for the {@link CategoryMetadataResource} REST controller.
 */
@SpringBootTest(classes = MachBeesApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategoryMetadataResourceIT {

    private static final String DEFAULT_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEQUENCE_NUMBER = 1;
    private static final Integer UPDATED_SEQUENCE_NUMBER = 2;

    @Autowired
    private CategoryMetadataRepository categoryMetadataRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryMetadataMockMvc;

    private CategoryMetadata categoryMetadata;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryMetadata createEntity(EntityManager em) {
        CategoryMetadata categoryMetadata = new CategoryMetadata()
            .categoryCode(DEFAULT_CATEGORY_CODE)
            .categoryName(DEFAULT_CATEGORY_NAME)
            .categoryDescription(DEFAULT_CATEGORY_DESCRIPTION)
            .sequenceNumber(DEFAULT_SEQUENCE_NUMBER);
        return categoryMetadata;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryMetadata createUpdatedEntity(EntityManager em) {
        CategoryMetadata categoryMetadata = new CategoryMetadata()
            .categoryCode(UPDATED_CATEGORY_CODE)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryDescription(UPDATED_CATEGORY_DESCRIPTION)
            .sequenceNumber(UPDATED_SEQUENCE_NUMBER);
        return categoryMetadata;
    }

    @BeforeEach
    public void initTest() {
        categoryMetadata = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategoryMetadata() throws Exception {
        int databaseSizeBeforeCreate = categoryMetadataRepository.findAll().size();
        // Create the CategoryMetadata
        restCategoryMetadataMockMvc.perform(post("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryMetadata)))
            .andExpect(status().isCreated());

        // Validate the CategoryMetadata in the database
        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeCreate + 1);
        CategoryMetadata testCategoryMetadata = categoryMetadataList.get(categoryMetadataList.size() - 1);
        assertThat(testCategoryMetadata.getCategoryCode()).isEqualTo(DEFAULT_CATEGORY_CODE);
        assertThat(testCategoryMetadata.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testCategoryMetadata.getCategoryDescription()).isEqualTo(DEFAULT_CATEGORY_DESCRIPTION);
        assertThat(testCategoryMetadata.getSequenceNumber()).isEqualTo(DEFAULT_SEQUENCE_NUMBER);
    }

    @Test
    @Transactional
    public void createCategoryMetadataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categoryMetadataRepository.findAll().size();

        // Create the CategoryMetadata with an existing ID
        categoryMetadata.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryMetadataMockMvc.perform(post("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryMetadata)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryMetadata in the database
        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCategoryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryMetadataRepository.findAll().size();
        // set the field null
        categoryMetadata.setCategoryCode(null);

        // Create the CategoryMetadata, which fails.


        restCategoryMetadataMockMvc.perform(post("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryMetadata)))
            .andExpect(status().isBadRequest());

        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryMetadataRepository.findAll().size();
        // set the field null
        categoryMetadata.setCategoryName(null);

        // Create the CategoryMetadata, which fails.


        restCategoryMetadataMockMvc.perform(post("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryMetadata)))
            .andExpect(status().isBadRequest());

        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCategoryDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryMetadataRepository.findAll().size();
        // set the field null
        categoryMetadata.setCategoryDescription(null);

        // Create the CategoryMetadata, which fails.


        restCategoryMetadataMockMvc.perform(post("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryMetadata)))
            .andExpect(status().isBadRequest());

        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSequenceNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = categoryMetadataRepository.findAll().size();
        // set the field null
        categoryMetadata.setSequenceNumber(null);

        // Create the CategoryMetadata, which fails.


        restCategoryMetadataMockMvc.perform(post("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryMetadata)))
            .andExpect(status().isBadRequest());

        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCategoryMetadata() throws Exception {
        // Initialize the database
        categoryMetadataRepository.saveAndFlush(categoryMetadata);

        // Get all the categoryMetadataList
        restCategoryMetadataMockMvc.perform(get("/api/category-metadata?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryMetadata.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryCode").value(hasItem(DEFAULT_CATEGORY_CODE)))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].categoryDescription").value(hasItem(DEFAULT_CATEGORY_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].sequenceNumber").value(hasItem(DEFAULT_SEQUENCE_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getCategoryMetadata() throws Exception {
        // Initialize the database
        categoryMetadataRepository.saveAndFlush(categoryMetadata);

        // Get the categoryMetadata
        restCategoryMetadataMockMvc.perform(get("/api/category-metadata/{id}", categoryMetadata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoryMetadata.getId().intValue()))
            .andExpect(jsonPath("$.categoryCode").value(DEFAULT_CATEGORY_CODE))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.categoryDescription").value(DEFAULT_CATEGORY_DESCRIPTION))
            .andExpect(jsonPath("$.sequenceNumber").value(DEFAULT_SEQUENCE_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingCategoryMetadata() throws Exception {
        // Get the categoryMetadata
        restCategoryMetadataMockMvc.perform(get("/api/category-metadata/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategoryMetadata() throws Exception {
        // Initialize the database
        categoryMetadataRepository.saveAndFlush(categoryMetadata);

        int databaseSizeBeforeUpdate = categoryMetadataRepository.findAll().size();

        // Update the categoryMetadata
        CategoryMetadata updatedCategoryMetadata = categoryMetadataRepository.findById(categoryMetadata.getId()).get();
        // Disconnect from session so that the updates on updatedCategoryMetadata are not directly saved in db
        em.detach(updatedCategoryMetadata);
        updatedCategoryMetadata
            .categoryCode(UPDATED_CATEGORY_CODE)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryDescription(UPDATED_CATEGORY_DESCRIPTION)
            .sequenceNumber(UPDATED_SEQUENCE_NUMBER);

        restCategoryMetadataMockMvc.perform(put("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCategoryMetadata)))
            .andExpect(status().isOk());

        // Validate the CategoryMetadata in the database
        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeUpdate);
        CategoryMetadata testCategoryMetadata = categoryMetadataList.get(categoryMetadataList.size() - 1);
        assertThat(testCategoryMetadata.getCategoryCode()).isEqualTo(UPDATED_CATEGORY_CODE);
        assertThat(testCategoryMetadata.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testCategoryMetadata.getCategoryDescription()).isEqualTo(UPDATED_CATEGORY_DESCRIPTION);
        assertThat(testCategoryMetadata.getSequenceNumber()).isEqualTo(UPDATED_SEQUENCE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingCategoryMetadata() throws Exception {
        int databaseSizeBeforeUpdate = categoryMetadataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryMetadataMockMvc.perform(put("/api/category-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categoryMetadata)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryMetadata in the database
        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategoryMetadata() throws Exception {
        // Initialize the database
        categoryMetadataRepository.saveAndFlush(categoryMetadata);

        int databaseSizeBeforeDelete = categoryMetadataRepository.findAll().size();

        // Delete the categoryMetadata
        restCategoryMetadataMockMvc.perform(delete("/api/category-metadata/{id}", categoryMetadata.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategoryMetadata> categoryMetadataList = categoryMetadataRepository.findAll();
        assertThat(categoryMetadataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
