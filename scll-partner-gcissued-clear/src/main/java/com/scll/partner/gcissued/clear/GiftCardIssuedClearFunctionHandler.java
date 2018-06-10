package com.scll.partner.gcissued.clear;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.partner.gcissued.clear.model.Customer;
import com.scll.partner.gcissued.clear.model.CustomerRedemption;
import com.scll.partner.gcissued.clear.model.GiftCardIssuedRequest;
import com.scll.partner.gcissued.clear.model.GiftCardIssuedResponse;
import com.scll.partner.gcissued.clear.util.HibernateUtil;


public class GiftCardIssuedClearFunctionHandler implements RequestHandler<GiftCardIssuedRequest, GiftCardIssuedResponse> {

    @Override
    public GiftCardIssuedResponse handleRequest(GiftCardIssuedRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Input: " + request);
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		GiftCardIssuedResponse response = new GiftCardIssuedResponse();
		
		try {

			session.beginTransaction();
			Query selectQuery = session.createQuery("FROM CustomerRedemption where PartnerID=:partnerID");
			selectQuery.setParameter("partnerID", request.getPartnerID());
			List<CustomerRedemption> customerRedemptionList =  selectQuery.getResultList();
			HashMap<String, Double> customerMap = new HashMap<String, Double>();
			Iterator<CustomerRedemption> customerRedemptionIterator = customerRedemptionList.iterator();
			
			while(customerRedemptionIterator.hasNext()) {
				CustomerRedemption customerRedemption = customerRedemptionIterator.next();
				if(customerMap.containsKey(customerRedemption.getCustomerID())){
					customerMap.put(customerRedemption.getCustomerID(),customerMap.get(customerRedemption.getCustomerID())+customerRedemption.getPointsUsed());
				}
				else {
					customerMap.put(customerRedemption.getCustomerID(),customerRedemption.getPointsUsed());
				}
				
			}
			
			
			
			Query query = session.createQuery("Delete CustomerRedemption where PartnerID= :partnerId");
			query.setParameter("partnerId", request.getPartnerID());
			logger.log("query---"+query.getQueryString());
			int updated = query.executeUpdate();
			
			
			Iterator customerMapList = customerMap.keySet().iterator();
			while(customerMapList.hasNext()) {
				String customerId = (String) customerMapList.next();
				Query customerSelectQuery = session.createQuery("From Customer where CustomerID= :customerID");
				customerSelectQuery.setParameter("customerID", customerId);
				
				Customer customer = (Customer) customerSelectQuery.getSingleResult();
				customer.setAvailablePoints(customer.getAvailablePoints()+customerMap.get(customerId));
				
				session.save(customer);
			}
			
			
			
			session.getTransaction().commit();
			logger.log("result---"+updated);
			
				response.setMessage("Successfully Updated");
				response.setStatus("0");
			
		}catch (Exception e) {
			logger.log("exception clearing issued gift cards"+e.getMessage().toString());
			e.printStackTrace();
		}
		finally {
			session.close();
		}
        
        return response;
    }

}
