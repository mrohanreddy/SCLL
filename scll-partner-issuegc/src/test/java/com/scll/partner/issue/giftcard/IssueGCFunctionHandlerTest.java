package com.scll.partner.issue.giftcard;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.partner.issue.giftcard.model.IssueGiftCardRequest;
import com.scll.partner.issue.giftcard.model.IssueGiftCardResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class IssueGCFunctionHandlerTest {

    private static IssueGiftCardRequest input;
    private static IssueGiftCardResponse output;

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
        IssueGCFunctionHandler handler = new IssueGCFunctionHandler();
        Context ctx = createContext();
        input = new IssueGiftCardRequest();
        input.setPhysicalGiftCardId("test");
        input.setSecurityCode("1");
         output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("successfully updated", output.getMessage());
    }
}
