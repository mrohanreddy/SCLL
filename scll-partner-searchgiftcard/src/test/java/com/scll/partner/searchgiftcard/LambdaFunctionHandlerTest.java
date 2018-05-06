package com.scll.partner.searchgiftcard;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.partner.searchgiftcard.model.CustomerRedemption;
import com.scll.partner.searchgiftcard.model.SearchGiftCardRequest;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static SearchGiftCardRequest searchGiftCardRequest;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
    	searchGiftCardRequest = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();
        
        SearchGiftCardRequest searchGiftCardRequest = new SearchGiftCardRequest();
        searchGiftCardRequest.setSecurityCode("scll234243211");
        searchGiftCardRequest.setPartnerID("1");
        
        CustomerRedemption output = handler.handleRequest(searchGiftCardRequest, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
