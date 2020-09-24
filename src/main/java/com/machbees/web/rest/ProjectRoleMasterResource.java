package com.machbees.web.rest;

import com.machbees.domain.ProjectRoleMaster;
import com.machbees.repository.ProjectRoleMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectRoleMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectRoleMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProjectRoleMasterResource.class);

    private static final String ENTITY_NAME = "projectRoleMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectRoleMasterRepository projectRoleMasterRepository;

    public ProjectRoleMasterResource(ProjectRoleMasterRepository projectRoleMasterRepository) {
        this.projectRoleMasterRepository = projectRoleMasterRepository;
    }

    /**
     * {@code POST  /project-role-masters} : Create a new projectRoleMaster.
     *
     * @param projectRoleMaster the projectRoleMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectRoleMaster, or with status {@code 400 (Bad Request)} if the projectRoleMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-role-masters")
    public ResponseEntity<ProjectRoleMaster> createProjectRoleMaster(@Valid @RequestBody ProjectRoleMaster projectRoleMaster) throws URISyntaxException {
        log.debug("REST request to save ProjectRoleMaster : {}", projectRoleMaster);
        if (projectRoleMaster.getId() != null) {
            throw new BadRequestAlertException("A new projectRoleMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectRoleMaster result = projectRoleMasterRepository.save(projectRoleMaster);
        return ResponseEntity.created(new URI("/api/project-role-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-role-masters} : Updates an existing projectRoleMaster.
     *
     * @param projectRoleMaster the projectRoleMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectRoleMaster,
     * or with status {@code 400 (Bad Request)} if the projectRoleMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectRoleMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-role-masters")
    public ResponseEntity<ProjectRoleMaster> updateProjectRoleMaster(@Valid @RequestBody ProjectRoleMaster projectRoleMaster) throws URISyntaxException {
        log.debug("REST request to update ProjectRoleMaster : {}", projectRoleMaster);
        if (projectRoleMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectRoleMaster result = projectRoleMasterRepository.save(projectRoleMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectRoleMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-role-masters} : get all the projectRoleMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectRoleMasters in body.
     */
    @GetMapping("/project-role-masters")
    public List<ProjectRoleMaster> getAllProjectRoleMasters() {
        log.debug("REST request to get all ProjectRoleMasters");
        return projectRoleMasterRepository.findAll();
    }

    /**
     * {@code GET  /project-role-masters/:id} : get the "id" projectRoleMaster.
     *
     * @param id the id of the projectRoleMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectRoleMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-role-masters/{id}")
    public ResponseEntity<ProjectRoleMaster> getProjectRoleMaster(@PathVariable Long id) {
        log.debug("REST request to get ProjectRoleMaster : {}", id);
        Optional<ProjectRoleMaster> projectRoleMaster = projectRoleMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectRoleMaster);
    }

    /**
     * {@code DELETE  /project-role-masters/:id} : delete the "id" projectRoleMaster.
     *
     * @param id the id of the projectRoleMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-role-masters/{id}")
    public ResponseEntity<Void> deleteProjectRoleMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProjectRoleMaster : {}", id);
        projectRoleMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
