package com.scll.admin.partner.registration.model;

import java.math.BigDecimal;
import java.util.Date;

public class PartnerRegistrationRequest {
	private String partnerID;
	private String partnerName;
	private String partnerAddress;
	private String partnerPhoneNumber;
	private String partnerEmailAddress;
	private BigDecimal scllPercentage;
	private BigDecimal partnerPercentage;
	private BigDecimal minimumPoints;
	private Date collaborationDate;
	private String storeID;
	private String frontImage;
	private String backImage;
	
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerAddress() {
		return partnerAddress;
	}
	public void setPartnerAddress(String partnerAddress) {
		this.partnerAddress = partnerAddress;
	}
	public String getPartnerPhoneNumber() {
		return partnerPhoneNumber;
	}
	public void setPartnerPhoneNumber(String partnerPhoneNumber) {
		this.partnerPhoneNumber = partnerPhoneNumber;
	}
	public BigDecimal getScllPercentage() {
		return scllPercentage;
	}
	public void setScllPercentage(BigDecimal scllPercentage) {
		this.scllPercentage = scllPercentage;
	}
	public BigDecimal getPartnerPercentage() {
		return partnerPercentage;
	}
	public void setPartnerPercentage(BigDecimal partnerPercentage) {
		this.partnerPercentage = partnerPercentage;
	}
	public BigDecimal getMinimumPoints() {
		return minimumPoints;
	}
	public void setMinimumPoints(BigDecimal minimumPoints) {
		this.minimumPoints = minimumPoints;
	}
	public Date getCollaborationDate() {
		return collaborationDate;
	}
	public void setCollaborationDate(Date collaborationDate) {
		this.collaborationDate = collaborationDate;
	}
	public String getPartnerEmailAddress() {
		return partnerEmailAddress;
	}
	public void setPartnerEmailAddress(String partnerEmailAddress) {
		this.partnerEmailAddress = partnerEmailAddress;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getFrontImage() {
		return frontImage;
	}
	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}
	public String getBackImage() {
		return backImage;
	}
	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}
	
}
