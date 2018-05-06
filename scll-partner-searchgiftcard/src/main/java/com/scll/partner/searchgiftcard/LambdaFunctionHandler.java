package com.scll.partner.searchgiftcard;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.partner.searchgiftcard.model.CustomerRedemption;
import com.scll.partner.searchgiftcard.model.SearchGiftCardRequest;
import com.scll.partner.searchgiftcard.util.HibernateUtil;

public class LambdaFunctionHandler implements RequestHandler<SearchGiftCardRequest, CustomerRedemption> {

    @Override
    public CustomerRedemption handleRequest(SearchGiftCardRequest searchGiftCardRequest, Context context) {
        context.getLogger().log("Input: " + searchGiftCardRequest.getSecurityCode());
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		CustomerRedemption customerRedemptionDetails = new CustomerRedemption();

		try {
			session.beginTransaction();
			
			Query query =session.createQuery("FROM CustomerRedemption where securityCode= :securityCode and partnerID=:partnerID");
			query.setParameter("securityCode",searchGiftCardRequest.getSecurityCode());
			query.setParameter("partnerID",searchGiftCardRequest.getPartnerID());
			customerRedemptionDetails = (CustomerRedemption)query.getSingleResult();	
			customerRedemptionDetails.setStatus("0");
			
		}catch(NoResultException e) {
			System.out.println(e.toString());
			e.getStackTrace();
			customerRedemptionDetails.setStatus("-1");
			customerRedemptionDetails.setMessage("Invalid Security Code or Partner");			 
		}catch (Exception e) {
			System.out.println(e.toString());
			e.getStackTrace();
			
		} finally {
			session.close();
		}
		
		
        // TODO: implement your handler
        return customerRedemptionDetails;
    }

}
