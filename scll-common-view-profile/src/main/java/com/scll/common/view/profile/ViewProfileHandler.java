package com.scll.common.view.profile;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.common.view.profile.model.Customer;
import com.scll.common.view.profile.model.Partner;
import com.scll.common.view.profile.model.ViewProfileRequest;
import com.scll.common.view.profile.model.ViewProfileResponse;
import com.scll.common.view.profile.util.HibernateUtil;

public class ViewProfileHandler implements RequestHandler<ViewProfileRequest, ViewProfileResponse> {

    @Override
    public ViewProfileResponse handleRequest(ViewProfileRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Input: " + request);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		ViewProfileResponse viewProfileResponse = new ViewProfileResponse();
		
		try {
			
			if(request.getUserType().equalsIgnoreCase("partner")) {
				Query partnerQuery = session.createQuery("FROM Partner where PartnerID=:partnerID");
				partnerQuery.setParameter("partnerID", request.getUserId());
				Partner partner = (Partner) partnerQuery.getSingleResult();
				viewProfileResponse.setPartner(partner);
				viewProfileResponse.setMessage("success");
				viewProfileResponse.setStatus("0");
			}
			else if(request.getUserType().equalsIgnoreCase("customer")) {
				Query customerQuery = session.createQuery("FROM Customer where CustomerID=:customerID");
				customerQuery.setParameter("customerID", request.getUserId());
				Customer cusotmer = (Customer) customerQuery.getSingleResult();
				viewProfileResponse.setCustomer(cusotmer);
				viewProfileResponse.setMessage("success");
				viewProfileResponse.setStatus("0");
				
			}
			
		}catch (Exception e) {
			logger.log("Exception saving partner details !!!" + e.toString() + "-->" + e.getMessage());
			e.printStackTrace();
			
			viewProfileResponse.setMessage("issue redeeming points "+e.getMessage());
			viewProfileResponse.setStatus("1");

		}finally {
			session.close();
		}
        

 
        return viewProfileResponse;
    }

}
