package com.scll.partner.giftcard.issued;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.partner.giftcard.issued.model.GiftCardIssued;
import com.scll.partner.giftcard.issued.model.GiftCardIssuedRequest;
import com.scll.partner.giftcard.issued.model.GiftCardIssuedResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GiftCardIssuedFunctionHandlerTest {

    private static GiftCardIssuedRequest input;

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
        GiftCardIssuedFunctionHandler handler = new GiftCardIssuedFunctionHandler();
        Context ctx = createContext();

        List<GiftCardIssued> output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
