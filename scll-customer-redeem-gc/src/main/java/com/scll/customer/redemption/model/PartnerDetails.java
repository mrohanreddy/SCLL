package com.scll.customer.redemption.model;

import java.math.BigDecimal;
import java.util.Date;

public class PartnerDetails {

	private String partnerID;
	private String partnerName;
	private String partnerMobileNumber;
	private String partnerEmailAddress;
	private String partnerAdd1;
	private String partnerAdd2;
	private String partnerZipcode;
	private String securityCode;
	private String pointsUsed;
	private Date redemptionOptedDate;
	private BigDecimal cardValue;
	
	public BigDecimal getCardValue() {
		return cardValue;
	}
	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}
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
	public String getPartnerMobileNumber() {
		return partnerMobileNumber;
	}
	public void setPartnerMobileNumber(String partnerMobileNumber) {
		this.partnerMobileNumber = partnerMobileNumber;
	}
	public String getPartnerAdd1() {
		return partnerAdd1;
	}
	public void setPartnerAdd1(String partnerAdd1) {
		this.partnerAdd1 = partnerAdd1;
	}
	public String getPartnerAdd2() {
		return partnerAdd2;
	}
	public void setPartnerAdd2(String partnerAdd2) {
		this.partnerAdd2 = partnerAdd2;
	}
	public String getPartnerZipcode() {
		return partnerZipcode;
	}
	public void setPartnerZipcode(String partnerZipcode) {
		this.partnerZipcode = partnerZipcode;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getPointsUsed() {
		return pointsUsed;
	}
	public void setPointsUsed(String pointsUsed) {
		this.pointsUsed = pointsUsed;
	}
	public Date getRedemptionOptedDate() {
		return redemptionOptedDate;
	}
	public void setRedemptionOptedDate(Date redemptionOptedDate) {
		this.redemptionOptedDate = redemptionOptedDate;
	}
	public String getPartnerEmailAddress() {
		return partnerEmailAddress;
	}
	public void setPartnerEmailAddress(String partnerEmailAddress) {
		this.partnerEmailAddress = partnerEmailAddress;
	}
	@Override
	public String toString() {
		return "PartnerDetails [partnerID=" + partnerID + ", partnerName=" + partnerName + ", partnerMobileNumber="
				+ partnerMobileNumber + ", partnerEmailAddress=" + partnerEmailAddress + ", partnerAdd1=" + partnerAdd1
				+ ", partnerAdd2=" + partnerAdd2 + ", partnerZipcode=" + partnerZipcode + ", securityCode="
				+ securityCode + ", pointsUsed=" + pointsUsed + ", redemptionOptedDate=" + redemptionOptedDate
				+ ", cardValue=" + cardValue + "]";
	}
	
	
	
	
}
