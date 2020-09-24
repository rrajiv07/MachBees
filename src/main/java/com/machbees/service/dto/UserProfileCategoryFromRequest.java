package com.machbees.service.dto;

public class UserProfileCategoryFromRequest {

	private long userId;
	private long profileCategory;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProfileCategory() {
		return profileCategory;
	}

	public void setProfileCategory(long profileCategory) {
		this.profileCategory = profileCategory;
	}

}
