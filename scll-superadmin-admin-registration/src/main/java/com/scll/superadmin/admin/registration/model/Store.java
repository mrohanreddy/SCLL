package com.scll.superadmin.admin.registration.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Store")
public class Store {
	
	@Id
	private String storeID;
	private String storeAdd1;
	private String storeAdd2;
	private String storeZipCode;
	private String storePhoneNumber;
	private String storeEmailAddress;
	
	
	
	
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getStoreAdd1() {
		return storeAdd1;
	}
	public void setStoreAdd1(String storeAdd1) {
		this.storeAdd1 = storeAdd1;
	}
	public String getStoreAdd2() {
		return storeAdd2;
	}
	public void setStoreAdd2(String storeAdd2) {
		this.storeAdd2 = storeAdd2;
	}
	public String getStoreZipCode() {
		return storeZipCode;
	}
	public void setStoreZipCode(String storeZipCode) {
		this.storeZipCode = storeZipCode;
	}
	public String getStorePhoneNumber() {
		return storePhoneNumber;
	}
	public void setStorePhoneNumber(String storePhoneNumber) {
		this.storePhoneNumber = storePhoneNumber;
	}
	public String getStoreEmailAddress() {
		return storeEmailAddress;
	}
	public void setStoreEmailAddress(String storeEmailAddress) {
		this.storeEmailAddress = storeEmailAddress;
	}
	
	
	

}
