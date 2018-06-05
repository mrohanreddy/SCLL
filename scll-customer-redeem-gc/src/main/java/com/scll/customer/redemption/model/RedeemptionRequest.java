package com.scll.customer.redemption.model;

import java.util.List;

public class RedeemptionRequest {
 
	private String customerID;
	private String storeID;
	private int totalPointsRedeemed;
	private List<CardDetails> cardsList;
	
	
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public List<CardDetails> getCardsList() {
		return cardsList;
	}
	public void setCardsList(List<CardDetails> cardsList) {
		this.cardsList = cardsList;
	}
	public int getTotalPointsRedeemed() {
		return totalPointsRedeemed;
	}
	public void setTotalPointsRedeemed(int totalPointsRedeemed) {
		this.totalPointsRedeemed = totalPointsRedeemed;
	}
	
	
}
