package com.machbees.service.dto;

public class UserProfileTypeFromRequest {

	private long userId;
	private long profileType;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProfileType() {
		return profileType;
	}

	public void setProfileType(long profileType) {
		this.profileType = profileType;
	}

}
