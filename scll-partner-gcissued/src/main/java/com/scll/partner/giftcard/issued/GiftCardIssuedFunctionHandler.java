package com.scll.partner.giftcard.issued;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.partner.giftcard.issued.model.CustomerRedemption;
import com.scll.partner.giftcard.issued.model.GiftCardIssuedRequest;
import com.scll.partner.giftcard.issued.model.GiftCardIssuedResponse;
import com.scll.partner.giftcard.issued.model.GiftCardIssued;
import com.scll.partner.giftcard.issued.util.HibernateUtil;


public class GiftCardIssuedFunctionHandler implements RequestHandler<GiftCardIssuedRequest, List<GiftCardIssued>> {

    @Override
    public List<GiftCardIssued> handleRequest(GiftCardIssuedRequest giftcardIssuedRequest, Context context) {
        LambdaLogger logger = context.getLogger();
        
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		GiftCardIssuedResponse response = new GiftCardIssuedResponse();
		response.setGiftCardsIssued(new ArrayList<GiftCardIssued>());
		GiftCardIssued giftCardIssued= null;
		
		try {

			List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
			Query accountDetailsQuery = session.createQuery("FROM CustomerRedemption where PartnerID= :partnerId");
			accountDetailsQuery.setParameter("partnerId", giftcardIssuedRequest.getPartnerID());
			customerRedemptionList = accountDetailsQuery.getResultList();
			
			
			for (CustomerRedemption customerRedemption : customerRedemptionList) {

				if (customerRedemption.getStatus().equals("Issued")) {
					giftCardIssued = new GiftCardIssued();
					giftCardIssued.setCardValue(customerRedemption.getCardValue().toString());
					giftCardIssued.setDateIssued(customerRedemption.getCardCollectionDate());
					giftCardIssued.setFirstName(customerRedemption.getCustomer().getCustomerFirstName());
					giftCardIssued.setLastName(customerRedemption.getCustomer().getCustomerLastName());
					giftCardIssued.setGiftCardID(customerRedemption.getPhysicalCardNumber());
					giftCardIssued.setStoreID(customerRedemption.getStoreID());
					response.getGiftCardsIssued().add(giftCardIssued);
				}
				
			}
        
		}catch (Exception e) {
			logger.log("exception getting gift cards details"+e.getMessage().toString());
			e.printStackTrace();
		}
		return response.getGiftCardsIssued();
    }
		
}
