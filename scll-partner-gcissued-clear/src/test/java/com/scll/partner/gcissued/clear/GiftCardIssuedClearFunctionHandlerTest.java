package com.scll.partner.gcissued.clear;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.partner.gcissued.clear.model.GiftCardIssuedRequest;
import com.scll.partner.gcissued.clear.model.GiftCardIssuedResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GiftCardIssuedClearFunctionHandlerTest {

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
        GiftCardIssuedClearFunctionHandler handler = new GiftCardIssuedClearFunctionHandler();
        Context ctx = createContext();
        GiftCardIssuedRequest request= new GiftCardIssuedRequest();
        request.setPartnerID("1");
        GiftCardIssuedResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Successfully Updated", output.getMessage());
    }
}
