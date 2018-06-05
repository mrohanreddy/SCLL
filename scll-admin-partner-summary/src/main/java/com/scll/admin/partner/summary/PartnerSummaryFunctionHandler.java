package com.scll.admin.partner.summary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.admin.partner.summary.model.CustomerRedemption;
import com.scll.admin.partner.summary.model.Partner;
import com.scll.admin.partner.summary.model.PartnerSummaryRequest;
import com.scll.admin.partner.summary.model.PartnerSummaryResponse;
import com.scll.admin.partner.summary.util.HibernateUtil;

public class PartnerSummaryFunctionHandler implements RequestHandler<PartnerSummaryRequest, PartnerSummaryResponse> {

    @Override
    public PartnerSummaryResponse handleRequest(PartnerSummaryRequest request, Context context) {
       LambdaLogger logger =  context.getLogger();
       logger.log("Input: " + request.toString());
       PartnerSummaryResponse response = new  PartnerSummaryResponse();
       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
			Query partnerSummaryQuery = session.createQuery("FROM CustomerRedemption where StoreID= :storeID and PartnerID= :partnerID");
			partnerSummaryQuery.setParameter("storeID",request.getStoreID());
			partnerSummaryQuery.setParameter("partnerID",request.getPartnerID());
			customerRedemptionList = partnerSummaryQuery.getResultList();
			int issuedCardCount=0;
			int optedCardCount=0;
			BigDecimal  outStandingAmount= new BigDecimal(0);
			List<Partner> partnerList = new ArrayList<Partner>();
			Partner partner = new Partner();
			if(!customerRedemptionList.isEmpty())
				for(CustomerRedemption customerRedemption : customerRedemptionList) {
					partner = customerRedemption.getPartner();
					if (null!=customerRedemption.getStatus()&&customerRedemption.getStatus().equals("Issued")) {
						issuedCardCount++;
					} else if (null!=customerRedemption.getStatus()&&customerRedemption.getStatus().equals("Opted")) {
						optedCardCount++;
					}
					
					if (null!=customerRedemption.getPaymentIndicator()&&customerRedemption.getPaymentIndicator().equals("Pending")) {
						outStandingAmount = outStandingAmount.add(customerRedemption.getSCLLContribution());
					}
				}
			else {
				Query partnerQuery = session.createQuery("FROM Partner where StoreID= :storeID and PartnerID=:partnerID");
				partnerQuery.setParameter("storeID", request.getStoreID());
				partnerQuery.setParameter("partnerID", request.getPartnerID());
				partnerList = partnerQuery.getResultList();
				partner = partnerList.get(0);
			}
			response.setGcIssued(issuedCardCount);
			response.setGcOpted(optedCardCount);
			response.setOutstandingAmount(outStandingAmount.toBigInteger().intValue());
			response.setPartnerID(partner.getPartnerID());
			response.setPartnerName(partner.getPartnerName());
			response.setPartnerStatus(partner.getPartnerStatus());
			response.setPartnerAdd1(partner.getPartnerAdd1());
			response.setPartnerAdd2(partner.getPartnerAdd2());
			response.setPartnerZipCode(partner.getPartnerZipCode());
			response.setPartnerMobileNumber(partner.getPartnerPhoneNumber());
			response.setPartnerEmailAddress(partner.getPartnerEmailAddress());
			
		}catch (Exception e) {
			logger.log("exception retreiving partners details"+e.getMessage().toString());
			e.printStackTrace();
		}finally {
			session.close();
		}
		

        
        return response;
    }

}
