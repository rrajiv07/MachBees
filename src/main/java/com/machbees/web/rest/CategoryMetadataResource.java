package com.machbees.web.rest;

import com.machbees.domain.CategoryMetadata;
import com.machbees.repository.CategoryMetadataRepository;
import com.machbees.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.machbees.domain.CategoryMetadata}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CategoryMetadataResource {

    private final Logger log = LoggerFactory.getLogger(CategoryMetadataResource.class);

    private static final String ENTITY_NAME = "categoryMetadata";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryMetadataRepository categoryMetadataRepository;

    public CategoryMetadataResource(CategoryMetadataRepository categoryMetadataRepository) {
        this.categoryMetadataRepository = categoryMetadataRepository;
    }

    /**
     * {@code POST  /category-metadata} : Create a new categoryMetadata.
     *
     * @param categoryMetadata the categoryMetadata to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryMetadata, or with status {@code 400 (Bad Request)} if the categoryMetadata has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-metadata")
    public ResponseEntity<CategoryMetadata> createCategoryMetadata(@Valid @RequestBody CategoryMetadata categoryMetadata) throws URISyntaxException {
        log.debug("REST request to save CategoryMetadata : {}", categoryMetadata);
        if (categoryMetadata.getId() != null) {
            throw new BadRequestAlertException("A new categoryMetadata cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryMetadata result = categoryMetadataRepository.save(categoryMetadata);
        return ResponseEntity.created(new URI("/api/category-metadata/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-metadata} : Updates an existing categoryMetadata.
     *
     * @param categoryMetadata the categoryMetadata to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryMetadata,
     * or with status {@code 400 (Bad Request)} if the categoryMetadata is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryMetadata couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-metadata")
    public ResponseEntity<CategoryMetadata> updateCategoryMetadata(@Valid @RequestBody CategoryMetadata categoryMetadata) throws URISyntaxException {
        log.debug("REST request to update CategoryMetadata : {}", categoryMetadata);
        if (categoryMetadata.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoryMetadata result = categoryMetadataRepository.save(categoryMetadata);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, categoryMetadata.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /category-metadata} : get all the categoryMetadata.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryMetadata in body.
     */
    @GetMapping("/category-metadata")
    public List<CategoryMetadata> getAllCategoryMetadata() {
        log.debug("REST request to get all CategoryMetadata");
        return categoryMetadataRepository.findAll();
    }

    /**
     * {@code GET  /category-metadata/:id} : get the "id" categoryMetadata.
     *
     * @param id the id of the categoryMetadata to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryMetadata, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-metadata/{id}")
    public ResponseEntity<CategoryMetadata> getCategoryMetadata(@PathVariable Long id) {
        log.debug("REST request to get CategoryMetadata : {}", id);
        Optional<CategoryMetadata> categoryMetadata = categoryMetadataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(categoryMetadata);
    }

    /**
     * {@code DELETE  /category-metadata/:id} : delete the "id" categoryMetadata.
     *
     * @param id the id of the categoryMetadata to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-metadata/{id}")
    public ResponseEntity<Void> deleteCategoryMetadata(@PathVariable Long id) {
        log.debug("REST request to delete CategoryMetadata : {}", id);
        categoryMetadataRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
