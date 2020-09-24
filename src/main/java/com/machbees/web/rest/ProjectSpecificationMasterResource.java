package com.machbees.web.rest;

import com.machbees.domain.ProjectSpecificationMaster;
import com.machbees.repository.ProjectSpecificationMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectSpecificationMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectSpecificationMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProjectSpecificationMasterResource.class);

    private static final String ENTITY_NAME = "projectSpecificationMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectSpecificationMasterRepository projectSpecificationMasterRepository;

    public ProjectSpecificationMasterResource(ProjectSpecificationMasterRepository projectSpecificationMasterRepository) {
        this.projectSpecificationMasterRepository = projectSpecificationMasterRepository;
    }

    /**
     * {@code POST  /project-specification-masters} : Create a new projectSpecificationMaster.
     *
     * @param projectSpecificationMaster the projectSpecificationMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectSpecificationMaster, or with status {@code 400 (Bad Request)} if the projectSpecificationMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-specification-masters")
    public ResponseEntity<ProjectSpecificationMaster> createProjectSpecificationMaster(@Valid @RequestBody ProjectSpecificationMaster projectSpecificationMaster) throws URISyntaxException {
        log.debug("REST request to save ProjectSpecificationMaster : {}", projectSpecificationMaster);
        if (projectSpecificationMaster.getId() != null) {
            throw new BadRequestAlertException("A new projectSpecificationMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectSpecificationMaster result = projectSpecificationMasterRepository.save(projectSpecificationMaster);
        return ResponseEntity.created(new URI("/api/project-specification-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-specification-masters} : Updates an existing projectSpecificationMaster.
     *
     * @param projectSpecificationMaster the projectSpecificationMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectSpecificationMaster,
     * or with status {@code 400 (Bad Request)} if the projectSpecificationMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectSpecificationMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-specification-masters")
    public ResponseEntity<ProjectSpecificationMaster> updateProjectSpecificationMaster(@Valid @RequestBody ProjectSpecificationMaster projectSpecificationMaster) throws URISyntaxException {
        log.debug("REST request to update ProjectSpecificationMaster : {}", projectSpecificationMaster);
        if (projectSpecificationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectSpecificationMaster result = projectSpecificationMasterRepository.save(projectSpecificationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectSpecificationMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-specification-masters} : get all the projectSpecificationMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectSpecificationMasters in body.
     */
    @GetMapping("/project-specification-masters")
    public List<ProjectSpecificationMaster> getAllProjectSpecificationMasters() {
        log.debug("REST request to get all ProjectSpecificationMasters");
        return projectSpecificationMasterRepository.findAll();
    }

    /**
     * {@code GET  /project-specification-masters/:id} : get the "id" projectSpecificationMaster.
     *
     * @param id the id of the projectSpecificationMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectSpecificationMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-specification-masters/{id}")
    public ResponseEntity<ProjectSpecificationMaster> getProjectSpecificationMaster(@PathVariable Long id) {
        log.debug("REST request to get ProjectSpecificationMaster : {}", id);
        Optional<ProjectSpecificationMaster> projectSpecificationMaster = projectSpecificationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectSpecificationMaster);
    }

    /**
     * {@code DELETE  /project-specification-masters/:id} : delete the "id" projectSpecificationMaster.
     *
     * @param id the id of the projectSpecificationMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-specification-masters/{id}")
    public ResponseEntity<Void> deleteProjectSpecificationMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProjectSpecificationMaster : {}", id);
        projectSpecificationMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
