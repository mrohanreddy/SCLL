package com.scll.customer.registration.model;


public class CustomerRegistrationRequest {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String emailID;
	private String password;
	private String scllStore;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getScllStore() {
		return scllStore;
	}
	public void setScllStore(String scllStore) {
		this.scllStore = scllStore;
	}
	
	
}
