package com.scll.admin.partner.summary.model;

public class PartnerSummaryResponse {
	
	private String partnerID;
	private String partnerName;
	private String partnerMobileNumber;
	private String partnerAdd1;
	private String partnerAdd2;
	private String partnerZipCode;
	private String partnerEmailAddress;
	private int gcIssued;
	private int gcOpted;
	private int outstandingAmount;
	private String partnerStatus;
	
	
	
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
	public int getGcIssued() {
		return gcIssued;
	}
	public void setGcIssued(int gcIssued) {
		this.gcIssued = gcIssued;
	}
	public int getGcOpted() {
		return gcOpted;
	}
	public void setGcOpted(int gcOpted) {
		this.gcOpted = gcOpted;
	}
	public int getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public String getPartnerStatus() {
		return partnerStatus;
	}
	public void setPartnerStatus(String partnerStatus) {
		this.partnerStatus = partnerStatus;
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
	public String getPartnerZipCode() {
		return partnerZipCode;
	}
	public void setPartnerZipCode(String partnerZipCode) {
		this.partnerZipCode = partnerZipCode;
	}
	public String getPartnerEmailAddress() {
		return partnerEmailAddress;
	}
	public void setPartnerEmailAddress(String partnerEmailAddress) {
		this.partnerEmailAddress = partnerEmailAddress;
	}
	@Override
	public String toString() {
		return "PartnerSummaryResponse [partnerID=" + partnerID + ", partnerName=" + partnerName
				+ ", partnerMobileNumber=" + partnerMobileNumber + ", partnerAdd1=" + partnerAdd1 + ", partnerAdd2="
				+ partnerAdd2 + ", partnerZipCode=" + partnerZipCode + ", partnerEmailAddress=" + partnerEmailAddress
				+ ", gcIssued=" + gcIssued + ", gcOpted=" + gcOpted + ", outstandingAmount=" + outstandingAmount
				+ ", partnerStatus=" + partnerStatus + "]";
	}
	
	
	
	
	

}
