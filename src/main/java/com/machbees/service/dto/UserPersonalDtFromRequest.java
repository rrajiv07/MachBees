package com.machbees.service.dto;

import java.util.List;

public class UserPersonalDtFromRequest {

	private long userId;
	private String name;
	private String surName;
	private String address;
	private String country;
	private String mobile;
	private String linkedIn;
	private String twitter;
	private String skypeAddress;
	private List<UserLanguageAndProficiency> Language;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		this.Language = language;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
