package com.machbees.web.rest;

import com.machbees.domain.ProjectTypeMaster;
import com.machbees.repository.ProjectTypeMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectTypeMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProjectTypeMasterResource.class);

    private static final String ENTITY_NAME = "projectTypeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectTypeMasterRepository projectTypeMasterRepository;

    public ProjectTypeMasterResource(ProjectTypeMasterRepository projectTypeMasterRepository) {
        this.projectTypeMasterRepository = projectTypeMasterRepository;
    }

    /**
     * {@code POST  /project-type-masters} : Create a new projectTypeMaster.
     *
     * @param projectTypeMaster the projectTypeMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectTypeMaster, or with status {@code 400 (Bad Request)} if the projectTypeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-type-masters")
    public ResponseEntity<ProjectTypeMaster> createProjectTypeMaster(@Valid @RequestBody ProjectTypeMaster projectTypeMaster) throws URISyntaxException {
        log.debug("REST request to save ProjectTypeMaster : {}", projectTypeMaster);
        if (projectTypeMaster.getId() != null) {
            throw new BadRequestAlertException("A new projectTypeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectTypeMaster result = projectTypeMasterRepository.save(projectTypeMaster);
        return ResponseEntity.created(new URI("/api/project-type-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-type-masters} : Updates an existing projectTypeMaster.
     *
     * @param projectTypeMaster the projectTypeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectTypeMaster,
     * or with status {@code 400 (Bad Request)} if the projectTypeMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectTypeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-type-masters")
    public ResponseEntity<ProjectTypeMaster> updateProjectTypeMaster(@Valid @RequestBody ProjectTypeMaster projectTypeMaster) throws URISyntaxException {
        log.debug("REST request to update ProjectTypeMaster : {}", projectTypeMaster);
        if (projectTypeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectTypeMaster result = projectTypeMasterRepository.save(projectTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-type-masters} : get all the projectTypeMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectTypeMasters in body.
     */
    @GetMapping("/project-type-masters")
    public List<ProjectTypeMaster> getAllProjectTypeMasters() {
        log.debug("REST request to get all ProjectTypeMasters");
        return projectTypeMasterRepository.findAll();
    }

    /**
     * {@code GET  /project-type-masters/:id} : get the "id" projectTypeMaster.
     *
     * @param id the id of the projectTypeMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectTypeMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-type-masters/{id}")
    public ResponseEntity<ProjectTypeMaster> getProjectTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get ProjectTypeMaster : {}", id);
        Optional<ProjectTypeMaster> projectTypeMaster = projectTypeMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectTypeMaster);
    }

    /**
     * {@code DELETE  /project-type-masters/:id} : delete the "id" projectTypeMaster.
     *
     * @param id the id of the projectTypeMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-type-masters/{id}")
    public ResponseEntity<Void> deleteProjectTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProjectTypeMaster : {}", id);
        projectTypeMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
