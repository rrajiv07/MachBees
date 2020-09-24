package com.machbees.web.rest;

import com.machbees.domain.ProfileMaster;
import com.machbees.repository.ProfileMasterRepository;
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
 * REST controller for managing {@link com.machbees.domain.ProfileMaster}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProfileMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProfileMasterResource.class);

    private static final String ENTITY_NAME = "profileMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfileMasterRepository profileMasterRepository;

    public ProfileMasterResource(ProfileMasterRepository profileMasterRepository) {
        this.profileMasterRepository = profileMasterRepository;
    }

    /**
     * {@code POST  /profile-masters} : Create a new profileMaster.
     *
     * @param profileMaster the profileMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profileMaster, or with status {@code 400 (Bad Request)} if the profileMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profile-masters")
    public ResponseEntity<ProfileMaster> createProfileMaster(@Valid @RequestBody ProfileMaster profileMaster) throws URISyntaxException {
        log.debug("REST request to save ProfileMaster : {}", profileMaster);
        if (profileMaster.getId() != null) {
            throw new BadRequestAlertException("A new profileMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfileMaster result = profileMasterRepository.save(profileMaster);
        return ResponseEntity.created(new URI("/api/profile-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profile-masters} : Updates an existing profileMaster.
     *
     * @param profileMaster the profileMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profileMaster,
     * or with status {@code 400 (Bad Request)} if the profileMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profileMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profile-masters")
    public ResponseEntity<ProfileMaster> updateProfileMaster(@Valid @RequestBody ProfileMaster profileMaster) throws URISyntaxException {
        log.debug("REST request to update ProfileMaster : {}", profileMaster);
        if (profileMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfileMaster result = profileMasterRepository.save(profileMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, profileMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /profile-masters} : get all the profileMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profileMasters in body.
     */
    @GetMapping("/profile-masters")
    public List<ProfileMaster> getAllProfileMasters() {
        log.debug("REST request to get all ProfileMasters");
        return profileMasterRepository.findAll();
    }

    /**
     * {@code GET  /profile-masters/:id} : get the "id" profileMaster.
     *
     * @param id the id of the profileMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profileMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profile-masters/{id}")
    public ResponseEntity<ProfileMaster> getProfileMaster(@PathVariable Long id) {
        log.debug("REST request to get ProfileMaster : {}", id);
        Optional<ProfileMaster> profileMaster = profileMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(profileMaster);
    }

    /**
     * {@code DELETE  /profile-masters/:id} : delete the "id" profileMaster.
     *
     * @param id the id of the profileMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profile-masters/{id}")
    public ResponseEntity<Void> deleteProfileMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProfileMaster : {}", id);
        profileMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
