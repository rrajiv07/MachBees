package com.machbees.web.rest;

import com.machbees.domain.ProjectFeatureMaster;
import com.machbees.repository.ProjectFeatureMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectFeatureMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectFeatureMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProjectFeatureMasterResource.class);

    private static final String ENTITY_NAME = "projectFeatureMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectFeatureMasterRepository projectFeatureMasterRepository;

    public ProjectFeatureMasterResource(ProjectFeatureMasterRepository projectFeatureMasterRepository) {
        this.projectFeatureMasterRepository = projectFeatureMasterRepository;
    }

    /**
     * {@code POST  /project-feature-masters} : Create a new projectFeatureMaster.
     *
     * @param projectFeatureMaster the projectFeatureMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectFeatureMaster, or with status {@code 400 (Bad Request)} if the projectFeatureMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-feature-masters")
    public ResponseEntity<ProjectFeatureMaster> createProjectFeatureMaster(@Valid @RequestBody ProjectFeatureMaster projectFeatureMaster) throws URISyntaxException {
        log.debug("REST request to save ProjectFeatureMaster : {}", projectFeatureMaster);
        if (projectFeatureMaster.getId() != null) {
            throw new BadRequestAlertException("A new projectFeatureMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectFeatureMaster result = projectFeatureMasterRepository.save(projectFeatureMaster);
        return ResponseEntity.created(new URI("/api/project-feature-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-feature-masters} : Updates an existing projectFeatureMaster.
     *
     * @param projectFeatureMaster the projectFeatureMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectFeatureMaster,
     * or with status {@code 400 (Bad Request)} if the projectFeatureMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectFeatureMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-feature-masters")
    public ResponseEntity<ProjectFeatureMaster> updateProjectFeatureMaster(@Valid @RequestBody ProjectFeatureMaster projectFeatureMaster) throws URISyntaxException {
        log.debug("REST request to update ProjectFeatureMaster : {}", projectFeatureMaster);
        if (projectFeatureMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectFeatureMaster result = projectFeatureMasterRepository.save(projectFeatureMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectFeatureMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-feature-masters} : get all the projectFeatureMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectFeatureMasters in body.
     */
    @GetMapping("/project-feature-masters")
    public List<ProjectFeatureMaster> getAllProjectFeatureMasters() {
        log.debug("REST request to get all ProjectFeatureMasters");
        return projectFeatureMasterRepository.findAll();
    }

    /**
     * {@code GET  /project-feature-masters/:id} : get the "id" projectFeatureMaster.
     *
     * @param id the id of the projectFeatureMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectFeatureMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-feature-masters/{id}")
    public ResponseEntity<ProjectFeatureMaster> getProjectFeatureMaster(@PathVariable Long id) {
        log.debug("REST request to get ProjectFeatureMaster : {}", id);
        Optional<ProjectFeatureMaster> projectFeatureMaster = projectFeatureMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectFeatureMaster);
    }

    /**
     * {@code DELETE  /project-feature-masters/:id} : delete the "id" projectFeatureMaster.
     *
     * @param id the id of the projectFeatureMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-feature-masters/{id}")
    public ResponseEntity<Void> deleteProjectFeatureMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProjectFeatureMaster : {}", id);
        projectFeatureMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
