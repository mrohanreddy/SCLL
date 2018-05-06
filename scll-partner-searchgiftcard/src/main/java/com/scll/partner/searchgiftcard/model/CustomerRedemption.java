package com.scll.partner.searchgiftcard.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CustomerRedemption")
public class CustomerRedemption implements java.io.Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String CustomerRedemptionID;
	
	@Column (name="partnerID1")
	private String partnerID;
	
	@Column (name="Status")
	private String customerRedemptionStatus;
	
	//private String customerID;	
		
	private Date redemptionOptedDate;
	private BigDecimal pointsUsed;	
	private String securityCode;		
	private Date cardCollectionDate;
	private String physicalCardNumber;
	private BigDecimal cardValue;
	@Transient
	private String status;
	@Transient
	private String message;
	
	@JoinColumn(name = "customerID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	
	
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerRedemptionID() {
		return CustomerRedemptionID;
	}
	public void setCustomerRedemptionID(String customerRedemptionID) {
		CustomerRedemptionID = customerRedemptionID;
	}
	
	/*public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}*/
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
	public Date getCardCollectionDate() {
		return cardCollectionDate;
	}
	public void setCardCollectionDate(Date cardCollectionDate) {
		this.cardCollectionDate = cardCollectionDate;
	}
	public String getPhysicalCardNumber() {
		return physicalCardNumber;
	}
	public void setPhysicalCardNumber(String physicalCardNumber) {
		this.physicalCardNumber = physicalCardNumber;
	}
	public BigDecimal getCardValue() {
		return cardValue;
	}
	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCustomerRedemptionStatus() {
		return customerRedemptionStatus;
	}
	public void setCustomerRedemptionStatus(String customerRedemptionStatus) {
		this.customerRedemptionStatus = customerRedemptionStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
