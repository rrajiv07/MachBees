package com.machbees.web.rest;

import com.machbees.domain.UserCompanyDetails;
import com.machbees.repository.UserCompanyDetailsRepository;
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
 * REST controller for managing {@link com.machbees.domain.UserCompanyDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserCompanyDetailsResource {

    private final Logger log = LoggerFactory.getLogger(UserCompanyDetailsResource.class);

    private static final String ENTITY_NAME = "userCompanyDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserCompanyDetailsRepository userCompanyDetailsRepository;

    public UserCompanyDetailsResource(UserCompanyDetailsRepository userCompanyDetailsRepository) {
        this.userCompanyDetailsRepository = userCompanyDetailsRepository;
    }

    /**
     * {@code POST  /user-company-details} : Create a new userCompanyDetails.
     *
     * @param userCompanyDetails the userCompanyDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userCompanyDetails, or with status {@code 400 (Bad Request)} if the userCompanyDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-company-details")
    public ResponseEntity<UserCompanyDetails> createUserCompanyDetails(@Valid @RequestBody UserCompanyDetails userCompanyDetails) throws URISyntaxException {
        log.debug("REST request to save UserCompanyDetails : {}", userCompanyDetails);
        if (userCompanyDetails.getId() != null) {
            throw new BadRequestAlertException("A new userCompanyDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCompanyDetails result = userCompanyDetailsRepository.save(userCompanyDetails);
        return ResponseEntity.created(new URI("/api/user-company-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-company-details} : Updates an existing userCompanyDetails.
     *
     * @param userCompanyDetails the userCompanyDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userCompanyDetails,
     * or with status {@code 400 (Bad Request)} if the userCompanyDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userCompanyDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-company-details")
    public ResponseEntity<UserCompanyDetails> updateUserCompanyDetails(@Valid @RequestBody UserCompanyDetails userCompanyDetails) throws URISyntaxException {
        log.debug("REST request to update UserCompanyDetails : {}", userCompanyDetails);
        if (userCompanyDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserCompanyDetails result = userCompanyDetailsRepository.save(userCompanyDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userCompanyDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-company-details} : get all the userCompanyDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userCompanyDetails in body.
     */
    @GetMapping("/user-company-details")
    public List<UserCompanyDetails> getAllUserCompanyDetails() {
        log.debug("REST request to get all UserCompanyDetails");
        return userCompanyDetailsRepository.findAll();
    }

    /**
     * {@code GET  /user-company-details/:id} : get the "id" userCompanyDetails.
     *
     * @param id the id of the userCompanyDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCompanyDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-company-details/{id}")
    public ResponseEntity<UserCompanyDetails> getUserCompanyDetails(@PathVariable Long id) {
        log.debug("REST request to get UserCompanyDetails : {}", id);
        Optional<UserCompanyDetails> userCompanyDetails = userCompanyDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userCompanyDetails);
    }

    /**
     * {@code DELETE  /user-company-details/:id} : delete the "id" userCompanyDetails.
     *
     * @param id the id of the userCompanyDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-company-details/{id}")
    public ResponseEntity<Void> deleteUserCompanyDetails(@PathVariable Long id) {
        log.debug("REST request to delete UserCompanyDetails : {}", id);
        userCompanyDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
