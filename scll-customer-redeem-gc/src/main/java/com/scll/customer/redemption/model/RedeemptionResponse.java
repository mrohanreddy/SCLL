package com.scll.customer.redemption.model;

import java.util.List;

public class RedeemptionResponse {
	
	private String status;
	private String message;
	private String customerName;
	private String customerEmailId;
	
	private List<PartnerDetails> partnerDetails;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<PartnerDetails> getPartnerDetails() {
		return partnerDetails;
	}
	public void setPartnerDetails(List<PartnerDetails> partnerDetails) {
		this.partnerDetails = partnerDetails;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	@Override
	public String toString() {
		return "RedeemptionResponse [status=" + status + ", message=" + message + ", customerName=" + customerName
				+ ", customerEmailId=" + customerEmailId + ", partnerDetails=" + partnerDetails + "]";
	}
	
	

	
}
