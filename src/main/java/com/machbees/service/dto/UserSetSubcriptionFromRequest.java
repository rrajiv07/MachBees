package com.machbees.service.dto;

public class UserSetSubcriptionFromRequest {

	private long userId;
	private long subscription;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getSubscription() {
		return subscription;
	}

	public void setSubscription(long subscription) {
		this.subscription = subscription;
	}

}
