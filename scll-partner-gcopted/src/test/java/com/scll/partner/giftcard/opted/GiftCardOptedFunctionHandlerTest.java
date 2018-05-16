package com.scll.partner.giftcard.opted;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.partner.giftcard.opted.model.GiftCardOpted;
import com.scll.partner.giftcard.opted.model.GiftCardOptedRequest;
import com.scll.partner.giftcard.opted.model.GiftCardOptedResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GiftCardOptedFunctionHandlerTest {

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
        GiftCardOptedFunctionHandler handler = new GiftCardOptedFunctionHandler();
        Context ctx = createContext();
        GiftCardOptedRequest request = new GiftCardOptedRequest();
        

        List<GiftCardOpted> output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
