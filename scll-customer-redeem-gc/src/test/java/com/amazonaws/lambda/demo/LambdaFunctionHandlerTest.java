package com.amazonaws.lambda.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.customer.redemption.handler.CustomerRedeemptionHandler;
import com.scll.customer.redemption.model.CardDetails;
import com.scll.customer.redemption.model.RedeemptionRequest;
import com.scll.customer.redemption.model.RedeemptionResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        CustomerRedeemptionHandler handler = new CustomerRedeemptionHandler();
        Context ctx = createContext();
        RedeemptionRequest request = new RedeemptionRequest();
        RedeemptionResponse response = new RedeemptionResponse();
        List<CardDetails> cardDetailsList = new ArrayList<CardDetails>();
        CardDetails cardDetails = new CardDetails();
        
        request.setCustomerID("019880985974");
        request.setStoreID("1");
       request.setTotalPointsRedeemed(200);
        cardDetails.setPartnerID("1");
        cardDetails.setPoints("200");
        cardDetails.setRedeemptionValue("8");
        
        cardDetailsList.add(cardDetails);
        request.setCardsList(cardDetailsList);
        
        
        
        response = handler.handleRequest(request, ctx);

        System.out.println("response--->"+response.toString());
        
        // TODO: validate output here if needed.
        Assert.assertEquals("Points Redeemed successfully", response.getMessage());
    }
}
