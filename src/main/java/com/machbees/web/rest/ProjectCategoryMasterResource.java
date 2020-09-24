package com.machbees.web.rest;

import com.machbees.domain.ProjectCategoryMaster;
import com.machbees.repository.ProjectCategoryMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectCategoryMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectCategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProjectCategoryMasterResource.class);

    private static final String ENTITY_NAME = "projectCategoryMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectCategoryMasterRepository projectCategoryMasterRepository;

    public ProjectCategoryMasterResource(ProjectCategoryMasterRepository projectCategoryMasterRepository) {
        this.projectCategoryMasterRepository = projectCategoryMasterRepository;
    }

    /**
     * {@code POST  /project-category-masters} : Create a new projectCategoryMaster.
     *
     * @param projectCategoryMaster the projectCategoryMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectCategoryMaster, or with status {@code 400 (Bad Request)} if the projectCategoryMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-category-masters")
    public ResponseEntity<ProjectCategoryMaster> createProjectCategoryMaster(@Valid @RequestBody ProjectCategoryMaster projectCategoryMaster) throws URISyntaxException {
        log.debug("REST request to save ProjectCategoryMaster : {}", projectCategoryMaster);
        if (projectCategoryMaster.getId() != null) {
            throw new BadRequestAlertException("A new projectCategoryMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectCategoryMaster result = projectCategoryMasterRepository.save(projectCategoryMaster);
        return ResponseEntity.created(new URI("/api/project-category-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-category-masters} : Updates an existing projectCategoryMaster.
     *
     * @param projectCategoryMaster the projectCategoryMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectCategoryMaster,
     * or with status {@code 400 (Bad Request)} if the projectCategoryMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectCategoryMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-category-masters")
    public ResponseEntity<ProjectCategoryMaster> updateProjectCategoryMaster(@Valid @RequestBody ProjectCategoryMaster projectCategoryMaster) throws URISyntaxException {
        log.debug("REST request to update ProjectCategoryMaster : {}", projectCategoryMaster);
        if (projectCategoryMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectCategoryMaster result = projectCategoryMasterRepository.save(projectCategoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectCategoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-category-masters} : get all the projectCategoryMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectCategoryMasters in body.
     */
    @GetMapping("/project-category-masters")
    public List<ProjectCategoryMaster> getAllProjectCategoryMasters() {
        log.debug("REST request to get all ProjectCategoryMasters");
        return projectCategoryMasterRepository.findAll();
    }

    /**
     * {@code GET  /project-category-masters/:id} : get the "id" projectCategoryMaster.
     *
     * @param id the id of the projectCategoryMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectCategoryMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-category-masters/{id}")
    public ResponseEntity<ProjectCategoryMaster> getProjectCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get ProjectCategoryMaster : {}", id);
        Optional<ProjectCategoryMaster> projectCategoryMaster = projectCategoryMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectCategoryMaster);
    }

    /**
     * {@code DELETE  /project-category-masters/:id} : delete the "id" projectCategoryMaster.
     *
     * @param id the id of the projectCategoryMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-category-masters/{id}")
    public ResponseEntity<Void> deleteProjectCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProjectCategoryMaster : {}", id);
        projectCategoryMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
