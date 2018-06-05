package com.scll.partner.issue.giftcard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.scll.partner.issue.giftcard.model.CustomerRedemption;
import com.scll.partner.issue.giftcard.model.IssueGiftCardRequest;
import com.scll.partner.issue.giftcard.model.IssueGiftCardResponse;
import com.scll.partner.issue.giftcard.util.HibernateUtil;

public class IssueGCFunctionHandler implements RequestHandler<IssueGiftCardRequest, IssueGiftCardResponse> {

	@Override
	public IssueGiftCardResponse handleRequest(IssueGiftCardRequest issueGiftCardRequest, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("Input: " + issueGiftCardRequest.getPhysicalGiftCardId());

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		IssueGiftCardResponse response = new IssueGiftCardResponse();

		try {

			session.beginTransaction();
			Query query = session.createQuery(
					"update CustomerRedemption set PhysicalCardNumber = :physicalCardNumber , Status= :status,PaymentIndicator= :paymentIndicator, cardCollectionDate= :cardCollectionDate"
							+ " where SecurityCode= :securityCode and Status= :optedStatus and PartnerID=:partnerID");
			query.setParameter("physicalCardNumber", issueGiftCardRequest.getPhysicalGiftCardId());
			query.setParameter("securityCode", issueGiftCardRequest.getSecurityCode());
			query.setParameter("cardCollectionDate",new Date());
			query.setParameter("status", "Issued");
			query.setParameter("paymentIndicator", "Pending");
			query.setParameter("optedStatus", "Opted");
			query.setParameter("partnerID", issueGiftCardRequest.getPartnerID());
			logger.log(query.getQueryString());
			int updated = query.executeUpdate();
			session.getTransaction().commit();

			response.setPhysicalGiftCardId(issueGiftCardRequest.getPhysicalGiftCardId());
			if (updated == 1) {
				response.setMessage("Successfully Updated");
				response.setStatus("0");
			}
			else {
				response.setMessage("Provided SecurityCode is already issued");
				response.setStatus("-1");
			}
				

			List<CustomerRedemption> customerRedemptionList = new ArrayList<CustomerRedemption>();
			Query accountDetailsQuery = session.createQuery("FROM CustomerRedemption where PartnerID= :partnerId");
			accountDetailsQuery.setParameter("partnerId", issueGiftCardRequest.getPartnerID());
			customerRedemptionList = accountDetailsQuery.getResultList();
			int issuedCardCount = 0;
			int optedCardCount = 0;
			BigDecimal pendingAmountFromSCLL = new BigDecimal("0");

			logger.log("cutstomerRedemptionList size of partner" + customerRedemptionList.size());

			for (CustomerRedemption customerRedemption : customerRedemptionList) {

				if (customerRedemption.getStatus().equals("Issued")) {
					issuedCardCount++;
				} else if (customerRedemption.getStatus().equals("Opted")) {
					optedCardCount++;
				}

				if (customerRedemption.getPaymentIndicator().equals("Pending")) {
					pendingAmountFromSCLL = pendingAmountFromSCLL.add(customerRedemption.getSCLLContribution());
				}
			}

			response.setIssuedCardCount(issuedCardCount);
			response.setOptedCardCount(optedCardCount);
			response.setPendingAmountFromSCLL(pendingAmountFromSCLL.toString());

		} catch (Exception e) {
			logger.log("Exception issueing card !!!" + e.toString() + "-->" + e.getMessage());
			e.printStackTrace();
			return response;
		}
		finally {
			session.close();
		}
		return response;
	}

}
