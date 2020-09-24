package com.machbees.web.rest;

import com.machbees.domain.UserPersonalDetails;
import com.machbees.repository.UserPersonalDetailsRepository;
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
 * REST controller for managing {@link com.machbees.domain.UserPersonalDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserPersonalDetailsResource {

    private final Logger log = LoggerFactory.getLogger(UserPersonalDetailsResource.class);

    private static final String ENTITY_NAME = "userPersonalDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserPersonalDetailsRepository userPersonalDetailsRepository;

    public UserPersonalDetailsResource(UserPersonalDetailsRepository userPersonalDetailsRepository) {
        this.userPersonalDetailsRepository = userPersonalDetailsRepository;
    }

    /**
     * {@code POST  /user-personal-details} : Create a new userPersonalDetails.
     *
     * @param userPersonalDetails the userPersonalDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userPersonalDetails, or with status {@code 400 (Bad Request)} if the userPersonalDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-personal-details")
    public ResponseEntity<UserPersonalDetails> createUserPersonalDetails(@Valid @RequestBody UserPersonalDetails userPersonalDetails) throws URISyntaxException {
        log.debug("REST request to save UserPersonalDetails : {}", userPersonalDetails);
        if (userPersonalDetails.getId() != null) {
            throw new BadRequestAlertException("A new userPersonalDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserPersonalDetails result = userPersonalDetailsRepository.save(userPersonalDetails);
        return ResponseEntity.created(new URI("/api/user-personal-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-personal-details} : Updates an existing userPersonalDetails.
     *
     * @param userPersonalDetails the userPersonalDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userPersonalDetails,
     * or with status {@code 400 (Bad Request)} if the userPersonalDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userPersonalDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-personal-details")
    public ResponseEntity<UserPersonalDetails> updateUserPersonalDetails(@Valid @RequestBody UserPersonalDetails userPersonalDetails) throws URISyntaxException {
        log.debug("REST request to update UserPersonalDetails : {}", userPersonalDetails);
        if (userPersonalDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserPersonalDetails result = userPersonalDetailsRepository.save(userPersonalDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userPersonalDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-personal-details} : get all the userPersonalDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPersonalDetails in body.
     */
    @GetMapping("/user-personal-details")
    public List<UserPersonalDetails> getAllUserPersonalDetails() {
        log.debug("REST request to get all UserPersonalDetails");
        return userPersonalDetailsRepository.findAll();
    }

    /**
     * {@code GET  /user-personal-details/:id} : get the "id" userPersonalDetails.
     *
     * @param id the id of the userPersonalDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userPersonalDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-personal-details/{id}")
    public ResponseEntity<UserPersonalDetails> getUserPersonalDetails(@PathVariable Long id) {
        log.debug("REST request to get UserPersonalDetails : {}", id);
        Optional<UserPersonalDetails> userPersonalDetails = userPersonalDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userPersonalDetails);
    }

    /**
     * {@code DELETE  /user-personal-details/:id} : delete the "id" userPersonalDetails.
     *
     * @param id the id of the userPersonalDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-personal-details/{id}")
    public ResponseEntity<Void> deleteUserPersonalDetails(@PathVariable Long id) {
        log.debug("REST request to delete UserPersonalDetails : {}", id);
        userPersonalDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
