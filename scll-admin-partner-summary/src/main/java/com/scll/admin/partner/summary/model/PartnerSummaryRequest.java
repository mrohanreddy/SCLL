package com.scll.admin.partner.summary.model;

public class PartnerSummaryRequest {
	
	private String storeID;
	private String partnerID;
	
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	@Override
	public String toString() {
		return "PartnerSummaryRequest [storeID=" + storeID + ", partnerID=" + partnerID + "]";
	}
	
	

}
