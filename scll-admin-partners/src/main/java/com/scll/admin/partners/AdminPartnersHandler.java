package com.scll.admin.partners;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.admin.partners.model.ListPartnersRequest;
import com.scll.admin.partners.model.Partner;
import com.scll.admin.partners.model.PartnerDetails;
import com.scll.admin.partners.util.HibernateUtil;

public class AdminPartnersHandler implements RequestHandler<ListPartnersRequest, List<PartnerDetails>> {

    @Override
    public List<PartnerDetails> handleRequest(ListPartnersRequest listPartnersRequest, Context context) {
        LambdaLogger logger =  context.getLogger();
        logger.log("Input: " + listPartnersRequest);
        
        List<PartnerDetails> partnerDetailList = new ArrayList<PartnerDetails>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			Query partnerListQuery = session.createQuery("FROM Partner where StoreID= :storeID");
			List<Partner> partnerList = new ArrayList<Partner>();
			partnerListQuery.setParameter("storeID", listPartnersRequest.getStoreID());
			partnerList = partnerListQuery.getResultList();
			
			for (Partner partner : partnerList) {
				PartnerDetails partnerDetails = new PartnerDetails();
				partnerDetails.setPartnerID(partner.getPartnerID());
				partnerDetails.setPartnerName(partner.getPartnerName());
				partnerDetailList.add(partnerDetails);
				
			}
			
		}catch (Exception e) {
			logger.log("exception retreiving partners details"+e.getMessage().toString());
			e.printStackTrace();
		}
		finally {
			session.close();
		}
        
        
       
        return partnerDetailList;
    }

}
