package com.machbees.web.rest;

import com.machbees.domain.ProjectBudgetDtl;
import com.machbees.repository.ProjectBudgetDtlRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectBudgetDtl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectBudgetDtlResource {

    private final Logger log = LoggerFactory.getLogger(ProjectBudgetDtlResource.class);

    private static final String ENTITY_NAME = "projectBudgetDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectBudgetDtlRepository projectBudgetDtlRepository;

    public ProjectBudgetDtlResource(ProjectBudgetDtlRepository projectBudgetDtlRepository) {
        this.projectBudgetDtlRepository = projectBudgetDtlRepository;
    }

    /**
     * {@code POST  /project-budget-dtls} : Create a new projectBudgetDtl.
     *
     * @param projectBudgetDtl the projectBudgetDtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectBudgetDtl, or with status {@code 400 (Bad Request)} if the projectBudgetDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-budget-dtls")
    public ResponseEntity<ProjectBudgetDtl> createProjectBudgetDtl(@RequestBody ProjectBudgetDtl projectBudgetDtl) throws URISyntaxException {
        log.debug("REST request to save ProjectBudgetDtl : {}", projectBudgetDtl);
        if (projectBudgetDtl.getId() != null) {
            throw new BadRequestAlertException("A new projectBudgetDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectBudgetDtl result = projectBudgetDtlRepository.save(projectBudgetDtl);
        return ResponseEntity.created(new URI("/api/project-budget-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-budget-dtls} : Updates an existing projectBudgetDtl.
     *
     * @param projectBudgetDtl the projectBudgetDtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectBudgetDtl,
     * or with status {@code 400 (Bad Request)} if the projectBudgetDtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectBudgetDtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-budget-dtls")
    public ResponseEntity<ProjectBudgetDtl> updateProjectBudgetDtl(@RequestBody ProjectBudgetDtl projectBudgetDtl) throws URISyntaxException {
        log.debug("REST request to update ProjectBudgetDtl : {}", projectBudgetDtl);
        if (projectBudgetDtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectBudgetDtl result = projectBudgetDtlRepository.save(projectBudgetDtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectBudgetDtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-budget-dtls} : get all the projectBudgetDtls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectBudgetDtls in body.
     */
    @GetMapping("/project-budget-dtls")
    public List<ProjectBudgetDtl> getAllProjectBudgetDtls() {
        log.debug("REST request to get all ProjectBudgetDtls");
        return projectBudgetDtlRepository.findAll();
    }

    /**
     * {@code GET  /project-budget-dtls/:id} : get the "id" projectBudgetDtl.
     *
     * @param id the id of the projectBudgetDtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectBudgetDtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-budget-dtls/{id}")
    public ResponseEntity<ProjectBudgetDtl> getProjectBudgetDtl(@PathVariable Long id) {
        log.debug("REST request to get ProjectBudgetDtl : {}", id);
        Optional<ProjectBudgetDtl> projectBudgetDtl = projectBudgetDtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectBudgetDtl);
    }

    /**
     * {@code DELETE  /project-budget-dtls/:id} : delete the "id" projectBudgetDtl.
     *
     * @param id the id of the projectBudgetDtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-budget-dtls/{id}")
    public ResponseEntity<Void> deleteProjectBudgetDtl(@PathVariable Long id) {
        log.debug("REST request to delete ProjectBudgetDtl : {}", id);
        projectBudgetDtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
