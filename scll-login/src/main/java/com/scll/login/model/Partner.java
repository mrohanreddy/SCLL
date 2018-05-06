package com.scll.login.model;

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
public class Partner implements java.io.Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String partnerID;
	
	private String userID;
	
	private String partnerName;
	
	@JoinColumn(name = "UserID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
	private SignIn signIn;
	
	
	
	public String getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	
	
	public SignIn getSignIn() {
		return signIn;
	}

	public void setSignIn(SignIn signIn) {
		this.signIn = signIn;
	}



}
