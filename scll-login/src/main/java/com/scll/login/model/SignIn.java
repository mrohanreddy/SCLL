package com.scll.login.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SignIn")
public class SignIn implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userID;
	
	private String userType;
	private String userPassword;
	private Date lastSuccessfulLogin;
	private Date lastFailureLogin;
	
	
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Date getLastSuccessfulLogin() {
		return lastSuccessfulLogin;
	}
	public void setLastSuccessfulLogin(Date lastSuccessfulLogin) {
		this.lastSuccessfulLogin = lastSuccessfulLogin;
	}
	public Date getLastFailureLogin() {
		return lastFailureLogin;
	}
	public void setLastFailureLogin(Date lastFailureLogin) {
		this.lastFailureLogin = lastFailureLogin;
	}
	

}
