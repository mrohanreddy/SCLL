package com.scll.admin.financial.report.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerRedemptionDetails {
	
	private String customerName;
	private String physicalCardNumber;
	private Date cardIssuedDate;
	private Date dateSCLLPaid;
	private BigDecimal scllContribution;
	private Date cardOptedDate;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhysicalCardNumber() {
		return physicalCardNumber;
	}
	public void setPhysicalCardNumber(String physicalCardNumber) {
		this.physicalCardNumber = physicalCardNumber;
	}
	public Date getCardIssuedDate() {
		return cardIssuedDate;
	}
	public void setCardIssuedDate(Date cardIssuedDate) {
		this.cardIssuedDate = cardIssuedDate;
	}
	public Date getDateSCLLPaid() {
		return dateSCLLPaid;
	}
	public void setDateSCLLPaid(Date dateSCLLPaid) {
		this.dateSCLLPaid = dateSCLLPaid;
	}
	public BigDecimal getScllContribution() {
		return scllContribution;
	}
	public void setScllContribution(BigDecimal scllContribution) {
		this.scllContribution = scllContribution;
	}
	public Date getCardOptedDate() {
		return cardOptedDate;
	}
	public void setCardOptedDate(Date cardOptedDate) {
		this.cardOptedDate = cardOptedDate;
	}
	@Override
	public String toString() {
		return "CustomerRedemptionDetails [customerName=" + customerName + ", physicalCardNumber=" + physicalCardNumber
				+ ", cardIssuedDate=" + cardIssuedDate + ", dateSCLLPaid=" + dateSCLLPaid + ", scllContribution="
				+ scllContribution + ", cardOptedDate=" + cardOptedDate + "]";
	}
	
	

}
