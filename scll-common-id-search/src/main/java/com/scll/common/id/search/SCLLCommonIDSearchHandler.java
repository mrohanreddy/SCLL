package com.scll.common.id.search;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.common.id.search.model.Customer;
import com.scll.common.id.search.model.IDSearchRequest;
import com.scll.common.id.search.model.IDSearchResponse;
import com.scll.common.id.search.model.Partner;
import com.scll.common.id.search.util.HibernateUtil;

public class SCLLCommonIDSearchHandler implements RequestHandler<IDSearchRequest, IDSearchResponse> {

    @Override
    public IDSearchResponse handleRequest(IDSearchRequest idSearchRequest, Context context) {
    	LambdaLogger logger = context.getLogger();
        logger.log("Input: " + idSearchRequest);
        IDSearchResponse response = new IDSearchResponse();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		if(idSearchRequest.getUserType().equalsIgnoreCase("Partner")) {
			Query accountDetailsQuery = session.createQuery("FROM Partner where PartnerID= :partnerId");
			accountDetailsQuery.setParameter("partnerId", idSearchRequest.getUserID());
			 List<Partner> partnerList= accountDetailsQuery.getResultList();
			 if(null==partnerList || partnerList.size()==0 ) {
				 response.setStatus("0");
			 }
			 else {
				 response.setStatus("1");
			 }
		}
		else if(idSearchRequest.getUserType().equalsIgnoreCase("Customer")) {
			Query accountDetailsQuery = session.createQuery("FROM Customer where CustomerID= :customerID");
			accountDetailsQuery.setParameter("customerID", idSearchRequest.getUserID());
			 List<Customer> customerList= accountDetailsQuery.getResultList();
			 if(null==customerList || customerList.size()==0 ) {
				 response.setStatus("0");
			 }
			 else {
				 response.setStatus("1");
			 }
		}
       return response;
        
    }

}
