package com.scll.customer.registration;

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
import com.scll.customer.registration.model.Customer;
import com.scll.customer.registration.model.CustomerRegistrationRequest;
import com.scll.customer.registration.model.CustomerRegistrationResponse;
import com.scll.customer.registration.model.SignIn;
import com.scll.customer.registration.util.HibernateUtil;

public class CustomerRegistrationFunctionHandler implements RequestHandler<CustomerRegistrationRequest, CustomerRegistrationResponse> {

    @Override
    public CustomerRegistrationResponse handleRequest(CustomerRegistrationRequest registrationRequest, Context context) {
    	LambdaLogger logger = context.getLogger();
    	logger.log("Input: " + registrationRequest);
    	
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        
        
		SignIn signIn = new SignIn();
		signIn.setUserID(registrationRequest.getMobileNumber());
		signIn.setUserPassword(registrationRequest.getPassword());
		signIn.setUserType("Customer");
		
		Customer customer = new Customer(); 
        customer.setCustomerID(registrationRequest.getMobileNumber());
        customer.setCustomerFirstName(registrationRequest.getFirstName());
        customer.setCustomerLastName(registrationRequest.getLastName());
        customer.setCustomerMobileNumber(registrationRequest.getMobileNumber());
        customer.setCustomerEmailAddress(registrationRequest.getEmailID());
        customer.setStoreID(registrationRequest.getScllStore());
        customer.setAvailablePoints(new Double(0));
        
        
        try {
			session.beginTransaction();
			session.save(customer);
			session.save(signIn);
			session.getTransaction().commit();
			response.setMessage("Customer Created Successfully");
			response.setStatus("0");
			String messageStatus=sendEmailToPartner(customer.getCustomerEmailAddress(),customer.getCustomerFirstName(),customer.getCustomerID());
		}
		catch (Exception e) {
			logger.log("Exception saving customer details !!!" + e.toString() + "-->" + e.getMessage());
			e.printStackTrace();
			if(e.getCause().getCause().getMessage().contains("Duplicate entry")&&e.getCause().getCause().getMessage().contains("'PRIMARY'")) {
				response.setMessage("Customer with mobile number # "+registrationRequest.getMobileNumber()+" is already created");
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

        // TODO: implement your handler
        return response;
    }

    private String sendEmailToPartner(String email,String customerName, String customerId) {
    	String status="";
		 try {

				URL url = new URL("http://app.terrachemicalsco.com/api/scll/customer-email?email="+email+"&customerName="+customerName);
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
