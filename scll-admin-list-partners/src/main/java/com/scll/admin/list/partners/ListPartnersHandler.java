package com.scll.admin.list.partners;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.admin.list.partners.model.CustomerRedemption;
import com.scll.admin.list.partners.model.ListPartnersRequest;
import com.scll.admin.list.partners.model.ListPartnersResponse;
import com.scll.admin.list.partners.model.Partner;
import com.scll.admin.list.partners.model.PartnerDetails;
import com.scll.admin.list.partners.util.HibernateUtil;




public class ListPartnersHandler implements RequestHandler<ListPartnersRequest, List<PartnerDetails>> {

    @Override
    public List<PartnerDetails> handleRequest(ListPartnersRequest listPartnersRequest, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Input: " + listPartnersRequest);
        ListPartnersResponse response = new ListPartnersResponse();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		response.setPartnersList(new ArrayList<PartnerDetails>());
		PartnerDetails partnerDetails = new PartnerDetails();
		
		try {
			List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
			List<Partner> partnerList = new ArrayList<Partner>();
			Query accountDetailsQuery = session.createQuery("FROM Partner where StoreID= :storeID and PartnerStatus=:partnerStatus");
			accountDetailsQuery.setParameter("storeID", listPartnersRequest.getStoreID());
			accountDetailsQuery.setParameter("partnerStatus", "A");
			partnerList = accountDetailsQuery.getResultList();
			
			for (Partner partner : partnerList) {
				customerRedemptionList = partner.getCustomerRedemptionList();
				int issuedCardCount = 0;
				int optedCardCount = 0;
				BigDecimal pendingAmountFromSCLL = new BigDecimal("0");

				for (CustomerRedemption customerRedemption : customerRedemptionList) {
					if (customerRedemption.getStatus().equals("Issued")) {
						issuedCardCount++;
					} else if (customerRedemption.getStatus().equals("Opted")) {
						optedCardCount++;
					}
				}
				
					partnerDetails = new PartnerDetails();
					
					partnerDetails.setPartnerID(partner.getPartnerID());
					partnerDetails.setPartnerName(partner.getPartnerName());
					partnerDetails.setPhone(partner.getPartnerPhoneNumber());
					partnerDetails.setPartnerAddress(partner.getPartnerAdd1());
					partnerDetails.setGiftCardsIssued(issuedCardCount);
					partnerDetails.setGiftCardsOpted(optedCardCount);
					response.getPartnersList().add(partnerDetails);
				
			}
        
		}catch (Exception e) {
			logger.log("exception retreiving partners details"+e.getMessage().toString());
			e.printStackTrace();
		}
		finally {
			session.close();
		}

        
        return response.getPartnersList();
    }

}
