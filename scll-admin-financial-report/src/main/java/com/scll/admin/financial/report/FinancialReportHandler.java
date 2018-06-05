package com.scll.admin.financial.report;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.admin.financial.report.model.CustomerRedemption;
import com.scll.admin.financial.report.model.CustomerRedemptionDetails;
import com.scll.admin.financial.report.model.FinancialReportRequest;
import com.scll.admin.financial.report.model.FinancialReportResponse;
import com.scll.admin.financial.report.util.HibernateUtil;

public class FinancialReportHandler implements RequestHandler<FinancialReportRequest, List<CustomerRedemptionDetails>> {

    @Override
    public List<CustomerRedemptionDetails> handleRequest(FinancialReportRequest financialReportRequest, Context context) {
       LambdaLogger logger =  context.getLogger();
       logger.log("financialReportRequest " + financialReportRequest);
       
       FinancialReportResponse response = new FinancialReportResponse();
       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		response.setCustomerRedemptionList(new ArrayList<CustomerRedemptionDetails>());
		List<CustomerRedemptionDetails> customerRedemptionDetailsList = new ArrayList<CustomerRedemptionDetails>();
		
		
		try {
				List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
			Query accountDetailsQuery = session.createQuery("FROM CustomerRedemption where CardCollectionDate BETWEEN :stDate AND :edDate and StoreID=:storeID and PartnerID=:partnerID");
			accountDetailsQuery.setParameter("stDate",financialReportRequest.getStartDate() );
			accountDetailsQuery.setParameter("edDate", financialReportRequest.getEndDate());
			accountDetailsQuery.setParameter("storeID", financialReportRequest.getStoreID());
			accountDetailsQuery.setParameter("partnerID", financialReportRequest.getPartnerID());
			customerRedemptionList = accountDetailsQuery.getResultList();
			
			for (CustomerRedemption customerRedemptionResult : customerRedemptionList) {
				
				

				CustomerRedemptionDetails customerRedemptionDetails = new CustomerRedemptionDetails();
				customerRedemptionDetails.setCustomerName(customerRedemptionResult.getCustomer().getCustomerFirstName()+" "+customerRedemptionResult.getCustomer().getCustomerLastName());
				customerRedemptionDetails.setPhysicalCardNumber(customerRedemptionResult.getPhysicalCardNumber());
				customerRedemptionDetails.setCardIssuedDate(customerRedemptionResult.getCardCollectionDate());
				customerRedemptionDetails.setCardOptedDate(customerRedemptionResult.getRedemptionOptedDate());
				
				if(null!=customerRedemptionResult.getPaymentIndicator()&&customerRedemptionResult.getPaymentIndicator().equals("Paid"))
					customerRedemptionDetails.setDateSCLLPaid(customerRedemptionResult.getPaymentDate());
					customerRedemptionDetails.setScllContribution(customerRedemptionResult.getSCLLContribution());
					customerRedemptionDetailsList.add(customerRedemptionDetails);
				
			}
        
		}catch (Exception e) {
			logger.log("exception retreiving partners details"+e.getMessage().toString());
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		
        return customerRedemptionDetailsList ;
    }

}
