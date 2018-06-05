package com.scll.admin.partner.summary;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.admin.partner.summary.model.PartnerSummaryRequest;
import com.scll.admin.partner.summary.model.PartnerSummaryResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class PartnerSummaryFunctionHandlerTest {

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
    public void testPartnerSummaryFunctionHandler() {
        PartnerSummaryFunctionHandler handler = new PartnerSummaryFunctionHandler();
        Context ctx = createContext();
        PartnerSummaryRequest request = new PartnerSummaryRequest();
        PartnerSummaryResponse response = new PartnerSummaryResponse();
        request.setPartnerID("1");
        request.setStoreID("1");
       
        response = handler.handleRequest(request, ctx);
        System.out.println("response------>"+response.toString());
        // TODO: validate output here if needed.
        Assert.assertEquals("A", response.getPartnerStatus());
    }
}
