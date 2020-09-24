package com.machbees.web.rest;

import com.machbees.domain.UserMaster;
import com.machbees.repository.UserMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.UserMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserMasterResource {

    private final Logger log = LoggerFactory.getLogger(UserMasterResource.class);

    private static final String ENTITY_NAME = "userMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserMasterRepository userMasterRepository;

    public UserMasterResource(UserMasterRepository userMasterRepository) {
        this.userMasterRepository = userMasterRepository;
    }

    /**
     * {@code POST  /user-masters} : Create a new userMaster.
     *
     * @param userMaster the userMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userMaster, or with status {@code 400 (Bad Request)} if the userMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-masters")
    public ResponseEntity<UserMaster> createUserMaster(@Valid @RequestBody UserMaster userMaster) throws URISyntaxException {
        log.debug("REST request to save UserMaster : {}", userMaster);
        if (userMaster.getId() != null) {
            throw new BadRequestAlertException("A new userMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserMaster result = userMasterRepository.save(userMaster);
        return ResponseEntity.created(new URI("/api/user-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-masters} : Updates an existing userMaster.
     *
     * @param userMaster the userMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userMaster,
     * or with status {@code 400 (Bad Request)} if the userMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-masters")
    public ResponseEntity<UserMaster> updateUserMaster(@Valid @RequestBody UserMaster userMaster) throws URISyntaxException {
        log.debug("REST request to update UserMaster : {}", userMaster);
        if (userMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserMaster result = userMasterRepository.save(userMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-masters} : get all the userMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userMasters in body.
     */
    @GetMapping("/user-masters")
    public List<UserMaster> getAllUserMasters() {
        log.debug("REST request to get all UserMasters");
        return userMasterRepository.findAll();
    }

    /**
     * {@code GET  /user-masters/:id} : get the "id" userMaster.
     *
     * @param id the id of the userMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-masters/{id}")
    public ResponseEntity<UserMaster> getUserMaster(@PathVariable Long id) {
        log.debug("REST request to get UserMaster : {}", id);
        Optional<UserMaster> userMaster = userMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userMaster);
    }

    /**
     * {@code DELETE  /user-masters/:id} : delete the "id" userMaster.
     *
     * @param id the id of the userMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-masters/{id}")
    public ResponseEntity<Void> deleteUserMaster(@PathVariable Long id) {
        log.debug("REST request to delete UserMaster : {}", id);
        userMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
