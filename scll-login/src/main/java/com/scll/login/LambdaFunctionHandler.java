package com.scll.login;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.login.model.CustomerRedemption;
import com.scll.login.model.LoginResponse;
import com.scll.login.model.Partner;
import com.scll.login.model.SignIn;
import com.scll.login.util.HibernateUtil;

public class LambdaFunctionHandler implements RequestHandler<SignIn, LoginResponse> {

    @Override
    public LoginResponse handleRequest(SignIn signInRequest, Context context) {
    	LambdaLogger lambdaLogger = context.getLogger();
    	lambdaLogger.log("Input: " + signInRequest.getUserID());
        
              
        LoginResponse loginResponse = new LoginResponse();
		

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			
			Query signInQuery =session.createQuery("FROM SignIn where userID= :userId");
			signInQuery.setParameter("userId",signInRequest.getUserID());
			SignIn signInDetails = (SignIn)signInQuery.getSingleResult();		
			
			if (signInDetails == null) {
				loginResponse.setStatus("-1");
				loginResponse.setMessage("Invalid User details");
				return loginResponse;				
			}else if(!signInDetails.getUserPassword().equals(signInRequest.getUserPassword())) {
				lambdaLogger.log("invalid Password");
				loginResponse.setStatus("-1");
				loginResponse.setMessage("Invalid Password");
				signInDetails.setLastFailureLogin(new Date());
				session.save(signInDetails);
				session.getTransaction().commit();
				return loginResponse;				
			}
			
			if (signInDetails.getUserType().equals("Customer")) {
				lambdaLogger.log("login successful as a Customer");
				loginResponse.setStatus("0");
				loginResponse.setMessage("login successful");
				loginResponse.setUserId(signInDetails.getUserID());
				loginResponse.setAvailablePoints(800);
				loginResponse.setRedeemedPoints(0);			
				
			}else if (signInDetails.getUserType().equals("Partner")) {
				Query query = session.createQuery("FROM Partner p where p.userID= :userId");
				query.setParameter("userId", signInRequest.getUserID());
				Partner partnerDetails = (Partner) query.getSingleResult();
				lambdaLogger.log("login successful as a Partner");
			
					List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
					Query accountDetailsQuery = session.createQuery("FROM CustomerRedemption where PartnerID= :userId");
					accountDetailsQuery.setParameter("userId", signInRequest.getUserID());
					customerRedemptionList = accountDetailsQuery.getResultList();
					int issuedCardCount = 0;
					int optedCardCount = 0;
					BigDecimal pendingAmountFromSCLL = new BigDecimal("0");

					lambdaLogger.log("customerRedemptionList size : " + customerRedemptionList.size());

					for (CustomerRedemption customerRedemption : customerRedemptionList) {

						if (customerRedemption.getStatus().equals("Issued")) {
							issuedCardCount++;
						} else if (customerRedemption.getStatus().equals("Opted")) {
							optedCardCount++;
						}

						if (customerRedemption.getPaymentIndicator().equals("Pending")) {
							pendingAmountFromSCLL = pendingAmountFromSCLL.add(customerRedemption.getSCLLContribution());
						}

					}

					loginResponse.setStatus("0");
					loginResponse.setMessage("login successful");
					loginResponse.setUserId(partnerDetails.getSignIn().getUserID());
					loginResponse.setPartnerId(partnerDetails.getPartnerID());
					loginResponse.setIssuedCardCount(issuedCardCount);
					loginResponse.setOptedCardCount(optedCardCount);
					loginResponse.setPendingAmountFromSCLL(pendingAmountFromSCLL.toString());
					loginResponse.setPartnerName(partnerDetails.getPartnerName());
					loginResponse.setLastSuccessfullLogin(partnerDetails.getSignIn().getLastSuccessfulLogin());
					loginResponse.setLastFailureLogin(partnerDetails.getSignIn().getLastFailureLogin());
					loginResponse.setUserType(partnerDetails.getSignIn().getUserType());
					SignIn signIn = new SignIn();
					signIn = partnerDetails.getSignIn();
					signIn.setLastSuccessfulLogin(new Date());
					session.save(signIn);
					session.getTransaction().commit();

			}
		
		}catch(NoResultException e) {
			System.out.println(e.toString());
			e.getStackTrace();
			loginResponse.setStatus("-1");
			loginResponse.setMessage("Invalid User details");
			
	    }catch (Exception e) {
			System.out.println(e.toString());
			e.getStackTrace();
			loginResponse.setStatus("-1");
			loginResponse.setMessage(e.toString());
			
		} finally {
			session.close();
		}
    	
        return loginResponse;
    }

}
