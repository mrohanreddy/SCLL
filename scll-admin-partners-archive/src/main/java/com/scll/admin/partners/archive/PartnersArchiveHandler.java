package com.scll.admin.partners.archive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.admin.partners.archive.model.CustomerRedemption;
import com.scll.admin.partners.archive.model.Partner;
import com.scll.admin.partners.archive.model.PartnerDetails;
import com.scll.admin.partners.archive.model.PartnersArchiveRequest;
import com.scll.admin.partners.archive.model.PartnersArchiveResponse;
import com.scll.partners.archive.util.HibernateUtil;


public class PartnersArchiveHandler implements RequestHandler<PartnersArchiveRequest, List<PartnerDetails>> {

    @Override
    public  List<PartnerDetails> handleRequest(PartnersArchiveRequest partnersArchiveRequest, Context context) {
       LambdaLogger logger= context.getLogger();
       logger.log("Input: " + partnersArchiveRequest);

       PartnersArchiveResponse response = new PartnersArchiveResponse();
       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		response.setPartnersList(new ArrayList<PartnerDetails>());
		PartnerDetails partnerDetails = new PartnerDetails();
		
		try {
			List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
			List<Partner> partnerList = new ArrayList<Partner>();
			Query accountDetailsQuery = session.createQuery("FROM Partner where StoreID= :storeID and PartnerStatus=:partnerStatus");
			accountDetailsQuery.setParameter("storeID", partnersArchiveRequest.getStoreID());
			accountDetailsQuery.setParameter("partnerStatus", "I");
			partnerList = accountDetailsQuery.getResultList();
			
			for (Partner partner : partnerList) {
				customerRedemptionList = partner.getCustomerRedemptionList();
				int issuedCardCount = 0;
				int optedCardCount = 0;
				BigDecimal outStandingAmount = new BigDecimal("0");

				for (CustomerRedemption customerRedemption : customerRedemptionList) {
					if (null!=customerRedemption.getStatus()&&customerRedemption.getStatus().equals("Issued")) {
						issuedCardCount++;
					} else if (null!=customerRedemption.getStatus()&&customerRedemption.getStatus().equals("Opted")) {
						optedCardCount++;
					}
					
					if (null!=customerRedemption.getPaymentIndicator()&&customerRedemption.getPaymentIndicator().equals("Pending")) {
						outStandingAmount = outStandingAmount.add(customerRedemption.getSCLLContribution());
					}
					
				}
				
					partnerDetails = new PartnerDetails();
					
					partnerDetails.setPartnerID(partner.getPartnerID());
					partnerDetails.setPartnerName(partner.getPartnerName());
					partnerDetails.setPhone(partner.getPartnerPhoneNumber());
					partnerDetails.setPartnerAddress(partner.getPartnerAdd1());
					partnerDetails.setGiftCardsIssued(issuedCardCount);
					partnerDetails.setGiftCardsOpted(optedCardCount);
					partnerDetails.setOutStandingPayment(outStandingAmount);
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
