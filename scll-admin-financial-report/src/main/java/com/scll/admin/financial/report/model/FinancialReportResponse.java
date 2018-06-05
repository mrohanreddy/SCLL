package com.scll.admin.financial.report.model;

import java.util.List;

public class FinancialReportResponse {
	
	private List<CustomerRedemptionDetails> customerRedemptionList;

	public List<CustomerRedemptionDetails> getCustomerRedemptionList() {
		return customerRedemptionList;
	}

	public void setCustomerRedemptionList(List<CustomerRedemptionDetails> customerRedemptionList) {
		this.customerRedemptionList = customerRedemptionList;
	}

	
}
