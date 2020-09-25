package com.machbees.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.machbees.domain.CategoryMetadata;
import com.machbees.domain.ProfileMaster;
import com.machbees.service.dto.UserCompanyDtFromRequest;
import com.machbees.service.dto.UserPersonalDtFromRequest;
import com.machbees.service.dto.UserProfileCategoryFromRequest;
import com.machbees.service.dto.UserProfileTypeFromRequest;
import com.machbees.service.dto.UserSetSubcriptionFromRequest;

public interface RegisterService {
	public JSONObject setEmailPassword(String emailId, String password);

	public List<CategoryMetadata> findByCategoryName(String categoryName);

	public JSONObject updateProfileCategory(UserProfileCategoryFromRequest profileCatDetails);

	List<ProfileMaster> findByprofileType();

	public JSONObject updateProfileType(UserProfileTypeFromRequest profiletype);

	public JSONObject setPersonaldetails(UserPersonalDtFromRequest personaldetails);

	public JSONObject getPersonaldetails(long userId);

	public JSONObject setCompanydetails(UserCompanyDtFromRequest companydetails);

	public JSONObject updateSubscription(UserSetSubcriptionFromRequest setSubscription);

	public JSONObject updateConfirm(int userId);

	public JSONObject fetchProfileType(long userId);

	public JSONObject fetchProfileCategory(long userId);
	
	public JSONObject fetchSubscription(long userId);

}
