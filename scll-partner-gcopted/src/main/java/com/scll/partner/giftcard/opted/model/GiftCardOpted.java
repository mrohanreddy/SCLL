package com.scll.partner.giftcard.opted.model;

import java.util.Date;

public class GiftCardOpted {
	
	
	private String firstName;
	private String lastName;
	private String storeID;
	private Date dateOpted;
	private String cardValue;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDateOpted() {
		return dateOpted;
	}
	public void setDateOpted(Date dateOpted) {
		this.dateOpted = dateOpted;
	}
	public String getCardValue() {
		return cardValue;
	}
	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	
	

}
