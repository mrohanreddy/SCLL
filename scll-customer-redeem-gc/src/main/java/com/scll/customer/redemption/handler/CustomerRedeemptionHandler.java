package com.scll.customer.redemption.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scll.customer.redemption.model.CardDetails;
import com.scll.customer.redemption.model.Customer;
import com.scll.customer.redemption.model.CustomerRedemption;
import com.scll.customer.redemption.model.Partner;
import com.scll.customer.redemption.model.PartnerDetails;
import com.scll.customer.redemption.model.RedeemptionRequest;
import com.scll.customer.redemption.model.RedeemptionResponse;
import com.scll.customer.redemption.util.HibernateUtil;
import com.scll.customer.redemption.util.RandomString;

public class CustomerRedeemptionHandler implements RequestHandler<RedeemptionRequest, RedeemptionResponse> {

	@Override
	public RedeemptionResponse handleRequest(RedeemptionRequest redeemptionRequest, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("Input: " + redeemptionRequest);

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		RedeemptionResponse redeemptionResponse = new RedeemptionResponse();
		
		try {
			List<CardDetails> cardDetailsList = redeemptionRequest.getCardsList();
			Iterator<CardDetails> cardDetailsIterator = cardDetailsList.iterator();
		
			Query customerQuery = session.createQuery("FROM Customer where CustomerID=:customerID");
			customerQuery.setParameter("customerID", redeemptionRequest.getCustomerID());
			System.out.println("customerQuery---->"+customerQuery.getQueryString());
			Customer customer = (Customer) customerQuery.getSingleResult();
			List<PartnerDetails> partnerDetailsList = new ArrayList<PartnerDetails>();
			RandomString gen = new RandomString(6);
			
			Integer pointsRedeemed = 0;
			redeemptionResponse.setCustomerName(customer.getCustomerFirstName()+" "+customer.getCustomerLastName());
			redeemptionResponse.setCustomerEmailId(customer.getCustomerEmailAddress());
			
			if(customer.getAvailablePoints()-redeemptionRequest.getTotalPointsRedeemed()>=0) {
			while (cardDetailsIterator.hasNext()) {
				
				CardDetails cardDetails = cardDetailsIterator.next();
				
				
				
				Query partnerQuery = session.createQuery("FROM Partner where PartnerID=:partnerID");
				partnerQuery.setParameter("partnerID", cardDetails.getPartnerID());
				System.out.println("partnerQuery---->"+partnerQuery.getQueryString());
				Partner partner = (Partner) partnerQuery.getSingleResult();
				
				
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
					Date now = new Date();
					String strDate = sdf.format(now);
					
					
					
					CustomerRedemption customerRedemption = new CustomerRedemption();
					customerRedemption.setCustomerRedemptionID(strDate);
					
					customerRedemption.setStoreID(redeemptionRequest.getStoreID());
					
					
					customerRedemption.setPointsUsed(new BigDecimal(cardDetails.getPoints()));
					BigDecimal scllContribution = new BigDecimal(Integer.parseInt(cardDetails.getPoints()) / 100)
							.multiply(partner.getScllPercentage());
					BigDecimal partnerContribution = new BigDecimal(Integer.parseInt(cardDetails.getPoints()) / 100)
							.multiply(partner.getPartnerPercentage());
					BigDecimal cardValue = scllContribution.add(partnerContribution);
					customerRedemption.setCardValue(cardValue);
					customerRedemption.setScllContribution(scllContribution);
					customerRedemption.setPartnerContribution(partnerContribution);
					customerRedemption.setRedemptionOptedDate(new Date());
					customerRedemption.setStatus("Opted");
					customerRedemption.setCustomerID(redeemptionRequest.getCustomerID());
					customerRedemption.setPartner(partner);
					//customerRedemption.setSecurityCode("scll" + strDate);
					customerRedemption.setSecurityCode(gen.nextString());
					
					session.beginTransaction();
					session.save(customerRedemption);
					
					pointsRedeemed = Integer.parseInt(cardDetails.getPoints())+pointsRedeemed;
					Query customerUpdateQuery = session.createQuery("update Customer set AvailablePoints=:availablePoints where CustomerID=:customerID");
					customerUpdateQuery.setParameter("availablePoints", customer.getAvailablePoints()-pointsRedeemed);
					customerUpdateQuery.setParameter("customerID", redeemptionRequest.getCustomerID());
					customerUpdateQuery.executeUpdate();
					session.getTransaction().commit();
					
					PartnerDetails partnerDetails = new PartnerDetails();
					partnerDetails.setCardValue(cardValue);
					partnerDetails.setPartnerAdd1(partner.getPartnerAdd1());
					partnerDetails.setPartnerAdd2(partner.getPartnerAdd2());
					partnerDetails.setPartnerID(partner.getPartnerID());
					partnerDetails.setPartnerMobileNumber(partner.getPartnerPhoneNumber());
					partnerDetails.setPartnerEmailAddress(partner.getPartnerEmailAddress());
					partnerDetails.setPartnerName(partner.getPartnerName());
					partnerDetails.setPartnerZipcode(partner.getPartnerZipCode());
					partnerDetails.setPointsUsed(cardDetails.getPoints());
					partnerDetails.setRedemptionOptedDate(customerRedemption.getRedemptionOptedDate());
					partnerDetails.setSecurityCode(customerRedemption.getSecurityCode());
					
					partnerDetailsList.add(partnerDetails);

			}
				redeemptionResponse.setMessage("Points Redeemed successfully");
				redeemptionResponse.setPartnerDetails(partnerDetailsList);
				redeemptionResponse.setStatus("0");
			}
			else {
				redeemptionResponse.setMessage("Available points are less than total points being redeemed");
				redeemptionResponse.setStatus("1");
			}
			String messageStatus=sendEmailToPartner(redeemptionResponse);
			
		} catch (Exception e) {
			logger.log("Exception saving partner details !!!" + e.toString() + "-->" + e.getMessage());
			e.printStackTrace();
			
			redeemptionResponse.setMessage("issue redeeming points "+e.getMessage());
			redeemptionResponse.setStatus("1");

		}

		finally {
			session.close();
		}

		return redeemptionResponse;
	}
	
	 private String sendEmailToPartner(RedeemptionResponse redeemptionResponse) {
	    	String status="";
			 try {

					URL url = new URL("http://app.terrachemicalsco.com/api/scll/redemption");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setDoOutput(true);
					//conn.setRequestProperty("Accept", "application/json");
					ObjectMapper mapper = new ObjectMapper();
					String input = mapper.writeValueAsString(redeemptionResponse);
					/*if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ conn.getResponseCode());
					}*/

					OutputStream os = conn.getOutputStream();
					os.write(input.getBytes());
					os.flush();
					
					
					
					BufferedReader br = new BufferedReader(new InputStreamReader(
							(conn.getInputStream())));

					
					conn.disconnect();
					status = "success";

				  } catch (MalformedURLException e) {

					e.printStackTrace();
					status= "error";

				  } catch (IOException e) {

					e.printStackTrace();
					status = "error";

				  }		
			 return status;
		}


}
