package com.scll.customer.redemption.model;

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
	private String customerRedemptionID;
	
	private String customerID;
	//private String partnerID;
	private String storeID;
	private Date redemptionOptedDate;
	private BigDecimal pointsUsed;
	private String securityCode;
	private BigDecimal cardValue;
	private BigDecimal scllContribution;
	private BigDecimal partnerContribution;
	private String status;

	/*@JoinColumn(name = "CustomerID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	private Customer customer;*/
	
	@JoinColumn(name = "PartnerID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	private Partner partner;

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

	/*public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}*/

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public Date getRedemptionOptedDate() {
		return redemptionOptedDate;
	}

	public void setRedemptionOptedDate(Date redemptionOptedDate) {
		this.redemptionOptedDate = redemptionOptedDate;
	}

	public BigDecimal getPointsUsed() {
		return pointsUsed;
	}

	public void setPointsUsed(BigDecimal pointsUsed) {
		this.pointsUsed = pointsUsed;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public BigDecimal getCardValue() {
		return cardValue;
	}

	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}

	public BigDecimal getScllContribution() {
		return scllContribution;
	}

	public void setScllContribution(BigDecimal scllContribution) {
		this.scllContribution = scllContribution;
	}

	public BigDecimal getPartnerContribution() {
		return partnerContribution;
	}

	public void setPartnerContribution(BigDecimal partnerContribution) {
		this.partnerContribution = partnerContribution;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	

	
	

}
