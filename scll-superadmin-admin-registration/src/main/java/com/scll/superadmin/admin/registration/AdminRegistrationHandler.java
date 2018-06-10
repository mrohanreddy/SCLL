package com.scll.superadmin.admin.registration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.superadmin.admin.registration.model.AdminRegistrationRequest;
import com.scll.superadmin.admin.registration.model.AdminRegistrationResponse;
import com.scll.superadmin.admin.registration.model.SignIn;
import com.scll.superadmin.admin.registration.model.Store;
import com.scll.superadmin.admin.registration.util.HibernateUtil;

public class AdminRegistrationHandler implements RequestHandler<AdminRegistrationRequest, AdminRegistrationResponse> {
		
    @Override
    public AdminRegistrationResponse handleRequest(AdminRegistrationRequest request, Context context) {
        LambdaLogger logger= context.getLogger();
        logger.log("Input: " + request);
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		AdminRegistrationResponse response = new AdminRegistrationResponse();
		SignIn signIn = new SignIn();
		signIn.setUserID(request.getStoreID());
		signIn.setUserPassword("Welcome");
		signIn.setUserType("Store");
		
		Store store = new Store();
		store.setStoreID(request.getStoreID());
		store.setStoreAdd1(request.getStoreAdd1());
		store.setStoreAdd2(request.getStoreAdd2());
		store.setStoreZipCode(request.getStoreZipCode());
		store.setStorePhoneNumber(request.getStorePhoneNumber());
		store.setStoreEmailAddress(request.getStoreEmailAddress());
		
		
		try {
			session.beginTransaction();
			session.save(store);
			session.save(signIn);
			session.getTransaction().commit();
			response.setMessage("Store Created Successfully");
			response.setStatus("0");
			//String messageStatus=sendEmailToPartner(partnerRegistrationRequest.getPartnerEmailAddress(),partnerRegistrationRequest.getPartnerName(),partnerRegistrationRequest.getPartnerID());
		}catch (Exception e) {
			logger.log("Exception saving partner details !!!" + e.toString() + "-->" + e.getMessage());
			e.printStackTrace();
			if(e.getCause().getCause().getMessage().contains("Duplicate entry")&&e.getCause().getCause().getMessage().contains("'PRIMARY'")) {
				response.setMessage("Partner with given partnerID "+request.getStoreID()+" is already created");
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

}
