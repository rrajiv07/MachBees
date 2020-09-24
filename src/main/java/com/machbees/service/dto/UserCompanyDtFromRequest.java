package com.machbees.service.dto;

import java.util.List;

public class UserCompanyDtFromRequest {

	private long userId;
	private String companyName;
	private String vatId;
	private String website;
	private String description;
	private String address;
	// private String country;
	private String mobile;
	private String linkedIn;
	private String twitter;
	private String skypeAddress;
	private List<UserLanguageAndProficiency> Language;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVatId() {
		return vatId;
	}

	public void setVatId(String vatId) {
		this.vatId = vatId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getSkypeAddress() {
		return skypeAddress;
	}

	public void setSkypeAddress(String skypeAddress) {
		this.skypeAddress = skypeAddress;
	}

	public List<UserLanguageAndProficiency> getLanguage() {
		return Language;
	}

	public void setLanguage(List<UserLanguageAndProficiency> language) {
		Language = language;
	}

}
