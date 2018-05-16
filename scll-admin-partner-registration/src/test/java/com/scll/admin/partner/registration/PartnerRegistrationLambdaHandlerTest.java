package com.scll.admin.partner.registration;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.admin.partner.registration.model.PartnerRegistrationRequest;
import com.scll.admin.partner.registration.model.PartnerRegistrationResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class PartnerRegistrationLambdaHandlerTest {

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
    public void testPartnerRegistrationLambdaHandler() {
        PartnerRegistrationLambdaHandler handler = new PartnerRegistrationLambdaHandler();
        Context ctx = createContext();
PartnerRegistrationRequest input = new PartnerRegistrationRequest();
PartnerRegistrationResponse output = new PartnerRegistrationResponse();
input.setPartnerID("20");
         output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Partner with given partnerID 20 is alreay created", output.getMessage());
    }
}
