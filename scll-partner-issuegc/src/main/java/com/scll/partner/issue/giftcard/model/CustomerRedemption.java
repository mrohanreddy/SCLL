package com.scll.partner.issue.giftcard.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerRedemption")
public class CustomerRedemption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String customerRedemptionID;
	
	private String physicalCardNumber;
	
	private String status;
	
	private String paymentIndicator;
	
	private BigDecimal sCLLContribution;
	
	private Date CardCollectionDate;
	
	private String securityCode;
	
	private String partnerID;

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

	public String getPaymentIndicator() {
		return paymentIndicator;
	}

	public void setPaymentIndicator(String paymentIndicator) {
		this.paymentIndicator = paymentIndicator;
	}

	public BigDecimal getSCLLContribution() {
		return sCLLContribution;
	}

	public void setSCLLContribution(BigDecimal sCLLContribution) {
		this.sCLLContribution = sCLLContribution;
	}

	public Date getCardCollectionDate() {
		return CardCollectionDate;
	}

	public void setCardCollectionDate(Date cardCollectionDate) {
		CardCollectionDate = cardCollectionDate;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public BigDecimal getsCLLContribution() {
		return sCLLContribution;
	}

	public void setsCLLContribution(BigDecimal sCLLContribution) {
		this.sCLLContribution = sCLLContribution;
	}

	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	
	
	
	
	

}
