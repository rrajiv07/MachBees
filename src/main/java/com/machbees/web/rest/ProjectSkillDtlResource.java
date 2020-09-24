package com.machbees.web.rest;

import com.machbees.domain.ProjectSkillDtl;
import com.machbees.repository.ProjectSkillDtlRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectSkillDtl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectSkillDtlResource {

    private final Logger log = LoggerFactory.getLogger(ProjectSkillDtlResource.class);

    private static final String ENTITY_NAME = "projectSkillDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectSkillDtlRepository projectSkillDtlRepository;

    public ProjectSkillDtlResource(ProjectSkillDtlRepository projectSkillDtlRepository) {
        this.projectSkillDtlRepository = projectSkillDtlRepository;
    }

    /**
     * {@code POST  /project-skill-dtls} : Create a new projectSkillDtl.
     *
     * @param projectSkillDtl the projectSkillDtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectSkillDtl, or with status {@code 400 (Bad Request)} if the projectSkillDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-skill-dtls")
    public ResponseEntity<ProjectSkillDtl> createProjectSkillDtl(@RequestBody ProjectSkillDtl projectSkillDtl) throws URISyntaxException {
        log.debug("REST request to save ProjectSkillDtl : {}", projectSkillDtl);
        if (projectSkillDtl.getId() != null) {
            throw new BadRequestAlertException("A new projectSkillDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectSkillDtl result = projectSkillDtlRepository.save(projectSkillDtl);
        return ResponseEntity.created(new URI("/api/project-skill-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-skill-dtls} : Updates an existing projectSkillDtl.
     *
     * @param projectSkillDtl the projectSkillDtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectSkillDtl,
     * or with status {@code 400 (Bad Request)} if the projectSkillDtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectSkillDtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-skill-dtls")
    public ResponseEntity<ProjectSkillDtl> updateProjectSkillDtl(@RequestBody ProjectSkillDtl projectSkillDtl) throws URISyntaxException {
        log.debug("REST request to update ProjectSkillDtl : {}", projectSkillDtl);
        if (projectSkillDtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectSkillDtl result = projectSkillDtlRepository.save(projectSkillDtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectSkillDtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-skill-dtls} : get all the projectSkillDtls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectSkillDtls in body.
     */
    @GetMapping("/project-skill-dtls")
    public List<ProjectSkillDtl> getAllProjectSkillDtls() {
        log.debug("REST request to get all ProjectSkillDtls");
        return projectSkillDtlRepository.findAll();
    }

    /**
     * {@code GET  /project-skill-dtls/:id} : get the "id" projectSkillDtl.
     *
     * @param id the id of the projectSkillDtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectSkillDtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-skill-dtls/{id}")
    public ResponseEntity<ProjectSkillDtl> getProjectSkillDtl(@PathVariable Long id) {
        log.debug("REST request to get ProjectSkillDtl : {}", id);
        Optional<ProjectSkillDtl> projectSkillDtl = projectSkillDtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectSkillDtl);
    }

    /**
     * {@code DELETE  /project-skill-dtls/:id} : delete the "id" projectSkillDtl.
     *
     * @param id the id of the projectSkillDtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-skill-dtls/{id}")
    public ResponseEntity<Void> deleteProjectSkillDtl(@PathVariable Long id) {
        log.debug("REST request to delete ProjectSkillDtl : {}", id);
        projectSkillDtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
