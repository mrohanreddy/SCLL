package com.scll.admin.list.partners.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerRedemption")
public class CustomerRedemption implements java.io.Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String CustomerRedemptionID;
	
	
	private String PartnerID;
	
	private String PaymentIndicator;
	private BigDecimal SCLLContribution;
	private String Status;
	@ManyToOne
    @JoinColumn(name="PartnerID", insertable = false, updatable = false)
	private Partner partner;
	
	
	public String getCustomerRedemptionID() {
		return CustomerRedemptionID;
	}
	public void setCustomerRedemptionID(String customerRedemptionID) {
		CustomerRedemptionID = customerRedemptionID;
	}
	public String getPartnerID() {
		return PartnerID;
	}
	public void setPartnerID(String partnerID) {
		PartnerID = partnerID;
	}
	public String getPaymentIndicator() {
		return PaymentIndicator;
	}
	public void setPaymentIndicator(String paymentIndicator) {
		PaymentIndicator = paymentIndicator;
	}
	public BigDecimal getSCLLContribution() {
		return SCLLContribution;
	}
	public void setSCLLContribution(BigDecimal sCLLContribution) {
		SCLLContribution = sCLLContribution;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	
	

}
