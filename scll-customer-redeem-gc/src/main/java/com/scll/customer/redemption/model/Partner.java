package com.scll.customer.redemption.model;

import java.io.Serializable;
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
@Table(name = "Partner")
public class Partner implements Serializable{
	
	@Id
	private String partnerID;
	private String storeID;
	private Date partnershipSetupDate;
	private String partnerName;
	private String partnerAdd1;
	private String partnerAdd2;
	private String partnerZipCode;
	private String partnerPhoneNumber;
	private String partnerEmailAddress;
	private BigDecimal scllPercentage;
	private BigDecimal partnerPercentage;
	private BigDecimal minimumPoints;
	
	

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}


	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public Date getPartnershipSetupDate() {
		return partnershipSetupDate;
	}

	public void setPartnershipSetupDate(Date partnershipSetupDate) {
		this.partnershipSetupDate = partnershipSetupDate;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerAdd1() {
		return partnerAdd1;
	}

	public void setPartnerAdd1(String partnerAdd1) {
		this.partnerAdd1 = partnerAdd1;
	}

	public String getPartnerPhoneNumber() {
		return partnerPhoneNumber;
	}

	public void setPartnerPhoneNumber(String partnerPhoneNumber) {
		this.partnerPhoneNumber = partnerPhoneNumber;
	}

	public BigDecimal getScllPercentage() {
		return scllPercentage;
	}

	public void setScllPercentage(BigDecimal scllPercentage) {
		this.scllPercentage = scllPercentage;
	}

	public BigDecimal getPartnerPercentage() {
		return partnerPercentage;
	}

	public void setPartnerPercentage(BigDecimal partnerPercentage) {
		this.partnerPercentage = partnerPercentage;
	}

	public BigDecimal getMinimumPoints() {
		return minimumPoints;
	}

	public void setMinimumPoints(BigDecimal minimumPoints) {
		this.minimumPoints = minimumPoints;
	}

	public String getPartnerEmailAddress() {
		return partnerEmailAddress;
	}

	public void setPartnerEmailAddress(String partnerEmailAddress) {
		this.partnerEmailAddress = partnerEmailAddress;
	}

	public String getPartnerAdd2() {
		return partnerAdd2;
	}

	public void setPartnerAdd2(String partnerAdd2) {
		this.partnerAdd2 = partnerAdd2;
	}

	public String getPartnerZipCode() {
		return partnerZipCode;
	}

	public void setPartnerZipCode(String partnerZipCode) {
		this.partnerZipCode = partnerZipCode;
	}
	
	
	
	
	
	

}
