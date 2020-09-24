package com.machbees.web.rest;

import com.machbees.domain.ProjectRoleDtl;
import com.machbees.repository.ProjectRoleDtlRepository;
import com.machbees.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.machbees.domain.ProjectRoleDtl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectRoleDtlResource {

    private final Logger log = LoggerFactory.getLogger(ProjectRoleDtlResource.class);

    private static final String ENTITY_NAME = "projectRoleDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectRoleDtlRepository projectRoleDtlRepository;

    public ProjectRoleDtlResource(ProjectRoleDtlRepository projectRoleDtlRepository) {
        this.projectRoleDtlRepository = projectRoleDtlRepository;
    }

    /**
     * {@code POST  /project-role-dtls} : Create a new projectRoleDtl.
     *
     * @param projectRoleDtl the projectRoleDtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectRoleDtl, or with status {@code 400 (Bad Request)} if the projectRoleDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-role-dtls")
    public ResponseEntity<ProjectRoleDtl> createProjectRoleDtl(@RequestBody ProjectRoleDtl projectRoleDtl) throws URISyntaxException {
        log.debug("REST request to save ProjectRoleDtl : {}", projectRoleDtl);
        if (projectRoleDtl.getId() != null) {
            throw new BadRequestAlertException("A new projectRoleDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectRoleDtl result = projectRoleDtlRepository.save(projectRoleDtl);
        return ResponseEntity.created(new URI("/api/project-role-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-role-dtls} : Updates an existing projectRoleDtl.
     *
     * @param projectRoleDtl the projectRoleDtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectRoleDtl,
     * or with status {@code 400 (Bad Request)} if the projectRoleDtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectRoleDtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-role-dtls")
    public ResponseEntity<ProjectRoleDtl> updateProjectRoleDtl(@RequestBody ProjectRoleDtl projectRoleDtl) throws URISyntaxException {
        log.debug("REST request to update ProjectRoleDtl : {}", projectRoleDtl);
        if (projectRoleDtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectRoleDtl result = projectRoleDtlRepository.save(projectRoleDtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectRoleDtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-role-dtls} : get all the projectRoleDtls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectRoleDtls in body.
     */
    @GetMapping("/project-role-dtls")
    public List<ProjectRoleDtl> getAllProjectRoleDtls() {
        log.debug("REST request to get all ProjectRoleDtls");
        return projectRoleDtlRepository.findAll();
    }

    /**
     * {@code GET  /project-role-dtls/:id} : get the "id" projectRoleDtl.
     *
     * @param id the id of the projectRoleDtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectRoleDtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-role-dtls/{id}")
    public ResponseEntity<ProjectRoleDtl> getProjectRoleDtl(@PathVariable Long id) {
        log.debug("REST request to get ProjectRoleDtl : {}", id);
        Optional<ProjectRoleDtl> projectRoleDtl = projectRoleDtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectRoleDtl);
    }

    /**
     * {@code DELETE  /project-role-dtls/:id} : delete the "id" projectRoleDtl.
     *
     * @param id the id of the projectRoleDtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-role-dtls/{id}")
    public ResponseEntity<Void> deleteProjectRoleDtl(@PathVariable Long id) {
        log.debug("REST request to delete ProjectRoleDtl : {}", id);
        projectRoleDtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
