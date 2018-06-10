package com.scll.partner.issue.giftcard.model;

public class IssueGiftCardRequest {
	
	private String physicalGiftCardId;
	private String securityCode;
	private String partnerID;

	public String getPhysicalGiftCardId() {
		return physicalGiftCardId;
	}

	public void setPhysicalGiftCardId(String physicalGiftCardId) {
		this.physicalGiftCardId = physicalGiftCardId;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	
}
