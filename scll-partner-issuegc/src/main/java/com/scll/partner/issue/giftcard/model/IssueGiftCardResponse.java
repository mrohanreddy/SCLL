package com.scll.partner.issue.giftcard.model;

public class IssueGiftCardResponse {
	
	private String message;
	private String physicalGiftCardId;
	private int issuedCardCount;
	private int optedCardCount;
	private String pendingAmountFromSCLL;
	private String status;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhysicalGiftCardId() {
		return physicalGiftCardId;
	}
	public void setPhysicalGiftCardId(String physicalGiftCardId) {
		this.physicalGiftCardId = physicalGiftCardId;
	}
	public int getIssuedCardCount() {
		return issuedCardCount;
	}
	public void setIssuedCardCount(int issuedCardCount) {
		this.issuedCardCount = issuedCardCount;
	}
	public int getOptedCardCount() {
		return optedCardCount;
	}
	public void setOptedCardCount(int optedCardCount) {
		this.optedCardCount = optedCardCount;
	}
	public String getPendingAmountFromSCLL() {
		return pendingAmountFromSCLL;
	}
	public void setPendingAmountFromSCLL(String pendingAmountFromSCLL) {
		this.pendingAmountFromSCLL = pendingAmountFromSCLL;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
