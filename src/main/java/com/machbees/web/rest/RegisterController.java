package com.machbees.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.machbees.domain.CategoryMetadata;
import com.machbees.domain.ProfileMaster;
import com.machbees.security.AuthoritiesConstants;
import com.machbees.service.RegisterService;
import com.machbees.service.dto.UserCompanyDtFromRequest;
import com.machbees.service.dto.UserDetailsFromRequest;
import com.machbees.service.dto.UserPersonalDtFromRequest;
import com.machbees.service.dto.UserProfileCategoryFromRequest;
import com.machbees.service.dto.UserProfileTypeFromRequest;
import com.machbees.service.dto.UserSetSubcriptionFromRequest;
import com.machbees.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class RegisterController {
	@Autowired
	RegisterService regService;

	private final Logger log = LoggerFactory.getLogger(RegisterController.class);
	private static final String ENTITY_NAME = "register";

	// setEmailPassword
	@PostMapping(value = "/common/registration/setEmailPassword")
	@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<?> setEmailPassword(@Valid @RequestBody UserDetailsFromRequest userdetails) {

		if ((userdetails.getEmailId() == null) || (userdetails.getPassword() == null)) {
			throw new BadRequestAlertException("EmailId/Pasword cannot be null", ENTITY_NAME, "null value");
		}
		JSONObject userResult = regService.setEmailPassword(userdetails.getEmailId(), userdetails.getPassword());
		return new ResponseEntity<JSONObject>(userResult, HttpStatus.CREATED);
	}

	// getProfileCategory
	@GetMapping("/common/metadata/profileCategory")
	public List<CategoryMetadata> getProfileCategory() throws JsonProcessingException {
		List<CategoryMetadata> category = regService.findByCategoryName("PROFILE_CATEGORY");
		System.out.println("category" + category);
		return category;
	}
	@GetMapping("/common/registration/profileCategory/{userId}")
	public ResponseEntity<?> fetchProfileCategory(@PathVariable long userId) {
		System.out.println("-inside-");
		JSONObject personalDt = regService.fetchProfileCategory(userId);
		return new ResponseEntity<JSONObject>(personalDt, HttpStatus.CREATED);
	}
	// setProfileCategory
	@PostMapping("/common/registration/profileCategory")
	public ResponseEntity<?> updateProfilecategory(@RequestBody UserProfileCategoryFromRequest profileCatDetails) {
		System.out.println("-inside-");
		JSONObject userResult = regService.updateProfileCategory(profileCatDetails);
		return new ResponseEntity<JSONObject>(userResult, HttpStatus.CREATED);
	}

	// getprofileType
	@GetMapping("/common/metadata/profileType")
	public List<ProfileMaster> getprofileType() {
		List<ProfileMaster> profile = regService.findByprofileType();
		System.out.println("profile" + profile);
		return profile;
	}
	@GetMapping("/common/registration/profileType/{userId}")
	public ResponseEntity<?> fetchProfileType(@PathVariable long userId) {
		System.out.println("-inside-");
		JSONObject personalDt = regService.fetchProfileType(userId);
		return new ResponseEntity<JSONObject>(personalDt, HttpStatus.CREATED);
	}
	// setprofileType
	@PostMapping("/common/registration/profileType")
	public ResponseEntity<?> setprofileType(@RequestBody UserProfileTypeFromRequest profiletype) {
		System.out.println("-inside-");
		JSONObject userResult = regService.updateProfileType(profiletype);
		return new ResponseEntity<JSONObject>(userResult, HttpStatus.CREATED);
	}

	// getUserLangugageAndProficency
	@GetMapping("/common/metadata/userProficiency")
	public List<CategoryMetadata> getUserProficency() throws JsonProcessingException {
		List<CategoryMetadata> category = regService.findByCategoryName("USER_PROFICIENCY");
		System.out.println("category" + category);
		return category;
	}

	// getUserLanguage
	@GetMapping("/common/metadata/userLanguage")
	public List<CategoryMetadata> getUserLanguage() throws JsonProcessingException {
		List<CategoryMetadata> category = regService.findByCategoryName("USER_SKILLS");
		System.out.println("category" + category);
		return category;
	}

	// setPersonaldetails
	@PostMapping("/common/registration/personalDetails")
	public ResponseEntity<?> setPersonaldetails(@RequestBody UserPersonalDtFromRequest personaldetails) {
		System.out.println("-inside-");
		JSONObject personalDt = regService.setPersonaldetails(personaldetails);
		return new ResponseEntity<JSONObject>(personalDt, HttpStatus.CREATED);
	}

	// getPersonaldetails
	@GetMapping("/common/registration/personalDetails/{userId}")
	public ResponseEntity<?> getPersonaldetails(@PathVariable long userId) {
		System.out.println("-inside-");
		JSONObject personalDt = regService.getPersonaldetails(userId);
		return new ResponseEntity<JSONObject>(personalDt, HttpStatus.CREATED);
	}

	// setCompanydetails
	@PostMapping("/common/registration/companyDetails")
	public ResponseEntity<?> setCompanydetails(@RequestBody UserCompanyDtFromRequest companydetails) {
		System.out.println("-inside-");
		JSONObject personalDt = regService.setCompanydetails(companydetails);
		return new ResponseEntity<JSONObject>(personalDt, HttpStatus.CREATED);
	}

	// getSubscription
	@GetMapping("/common/registration/subscription")
	public List<CategoryMetadata> getSubscription() throws JsonProcessingException {
		List<CategoryMetadata> category = regService.findByCategoryName("SUBSCRIPTION");
		System.out.println("category" + category);
		return category;
	}

	// setSubscription
	@PostMapping("/common/registration/subscription")
	public ResponseEntity<?> setSubscription(@RequestBody UserSetSubcriptionFromRequest setSubscription) {
		System.out.println("-inside-");
		JSONObject usersubscriptionResult = regService.updateSubscription(setSubscription);
		return new ResponseEntity<JSONObject>(usersubscriptionResult, HttpStatus.CREATED);
	}
	// setConfirm
	@PostMapping("/common/registration/confirm")
	public ResponseEntity<?> setConfirm(@RequestBody int userId) {
		System.out.println("-inside-");
		JSONObject userConfirmResult = regService.updateConfirm(userId);
		return new ResponseEntity<JSONObject>(userConfirmResult, HttpStatus.CREATED);
	}

}
