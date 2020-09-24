package com.machbees.serviceImpl;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machbees.domain.CategoryMetadata;
import com.machbees.domain.ProfileMaster;
import com.machbees.domain.UserCompanyDetails;
import com.machbees.domain.UserLanguageDetails;
import com.machbees.domain.UserMaster;
import com.machbees.domain.UserPersonalDetails;
import com.machbees.repository.CategoryMetadataRepository;
import com.machbees.repository.ProfileMasterRepository;
import com.machbees.repository.UserCompanyDetailsRepository;
import com.machbees.repository.UserLanguageDetailsRepository;
import com.machbees.repository.UserMasterRepository;
import com.machbees.repository.UserPersonalDetailsRepository;
import com.machbees.service.RegisterService;
import com.machbees.service.dto.UserCompanyDtFromRequest;
import com.machbees.service.dto.UserLanguageAndProficiency;
import com.machbees.service.dto.UserPersonalDtFromRequest;
import com.machbees.service.dto.UserProfileCategoryFromRequest;
import com.machbees.service.dto.UserProfileTypeFromRequest;
import com.machbees.service.dto.UserSetSubcriptionFromRequest;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private UserMasterRepository userRepo;
	@Autowired
	private CategoryMetadataRepository categoryRepo;
	@Autowired
	private ProfileMasterRepository profileRepo;
	@Autowired
	private UserPersonalDetailsRepository personalDtRepo;
	@Autowired
	private UserCompanyDetailsRepository companyDtRepo;
	@Autowired
	private UserLanguageDetailsRepository langAndProfRepo;

	JSONObject regResult = new JSONObject();

	public JSONObject setEmailPassword(String emailId, String password) {
		regResult.clear();
		// check status is in completed
		if (checkUserIsCompleted(emailId)) {
			regResult.put("message", "Your Registration is under verification");
			regResult.put("responseStatus", "failure");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
		} else {
			UserMaster userMaster = userRepo.findByEmailIdAndStatus(emailId,
					categoryRepo.getByCategoryCodeAndCategoryName("INPROGRESS", "USER_STATUS"));
			if (userMaster != null) {
				regResult.put("message", "Email Id already Registered.");
				regResult.put("responseStatus", "failure");
				regResult.put("responseId", HttpStatus.BAD_REQUEST);
			} else {
				UserMaster userMasterResult = new UserMaster();
				userMasterResult.setEmailId(emailId);
				userMasterResult.setPassword(password);

				// update status
				CategoryMetadata profileCategory = categoryRepo.getByCategoryCodeAndCategoryName("INPROGRESS",
						"USER_STATUS");
				userMasterResult.setStatus(profileCategory);

				userRepo.save(userMasterResult);
				regResult.put("userId", userMasterResult.getId());
				regResult.put("message", "User details Inserted");
				regResult.put("responseId", HttpStatus.CREATED);
				regResult.put("responseStatus", "success");
			}
		}
		return regResult;
	}

	public List<CategoryMetadata> findByCategoryName(String categoryName) {
		List<CategoryMetadata> categoryMetadata = categoryRepo.findByCategoryName(categoryName);
		return categoryMetadata;
	}

	public JSONObject updateProfileCategory(UserProfileCategoryFromRequest profileCatDetails) {
		regResult.clear();
		if (checkUserIsCompletedByUserId(profileCatDetails.getUserId())) {
			regResult.put("message", "Your Registration is under verification");
			regResult.put("responseStatus", "failure");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
		} else {
			CategoryMetadata profileCategory = categoryRepo.findById(profileCatDetails.getProfileCategory()).get();
			if (profileCategory == null) {
				regResult.put("message", "please select valid profile category");
				regResult.put("responseStatus", "failure");
			} else {
				UserMaster userMaster = userRepo.findById(profileCatDetails.getUserId()).get();
				userMaster.setProfileCategory(profileCategory);
				userRepo.save(userMaster);
				regResult.put("userId", userMaster.getId());
				regResult.put("message", "User details Inserted");
				regResult.put("responseId", HttpStatus.CREATED);
				regResult.put("responseStatus", "success");
			}
		}
		return regResult;
	}

	@Override
	public List<ProfileMaster> findByprofileType() {
		return profileRepo.findAll();
	}

	@Override
	public JSONObject updateProfileType(UserProfileTypeFromRequest profileTypeDetails) {
		regResult.clear();
		if (checkUserIsCompletedByUserId(profileTypeDetails.getUserId())) {
			regResult.put("message", "Your Registration is under verification");
			regResult.put("responseStatus", "failure");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
		} else {
			try {
				ProfileMaster profileCategory = profileRepo.findById(profileTypeDetails.getProfileType()).get();
				if (profileCategory == null) {
					regResult.put("message", "please select valid profile");
					regResult.put("responseStatus", "failure");
					regResult.put("responseId", HttpStatus.BAD_REQUEST);
				} else {
					UserMaster userMaster = userRepo.findById(profileTypeDetails.getUserId()).get();
					userMaster.setProfile(profileCategory);
					userRepo.save(userMaster);
					regResult.put("userId", userMaster.getId());
					regResult.put("message", "User details Updated");
					regResult.put("responseId", HttpStatus.CREATED);
					regResult.put("responseStatus", "success");
				}
			} catch (Exception e) {
				regResult.put("message", "please select valid profile category");
				regResult.put("responseId", HttpStatus.BAD_REQUEST);
				regResult.put("responseStatus", "failure");
			}
		}
		return regResult;
	}

	@Override
	public JSONObject setPersonaldetails(UserPersonalDtFromRequest personaldtReq) {
		regResult.clear();
		if (checkUserIsCompletedByUserId(personaldtReq.getUserId())) {
			regResult.put("message", "Your Registration is under verification");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
			regResult.put("responseStatus", "failure");
		} else if ((personaldtReq.getLanguage() == null)) {
			regResult.put("message", "Please enter language details");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
			regResult.put("responseStatus", "failure");
		} else {

			// Save personaldetails
			UserPersonalDetails personaldetails = new UserPersonalDetails();
			UserMaster userMaster = userRepo.findById(personaldtReq.getUserId()).get();
			personaldetails.setUser(userMaster);
			personaldetails.setName(personaldtReq.getName());
			personaldetails.setSurname(personaldtReq.getSurName());
			personaldetails.setAddress(personaldtReq.getAddress());
			personaldetails.setCountry(personaldtReq.getCountry());
			personaldetails.setMobile(personaldtReq.getMobile());
			personaldetails.setLinkedin(personaldtReq.getLinkedIn());
			personaldetails.setSkype(personaldtReq.getSkypeAddress());
			personaldetails.setTwitter(personaldtReq.getTwitter());

			// save langAndProficiency
			for (UserLanguageAndProficiency langAndProfRequest : personaldtReq.getLanguage()) {
				UserLanguageDetails llp = new UserLanguageDetails();
				llp.setUser(userMaster);
				if ((categoryRepo.findById(langAndProfRequest.getLanguage()) == null)
						|| (categoryRepo.findById(langAndProfRequest.getProficiency()) == null)) {
					regResult.put("message", "please select valid Language/Proficiency category");
					regResult.put("responseId", HttpStatus.BAD_REQUEST);
					regResult.put("responseStatus", "failure");
				} else {
					llp.setLanguage(categoryRepo.findById(langAndProfRequest.getLanguage()).get());
					llp.setProficiency(categoryRepo.findById(langAndProfRequest.getProficiency()).get());
					personalDtRepo.save(personaldetails);
					langAndProfRepo.save(llp);
					regResult.put("message", "Personal details Inserted");
					regResult.put("responseStatus", "success");
					regResult.put("userId", userMaster.getId());
				}
			}
		}
		return regResult;
	}

	@Override
	public JSONObject getPersonaldetails(long userId) {
		regResult.clear();
		UserMaster userMasterResult = userRepo.findById(userId).get();
		regResult.put("PersonalDetails", userMasterResult);
		regResult.put("message", "Personal details Retrieved");
		regResult.put("responseId", HttpStatus.CREATED);
		regResult.put("responseStatus", "success");
		return regResult;
	}

	@Override
	public JSONObject setCompanydetails(UserCompanyDtFromRequest companydtReq) {
		regResult.clear();
		if (checkUserIsCompletedByUserId(companydtReq.getUserId())) {
			regResult.put("message", "Your Registration is under verification");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
			regResult.put("responseStatus", "failure");
		} else if (companydtReq.getLanguage() == null) {
			regResult.put("message", "Please enter language details");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
			regResult.put("responseStatus", "failure");
		} else {
			// Save companydetails
			UserCompanyDetails companydetails = new UserCompanyDetails();
			UserMaster userMaster = userRepo.findById(companydtReq.getUserId()).get();
			companydetails.setUser(userMaster);
			companydetails.setName(companydtReq.getCompanyName());
			companydetails.setVat(companydtReq.getVatId());
			companydetails.setWebsite(companydtReq.getWebsite());
			companydetails.setDescription(companydtReq.getDescription());
			companydetails.setAddress(companydtReq.getAddress());
			// companydetails.set(companydtReq.getLinkedIn());
			companydetails.setMobile(companydtReq.getMobile());
			companydetails.setLinkedin(companydtReq.getLinkedIn());
			companydetails.setTwitter(companydtReq.getTwitter());
			companydetails.setSkype(companydtReq.getSkypeAddress());
			companyDtRepo.save(companydetails);

			// save langAndProficiency
			for (UserLanguageAndProficiency langAndProfRequest : companydtReq.getLanguage()) {
				UserLanguageDetails llp = new UserLanguageDetails();
				llp.setUser(userMaster);
				if ((categoryRepo.findById(langAndProfRequest.getLanguage()) == null)
						|| (categoryRepo.findById(langAndProfRequest.getProficiency()) == null)) {
					regResult.put("message", "please select valid Language/Proficiency category");
					regResult.put("responseId", HttpStatus.BAD_REQUEST);
					regResult.put("responseStatus", "failure");
				} else {
					llp.setLanguage(categoryRepo.findById(langAndProfRequest.getLanguage()).get());
					llp.setProficiency(categoryRepo.findById(langAndProfRequest.getProficiency()).get());
					companyDtRepo.save(companydetails);
					langAndProfRepo.save(llp);
					regResult.put("message", "Company details Inserted");
					regResult.put("userId", userMaster.getId());
					regResult.put("responseStatus", "success");
				}
			}
		}
		return regResult;
	}

	@Override
	public JSONObject updateSubscription(UserSetSubcriptionFromRequest setSubscription) {
		regResult.clear();
		if (checkUserIsCompletedByUserId(setSubscription.getUserId())) {
			regResult.put("message", "Your Registration is under verification");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
			regResult.put("responseStatus", "failure");
		} else {
			UserMaster userMaster = userRepo.findByIdAndStatus(setSubscription.getUserId(),
					categoryRepo.getByCategoryCodeAndCategoryName("INPROGRESS", "USER_STATUS"));
			if (userMaster != null) {
				regResult.put("message", "Email Id already Registered.");
				regResult.put("responseId", HttpStatus.BAD_REQUEST);
				regResult.put("responseStatus", "failure");
			} else {
				UserMaster user = userRepo.findById(setSubscription.getUserId()).get();
				CategoryMetadata subscriptionCategory = categoryRepo.findById(setSubscription.getSubscription()).get();
				if (subscriptionCategory == null) {
					regResult.put("message", "please select valid Subscription");
					regResult.put("responseStatus", "failure");
					regResult.put("responseId", HttpStatus.BAD_REQUEST);
				} else {
					user.setPaymentSubscription(subscriptionCategory);
					userRepo.save(user);
					regResult.put("responseStatus", "success");
					regResult.put("message", "subscription details updated");
					regResult.put("userId", user.getId());
				}
			}
		}
		return regResult;
	}

	@Override
	public JSONObject updateConfirm(int userId) {
		regResult.clear();
		if (checkUserIsCompletedByUserId(userId)) {
			regResult.put("message", "Your Registration is under verification");
			regResult.put("responseId", HttpStatus.BAD_REQUEST);
			regResult.put("responseStatus", "failure");
		} else {
			UserMaster userMaster = userRepo.findByIdAndStatus(userId,
					categoryRepo.getByCategoryCodeAndCategoryName("INPROGRESS", "USER_STATUS"));
			if (userMaster != null) {
				regResult.put("message", "Email Id already Registered.");
				regResult.put("responseId", HttpStatus.BAD_REQUEST);
				regResult.put("responseStatus", "failure");
			} else {
				UserMaster user = userRepo.findById(userId);
				CategoryMetadata confirmCategory = categoryRepo.getByCategoryName("COMPLETED");
				user.setPaymentSubscription(confirmCategory);
				userRepo.save(user);
				regResult.put("message", "subscription details Inserted");
				regResult.put("userId", user.getId());
				regResult.put("responseStatus", "success");
			}
		}
		return regResult;
	}

	private Boolean checkUserIsCompleted(String emailId) {
		Boolean checkUserIssComplete = false;
		UserMaster userMaster = userRepo.findByEmailIdAndStatus(emailId,
				categoryRepo.getByCategoryCodeAndCategoryName("COMPLETED", "USER_STATUS"));
		if (userMaster != null)
			checkUserIssComplete = true;
		return checkUserIssComplete;
	}

	private boolean checkUserIsCompletedByUserId(long userId) {
		Boolean checkUserIssComplete = false;
		UserMaster userMaster = userRepo.findByIdAndStatus(userId,
				categoryRepo.getByCategoryCodeAndCategoryName("COMPLETED", "USER_STATUS"));
		if (userMaster != null)
			checkUserIssComplete = true;
		return checkUserIssComplete;
	}

}