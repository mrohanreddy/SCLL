package com.scll.partner.gcissued.clear.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	private String customerID;
	
	private String storeID;
	
	private String customerFirstName;
	
	private String customerLastName;
	
	private String customerMobileNumber;
	
	private String customerEmailAddress;
	
	private Double availablePoints;
	
	/*@JoinColumn(name = "UserID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	private SignIn signIn;
*/
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	/*public SignIn getSignIn() {
		return signIn;
	}

	public void setSignIn(SignIn signIn) {
		this.signIn = signIn;
	}*/

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}

	public void setCustomerEmailAddress(String customerEmailAddress) {
		this.customerEmailAddress = customerEmailAddress;
	}

	public Double getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(Double availablePoints) {
		this.availablePoints = availablePoints;
	}
	
	

	
	
}
