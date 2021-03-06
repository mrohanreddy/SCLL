package com.scll.partner.giftcard.issued.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerRedemption")
public class CustomerRedemption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String customerRedemptionID;
	
	private String customerID;
	
	private String physicalCardNumber;
	
	private String status;
	
	private BigDecimal cardValue;
	
	private Date CardCollectionDate;
	
	private String storeID;
	
	@JoinColumn(name = "CustomerID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	

	public String getCustomerRedemptionID() {
		return customerRedemptionID;
	}

	public void setCustomerRedemptionID(String customerRedemptionID) {
		this.customerRedemptionID = customerRedemptionID;
	}

	public String getPhysicalCardNumber() {
		return physicalCardNumber;
	}

	public void setPhysicalCardNumber(String physicalCardNumber) {
		this.physicalCardNumber = physicalCardNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public BigDecimal getCardValue() {
		return cardValue;
	}

	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}

	public Date getCardCollectionDate() {
		return CardCollectionDate;
	}

	public void setCardCollectionDate(Date cardCollectionDate) {
		CardCollectionDate = cardCollectionDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	
	
	
	
	
	

}
