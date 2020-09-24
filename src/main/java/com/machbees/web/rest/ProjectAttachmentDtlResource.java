package com.machbees.web.rest;

import com.machbees.domain.ProjectAttachmentDtl;
import com.machbees.repository.ProjectAttachmentDtlRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectAttachmentDtl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectAttachmentDtlResource {

    private final Logger log = LoggerFactory.getLogger(ProjectAttachmentDtlResource.class);

    private static final String ENTITY_NAME = "projectAttachmentDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectAttachmentDtlRepository projectAttachmentDtlRepository;

    public ProjectAttachmentDtlResource(ProjectAttachmentDtlRepository projectAttachmentDtlRepository) {
        this.projectAttachmentDtlRepository = projectAttachmentDtlRepository;
    }

    /**
     * {@code POST  /project-attachment-dtls} : Create a new projectAttachmentDtl.
     *
     * @param projectAttachmentDtl the projectAttachmentDtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectAttachmentDtl, or with status {@code 400 (Bad Request)} if the projectAttachmentDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-attachment-dtls")
    public ResponseEntity<ProjectAttachmentDtl> createProjectAttachmentDtl(@Valid @RequestBody ProjectAttachmentDtl projectAttachmentDtl) throws URISyntaxException {
        log.debug("REST request to save ProjectAttachmentDtl : {}", projectAttachmentDtl);
        if (projectAttachmentDtl.getId() != null) {
            throw new BadRequestAlertException("A new projectAttachmentDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectAttachmentDtl result = projectAttachmentDtlRepository.save(projectAttachmentDtl);
        return ResponseEntity.created(new URI("/api/project-attachment-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-attachment-dtls} : Updates an existing projectAttachmentDtl.
     *
     * @param projectAttachmentDtl the projectAttachmentDtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectAttachmentDtl,
     * or with status {@code 400 (Bad Request)} if the projectAttachmentDtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectAttachmentDtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-attachment-dtls")
    public ResponseEntity<ProjectAttachmentDtl> updateProjectAttachmentDtl(@Valid @RequestBody ProjectAttachmentDtl projectAttachmentDtl) throws URISyntaxException {
        log.debug("REST request to update ProjectAttachmentDtl : {}", projectAttachmentDtl);
        if (projectAttachmentDtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectAttachmentDtl result = projectAttachmentDtlRepository.save(projectAttachmentDtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectAttachmentDtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-attachment-dtls} : get all the projectAttachmentDtls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectAttachmentDtls in body.
     */
    @GetMapping("/project-attachment-dtls")
    public List<ProjectAttachmentDtl> getAllProjectAttachmentDtls() {
        log.debug("REST request to get all ProjectAttachmentDtls");
        return projectAttachmentDtlRepository.findAll();
    }

    /**
     * {@code GET  /project-attachment-dtls/:id} : get the "id" projectAttachmentDtl.
     *
     * @param id the id of the projectAttachmentDtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectAttachmentDtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-attachment-dtls/{id}")
    public ResponseEntity<ProjectAttachmentDtl> getProjectAttachmentDtl(@PathVariable Long id) {
        log.debug("REST request to get ProjectAttachmentDtl : {}", id);
        Optional<ProjectAttachmentDtl> projectAttachmentDtl = projectAttachmentDtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectAttachmentDtl);
    }

    /**
     * {@code DELETE  /project-attachment-dtls/:id} : delete the "id" projectAttachmentDtl.
     *
     * @param id the id of the projectAttachmentDtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-attachment-dtls/{id}")
    public ResponseEntity<Void> deleteProjectAttachmentDtl(@PathVariable Long id) {
        log.debug("REST request to delete ProjectAttachmentDtl : {}", id);
        projectAttachmentDtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
