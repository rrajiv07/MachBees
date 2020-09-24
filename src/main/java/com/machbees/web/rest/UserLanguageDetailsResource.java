package com.machbees.web.rest;

import com.machbees.domain.UserLanguageDetails;
import com.machbees.repository.UserLanguageDetailsRepository;
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
 * REST controller for managing {@link com.machbees.domain.UserLanguageDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserLanguageDetailsResource {

    private final Logger log = LoggerFactory.getLogger(UserLanguageDetailsResource.class);

    private static final String ENTITY_NAME = "userLanguageDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserLanguageDetailsRepository userLanguageDetailsRepository;

    public UserLanguageDetailsResource(UserLanguageDetailsRepository userLanguageDetailsRepository) {
        this.userLanguageDetailsRepository = userLanguageDetailsRepository;
    }

    /**
     * {@code POST  /user-language-details} : Create a new userLanguageDetails.
     *
     * @param userLanguageDetails the userLanguageDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userLanguageDetails, or with status {@code 400 (Bad Request)} if the userLanguageDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-language-details")
    public ResponseEntity<UserLanguageDetails> createUserLanguageDetails(@RequestBody UserLanguageDetails userLanguageDetails) throws URISyntaxException {
        log.debug("REST request to save UserLanguageDetails : {}", userLanguageDetails);
        if (userLanguageDetails.getId() != null) {
            throw new BadRequestAlertException("A new userLanguageDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserLanguageDetails result = userLanguageDetailsRepository.save(userLanguageDetails);
        return ResponseEntity.created(new URI("/api/user-language-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-language-details} : Updates an existing userLanguageDetails.
     *
     * @param userLanguageDetails the userLanguageDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userLanguageDetails,
     * or with status {@code 400 (Bad Request)} if the userLanguageDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userLanguageDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-language-details")
    public ResponseEntity<UserLanguageDetails> updateUserLanguageDetails(@RequestBody UserLanguageDetails userLanguageDetails) throws URISyntaxException {
        log.debug("REST request to update UserLanguageDetails : {}", userLanguageDetails);
        if (userLanguageDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserLanguageDetails result = userLanguageDetailsRepository.save(userLanguageDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userLanguageDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-language-details} : get all the userLanguageDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userLanguageDetails in body.
     */
    @GetMapping("/user-language-details")
    public List<UserLanguageDetails> getAllUserLanguageDetails() {
        log.debug("REST request to get all UserLanguageDetails");
        return userLanguageDetailsRepository.findAll();
    }

    /**
     * {@code GET  /user-language-details/:id} : get the "id" userLanguageDetails.
     *
     * @param id the id of the userLanguageDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userLanguageDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-language-details/{id}")
    public ResponseEntity<UserLanguageDetails> getUserLanguageDetails(@PathVariable Long id) {
        log.debug("REST request to get UserLanguageDetails : {}", id);
        Optional<UserLanguageDetails> userLanguageDetails = userLanguageDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userLanguageDetails);
    }

    /**
     * {@code DELETE  /user-language-details/:id} : delete the "id" userLanguageDetails.
     *
     * @param id the id of the userLanguageDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-language-details/{id}")
    public ResponseEntity<Void> deleteUserLanguageDetails(@PathVariable Long id) {
        log.debug("REST request to delete UserLanguageDetails : {}", id);
        userLanguageDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
