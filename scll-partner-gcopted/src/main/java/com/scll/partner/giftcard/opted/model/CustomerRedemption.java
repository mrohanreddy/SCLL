package com.scll.partner.giftcard.opted.model;

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

import com.scll.partner.giftcard.opted.model.Customer;

@Entity
@Table(name = "CustomerRedemption")
public class CustomerRedemption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String customerRedemptionID;
	
	private String customerID;
	
	//private String securityCode;
	
	private String status;
	
	private BigDecimal cardValue;
	
	private Date redemptionOptedDate;
	
	
	@JoinColumn(name = "CustomerID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	private String storeID;

	public String getCustomerRedemptionID() {
		return customerRedemptionID;
	}

	public void setCustomerRedemptionID(String customerRedemptionID) {
		this.customerRedemptionID = customerRedemptionID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/*public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}*/

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getCardValue() {
		return cardValue;
	}

	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}

	public Date getRedemptionOptedDate() {
		return redemptionOptedDate;
	}

	public void setRedemptionOptedDate(Date redemptionOptedDate) {
		this.redemptionOptedDate = redemptionOptedDate;
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
