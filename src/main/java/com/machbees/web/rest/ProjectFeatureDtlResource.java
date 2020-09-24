package com.machbees.web.rest;

import com.machbees.domain.ProjectFeatureDtl;
import com.machbees.repository.ProjectFeatureDtlRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectFeatureDtl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectFeatureDtlResource {

    private final Logger log = LoggerFactory.getLogger(ProjectFeatureDtlResource.class);

    private static final String ENTITY_NAME = "projectFeatureDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectFeatureDtlRepository projectFeatureDtlRepository;

    public ProjectFeatureDtlResource(ProjectFeatureDtlRepository projectFeatureDtlRepository) {
        this.projectFeatureDtlRepository = projectFeatureDtlRepository;
    }

    /**
     * {@code POST  /project-feature-dtls} : Create a new projectFeatureDtl.
     *
     * @param projectFeatureDtl the projectFeatureDtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectFeatureDtl, or with status {@code 400 (Bad Request)} if the projectFeatureDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-feature-dtls")
    public ResponseEntity<ProjectFeatureDtl> createProjectFeatureDtl(@RequestBody ProjectFeatureDtl projectFeatureDtl) throws URISyntaxException {
        log.debug("REST request to save ProjectFeatureDtl : {}", projectFeatureDtl);
        if (projectFeatureDtl.getId() != null) {
            throw new BadRequestAlertException("A new projectFeatureDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectFeatureDtl result = projectFeatureDtlRepository.save(projectFeatureDtl);
        return ResponseEntity.created(new URI("/api/project-feature-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-feature-dtls} : Updates an existing projectFeatureDtl.
     *
     * @param projectFeatureDtl the projectFeatureDtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectFeatureDtl,
     * or with status {@code 400 (Bad Request)} if the projectFeatureDtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectFeatureDtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-feature-dtls")
    public ResponseEntity<ProjectFeatureDtl> updateProjectFeatureDtl(@RequestBody ProjectFeatureDtl projectFeatureDtl) throws URISyntaxException {
        log.debug("REST request to update ProjectFeatureDtl : {}", projectFeatureDtl);
        if (projectFeatureDtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectFeatureDtl result = projectFeatureDtlRepository.save(projectFeatureDtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectFeatureDtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-feature-dtls} : get all the projectFeatureDtls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectFeatureDtls in body.
     */
    @GetMapping("/project-feature-dtls")
    public List<ProjectFeatureDtl> getAllProjectFeatureDtls() {
        log.debug("REST request to get all ProjectFeatureDtls");
        return projectFeatureDtlRepository.findAll();
    }

    /**
     * {@code GET  /project-feature-dtls/:id} : get the "id" projectFeatureDtl.
     *
     * @param id the id of the projectFeatureDtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectFeatureDtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-feature-dtls/{id}")
    public ResponseEntity<ProjectFeatureDtl> getProjectFeatureDtl(@PathVariable Long id) {
        log.debug("REST request to get ProjectFeatureDtl : {}", id);
        Optional<ProjectFeatureDtl> projectFeatureDtl = projectFeatureDtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectFeatureDtl);
    }

    /**
     * {@code DELETE  /project-feature-dtls/:id} : delete the "id" projectFeatureDtl.
     *
     * @param id the id of the projectFeatureDtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-feature-dtls/{id}")
    public ResponseEntity<Void> deleteProjectFeatureDtl(@PathVariable Long id) {
        log.debug("REST request to delete ProjectFeatureDtl : {}", id);
        projectFeatureDtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
