package com.scll.admin.partners.archive.model;

import java.math.BigDecimal;

public class PartnerDetails {
	
	private String partnerID;
	private String partnerName;
	private String partnerAddress;
	private String phone;
	private int giftCardsIssued; 
	private int giftCardsOpted;
	private BigDecimal outStandingPayment;
	
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGiftCardsIssued() {
		return giftCardsIssued;
	}
	public void setGiftCardsIssued(int giftCardsIssued) {
		this.giftCardsIssued = giftCardsIssued;
	}
	public int getGiftCardsOpted() {
		return giftCardsOpted;
	}
	public void setGiftCardsOpted(int giftCardsOpted) {
		this.giftCardsOpted = giftCardsOpted;
	}
	public BigDecimal getOutStandingPayment() {
		return outStandingPayment;
	}
	public void setOutStandingPayment(BigDecimal outStandingPayment) {
		this.outStandingPayment = outStandingPayment;
	}
	
}
