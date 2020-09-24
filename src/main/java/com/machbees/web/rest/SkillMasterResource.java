package com.machbees.web.rest;

import com.machbees.domain.SkillMaster;
import com.machbees.repository.SkillMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.SkillMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SkillMasterResource {

    private final Logger log = LoggerFactory.getLogger(SkillMasterResource.class);

    private static final String ENTITY_NAME = "skillMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SkillMasterRepository skillMasterRepository;

    public SkillMasterResource(SkillMasterRepository skillMasterRepository) {
        this.skillMasterRepository = skillMasterRepository;
    }

    /**
     * {@code POST  /skill-masters} : Create a new skillMaster.
     *
     * @param skillMaster the skillMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new skillMaster, or with status {@code 400 (Bad Request)} if the skillMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/skill-masters")
    public ResponseEntity<SkillMaster> createSkillMaster(@Valid @RequestBody SkillMaster skillMaster) throws URISyntaxException {
        log.debug("REST request to save SkillMaster : {}", skillMaster);
        if (skillMaster.getId() != null) {
            throw new BadRequestAlertException("A new skillMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillMaster result = skillMasterRepository.save(skillMaster);
        return ResponseEntity.created(new URI("/api/skill-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /skill-masters} : Updates an existing skillMaster.
     *
     * @param skillMaster the skillMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillMaster,
     * or with status {@code 400 (Bad Request)} if the skillMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the skillMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/skill-masters")
    public ResponseEntity<SkillMaster> updateSkillMaster(@Valid @RequestBody SkillMaster skillMaster) throws URISyntaxException {
        log.debug("REST request to update SkillMaster : {}", skillMaster);
        if (skillMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SkillMaster result = skillMasterRepository.save(skillMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, skillMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /skill-masters} : get all the skillMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skillMasters in body.
     */
    @GetMapping("/skill-masters")
    public List<SkillMaster> getAllSkillMasters() {
        log.debug("REST request to get all SkillMasters");
        return skillMasterRepository.findAll();
    }

    /**
     * {@code GET  /skill-masters/:id} : get the "id" skillMaster.
     *
     * @param id the id of the skillMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the skillMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/skill-masters/{id}")
    public ResponseEntity<SkillMaster> getSkillMaster(@PathVariable Long id) {
        log.debug("REST request to get SkillMaster : {}", id);
        Optional<SkillMaster> skillMaster = skillMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(skillMaster);
    }

    /**
     * {@code DELETE  /skill-masters/:id} : delete the "id" skillMaster.
     *
     * @param id the id of the skillMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/skill-masters/{id}")
    public ResponseEntity<Void> deleteSkillMaster(@PathVariable Long id) {
        log.debug("REST request to delete SkillMaster : {}", id);
        skillMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
