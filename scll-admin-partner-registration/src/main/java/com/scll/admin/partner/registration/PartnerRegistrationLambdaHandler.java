package com.scll.admin.partner.registration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.admin.partner.registration.model.Partner;
import com.scll.admin.partner.registration.model.PartnerRegistrationRequest;
import com.scll.admin.partner.registration.model.PartnerRegistrationResponse;
import com.scll.admin.partner.registration.model.SignIn;
import com.scll.admin.partner.registration.util.HibernateUtil;


public class PartnerRegistrationLambdaHandler implements RequestHandler<PartnerRegistrationRequest, PartnerRegistrationResponse> {

    @Override
    public PartnerRegistrationResponse handleRequest(PartnerRegistrationRequest partnerRegistrationRequest, Context context) {
    	LambdaLogger logger = context.getLogger();
        logger.log("Input: " + partnerRegistrationRequest);
        
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		PartnerRegistrationResponse response = new PartnerRegistrationResponse();
		
		SignIn signIn = new SignIn();
		signIn.setUserID(partnerRegistrationRequest.getPartnerID());
		signIn.setUserPassword("Welcome");
		signIn.setUserType("Partner");
		Partner partner = new Partner();
		partner.setSignIn(signIn);
		partner.setPartnerID(partnerRegistrationRequest.getPartnerID());
		partner.setStoreID(partnerRegistrationRequest.getStoreID());
		partner.setPartnerName(partnerRegistrationRequest.getPartnerName());
		partner.setPartnerAdd1(partnerRegistrationRequest.getPartnerAddress());
		partner.setPartnerPhoneNumber(partnerRegistrationRequest.getPartnerPhoneNumber());
		partner.setPartnerEmailAddress(partnerRegistrationRequest.getPartnerEmailAddress());
		partner.setScllPercentage(partnerRegistrationRequest.getScllPercentage());
		partner.setPartnerPercentage(partnerRegistrationRequest.getPartnerPercentage());
		partner.setMinimumPoints(partnerRegistrationRequest.getMinimumPoints());
		partner.setPartnershipSetupDate(partnerRegistrationRequest.getCollaborationDate());
		partner.setFrontImage(partnerRegistrationRequest.getFrontImage());
		partner.setBackImage(partnerRegistrationRequest.getBackImage());
		
		
		try {
			session.beginTransaction();
			session.save(partner);
			session.save(signIn);
			session.getTransaction().commit();
			response.setMessage("Partner Created Successfully");
			response.setStatus("0");
			String messageStatus=sendEmailToPartner(partnerRegistrationRequest.getPartnerEmailAddress(),partnerRegistrationRequest.getPartnerName(),partnerRegistrationRequest.getPartnerID());
		}
		catch (Exception e) {
			logger.log("Exception saving partner details !!!" + e.toString() + "-->" + e.getMessage());
			e.printStackTrace();
			if(e.getCause().getCause().getMessage().contains("Duplicate entry")&&e.getCause().getCause().getMessage().contains("'PRIMARY'")) {
				response.setMessage("Partner with given partnerID "+partnerRegistrationRequest.getPartnerID()+" is already created");
				response.setStatus("-1");
			}
			else {
				response.setMessage("error processing request");
				response.setStatus("-1");
			}
			logger.log("exception "+e.getCause().getCause().getMessage());
		}
		finally {
			session.close();
		}
        
        return response;
    }
    
    
    private String sendEmailToPartner(String email,String partnerName, String partnerID) {
    	String status="";
		 try {

				URL url = new URL("http://app.terrachemicalsco.com/api/scll/email?email="+email+"&partnerName="+partnerName+"&partnerId="+partnerID);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				/*if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}*/

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

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
