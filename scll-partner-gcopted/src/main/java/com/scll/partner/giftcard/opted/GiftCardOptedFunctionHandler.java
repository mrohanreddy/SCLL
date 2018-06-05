package com.scll.partner.giftcard.opted;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.partner.giftcard.opted.model.CustomerRedemption;
import com.scll.partner.giftcard.opted.model.GiftCardOpted;
import com.scll.partner.giftcard.opted.model.GiftCardOptedRequest;
import com.scll.partner.giftcard.opted.model.GiftCardOptedResponse;
import com.scll.partner.giftcard.opted.util.HibernateUtil;

public class GiftCardOptedFunctionHandler implements RequestHandler<GiftCardOptedRequest, List<GiftCardOpted>> {

	@Override
    public List<GiftCardOpted> handleRequest(GiftCardOptedRequest giftcardOptedRequest, Context context) {
		LambdaLogger logger= context.getLogger();
		logger.log("PartnerID : " + giftcardOptedRequest.getPartnerID());
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		GiftCardOptedResponse response = new GiftCardOptedResponse();
		response.setGiftCardsOpted(new ArrayList<GiftCardOpted>());
		GiftCardOpted giftCardOpted= null;
		try {
			List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
			Query accountDetailsQuery = session.createQuery("FROM CustomerRedemption where PartnerID= :partnerId");
			accountDetailsQuery.setParameter("partnerId", giftcardOptedRequest.getPartnerID());
			customerRedemptionList = accountDetailsQuery.getResultList();
			
			for (CustomerRedemption customerRedemption : customerRedemptionList) {

				if (customerRedemption.getStatus().equals("Opted")) {
					giftCardOpted = new GiftCardOpted();
					giftCardOpted.setCardValue(customerRedemption.getCardValue().toString());
					logger.log("date---"+customerRedemption.getRedemptionOptedDate().toString());
					giftCardOpted.setDateOpted(customerRedemption.getRedemptionOptedDate());
					giftCardOpted.setFirstName(customerRedemption.getCustomer().getCustomerFirstName());
					giftCardOpted.setLastName(customerRedemption.getCustomer().getCustomerLastName());
					giftCardOpted.setStoreID(customerRedemption.getStoreID());
					
					response.getGiftCardsOpted().add(giftCardOpted);
				}
				
			}
			
		}catch (Exception e) {
			logger.log("exception getting gift cards details"+e.getMessage().toString());
			e.printStackTrace();
		}
		finally {
			session.close();
		}
        
        return response.getGiftCardsOpted();
    }

}
