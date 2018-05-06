package com.scll.login.model;

import java.util.Date;

public class LoginResponse {
    private String userId;
    private String partnerId;
    private String status;

    private Integer issuedCardCount;
	private Integer optedCardCount;
	private String pendingAmountFromSCLL;
    private String partnerName;
    private Date lastSuccessfullLogin;
    private Date lastFailureLogin;
    private Partner partnerDetails;
    private CustomerRedemption redemptionDetails;
    private String userType;
    private String message;
    
    private Integer redeemedPoints;
    private Integer availablePoints;
    

	public LoginResponse() {
    }

      
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getPendingAmountFromSCLL() {
		return pendingAmountFromSCLL;
	}

	public void setPendingAmountFromSCLL(String pendingAmountFromSCLL) {
		this.pendingAmountFromSCLL = pendingAmountFromSCLL;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public Date getLastSuccessfullLogin() {
		return lastSuccessfullLogin;
	}

	public void setLastSuccessfullLogin(Date lastSuccessfullLogin) {
		this.lastSuccessfullLogin = lastSuccessfullLogin;
	}

	public Date getLastFailureLogin() {
		return lastFailureLogin;
	}

	public void setLastFailureLogin(Date lastFailureLogin) {
		this.lastFailureLogin = lastFailureLogin;
	}

	public Partner getPartnerDetails() {
		return partnerDetails;
	}

	public void setPartnerDetails(Partner partnerDetails) {
		this.partnerDetails = partnerDetails;
	}

	public CustomerRedemption getRedemptionDetails() {
		return redemptionDetails;
	}

	public void setRedemptionDetails(CustomerRedemption redemptionDetails) {
		this.redemptionDetails = redemptionDetails;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Integer getIssuedCardCount() {
		return issuedCardCount;
	}


	public void setIssuedCardCount(Integer issuedCardCount) {
		this.issuedCardCount = issuedCardCount;
	}


	public Integer getOptedCardCount() {
		return optedCardCount;
	}


	public void setOptedCardCount(Integer optedCardCount) {
		this.optedCardCount = optedCardCount;
	}


	public Integer getRedeemedPoints() {
		return redeemedPoints;
	}


	public void setRedeemedPoints(Integer redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}


	public Integer getAvailablePoints() {
		return availablePoints;
	}


	public void setAvailablePoints(Integer availablePoints) {
		this.availablePoints = availablePoints;
	}

		
}
