package com.machbees.web.rest;

import com.machbees.domain.SkillCategoryMaster;
import com.machbees.repository.SkillCategoryMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.SkillCategoryMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SkillCategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(SkillCategoryMasterResource.class);

    private static final String ENTITY_NAME = "skillCategoryMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SkillCategoryMasterRepository skillCategoryMasterRepository;

    public SkillCategoryMasterResource(SkillCategoryMasterRepository skillCategoryMasterRepository) {
        this.skillCategoryMasterRepository = skillCategoryMasterRepository;
    }

    /**
     * {@code POST  /skill-category-masters} : Create a new skillCategoryMaster.
     *
     * @param skillCategoryMaster the skillCategoryMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new skillCategoryMaster, or with status {@code 400 (Bad Request)} if the skillCategoryMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/skill-category-masters")
    public ResponseEntity<SkillCategoryMaster> createSkillCategoryMaster(@Valid @RequestBody SkillCategoryMaster skillCategoryMaster) throws URISyntaxException {
        log.debug("REST request to save SkillCategoryMaster : {}", skillCategoryMaster);
        if (skillCategoryMaster.getId() != null) {
            throw new BadRequestAlertException("A new skillCategoryMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillCategoryMaster result = skillCategoryMasterRepository.save(skillCategoryMaster);
        return ResponseEntity.created(new URI("/api/skill-category-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /skill-category-masters} : Updates an existing skillCategoryMaster.
     *
     * @param skillCategoryMaster the skillCategoryMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillCategoryMaster,
     * or with status {@code 400 (Bad Request)} if the skillCategoryMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the skillCategoryMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/skill-category-masters")
    public ResponseEntity<SkillCategoryMaster> updateSkillCategoryMaster(@Valid @RequestBody SkillCategoryMaster skillCategoryMaster) throws URISyntaxException {
        log.debug("REST request to update SkillCategoryMaster : {}", skillCategoryMaster);
        if (skillCategoryMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SkillCategoryMaster result = skillCategoryMasterRepository.save(skillCategoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, skillCategoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /skill-category-masters} : get all the skillCategoryMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skillCategoryMasters in body.
     */
    @GetMapping("/skill-category-masters")
    public List<SkillCategoryMaster> getAllSkillCategoryMasters() {
        log.debug("REST request to get all SkillCategoryMasters");
        return skillCategoryMasterRepository.findAll();
    }

    /**
     * {@code GET  /skill-category-masters/:id} : get the "id" skillCategoryMaster.
     *
     * @param id the id of the skillCategoryMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the skillCategoryMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/skill-category-masters/{id}")
    public ResponseEntity<SkillCategoryMaster> getSkillCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get SkillCategoryMaster : {}", id);
        Optional<SkillCategoryMaster> skillCategoryMaster = skillCategoryMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(skillCategoryMaster);
    }

    /**
     * {@code DELETE  /skill-category-masters/:id} : delete the "id" skillCategoryMaster.
     *
     * @param id the id of the skillCategoryMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/skill-category-masters/{id}")
    public ResponseEntity<Void> deleteSkillCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete SkillCategoryMaster : {}", id);
        skillCategoryMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
