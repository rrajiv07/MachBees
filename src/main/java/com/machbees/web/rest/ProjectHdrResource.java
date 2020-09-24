package com.machbees.web.rest;

import com.machbees.domain.ProjectHdr;
import com.machbees.repository.ProjectHdrRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProjectHdr}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectHdrResource {

    private final Logger log = LoggerFactory.getLogger(ProjectHdrResource.class);

    private static final String ENTITY_NAME = "projectHdr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectHdrRepository projectHdrRepository;

    public ProjectHdrResource(ProjectHdrRepository projectHdrRepository) {
        this.projectHdrRepository = projectHdrRepository;
    }

    /**
     * {@code POST  /project-hdrs} : Create a new projectHdr.
     *
     * @param projectHdr the projectHdr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectHdr, or with status {@code 400 (Bad Request)} if the projectHdr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-hdrs")
    public ResponseEntity<ProjectHdr> createProjectHdr(@Valid @RequestBody ProjectHdr projectHdr) throws URISyntaxException {
        log.debug("REST request to save ProjectHdr : {}", projectHdr);
        if (projectHdr.getId() != null) {
            throw new BadRequestAlertException("A new projectHdr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectHdr result = projectHdrRepository.save(projectHdr);
        return ResponseEntity.created(new URI("/api/project-hdrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-hdrs} : Updates an existing projectHdr.
     *
     * @param projectHdr the projectHdr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectHdr,
     * or with status {@code 400 (Bad Request)} if the projectHdr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectHdr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-hdrs")
    public ResponseEntity<ProjectHdr> updateProjectHdr(@Valid @RequestBody ProjectHdr projectHdr) throws URISyntaxException {
        log.debug("REST request to update ProjectHdr : {}", projectHdr);
        if (projectHdr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectHdr result = projectHdrRepository.save(projectHdr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectHdr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-hdrs} : get all the projectHdrs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectHdrs in body.
     */
    @GetMapping("/project-hdrs")
    public List<ProjectHdr> getAllProjectHdrs() {
        log.debug("REST request to get all ProjectHdrs");
        return projectHdrRepository.findAll();
    }

    /**
     * {@code GET  /project-hdrs/:id} : get the "id" projectHdr.
     *
     * @param id the id of the projectHdr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectHdr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-hdrs/{id}")
    public ResponseEntity<ProjectHdr> getProjectHdr(@PathVariable Long id) {
        log.debug("REST request to get ProjectHdr : {}", id);
        Optional<ProjectHdr> projectHdr = projectHdrRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectHdr);
    }

    /**
     * {@code DELETE  /project-hdrs/:id} : delete the "id" projectHdr.
     *
     * @param id the id of the projectHdr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-hdrs/{id}")
    public ResponseEntity<Void> deleteProjectHdr(@PathVariable Long id) {
        log.debug("REST request to delete ProjectHdr : {}", id);
        projectHdrRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
