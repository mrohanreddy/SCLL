package com.scll.partner.giftcard.issued.model;

import java.util.Date;

public class GiftCardIssued {
	
	
	private String firstName;
	private String lastName;
	private String storeID;
	private String giftCardID;
	private Date dateIssued;
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
	public String getGiftCardID() {
		return giftCardID;
	}
	public void setGiftCardID(String giftCardID) {
		this.giftCardID = giftCardID;
	}
	public Date getDateIssued() {
		return dateIssued;
	}
	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
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
