package com.scll.partner.gcissued.clear;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
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
			Query query = session.createQuery("Update CustomerRedemption set Status=:status, PaymentIndicator=:paymentIndicator, PhysicalCardNumber=:physicalCardNumber where PartnerID= :partnerId");
			query.setParameter("partnerId", request.getPartnerID());
			query.setParameter("status","Opted");
			query.setParameter("paymentIndicator","");
			query.setParameter("physicalCardNumber","");
			logger.log("query---"+query.getQueryString());
			int updated = query.executeUpdate();
			session.getTransaction().commit();
			logger.log("result---"+updated);
			if (updated == 0) {
				response.setMessage("Issue Updating customer redemption");
				response.setStatus("-1");
			}
			else {
				response.setMessage("Successfully Updated");
				response.setStatus("0");
			}
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
