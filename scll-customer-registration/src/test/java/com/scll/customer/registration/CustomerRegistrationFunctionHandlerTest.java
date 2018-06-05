package com.scll.customer.registration;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.customer.registration.model.CustomerRegistrationRequest;
import com.scll.customer.registration.model.CustomerRegistrationResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CustomerRegistrationFunctionHandlerTest {

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
    public void testCustomerRegistrationFunctionHandler() {
        CustomerRegistrationFunctionHandler handler = new CustomerRegistrationFunctionHandler();
        Context ctx = createContext();
        CustomerRegistrationRequest request = new CustomerRegistrationRequest();
        request.setEmailID("srinivas.p1266@gmail.com");
        request.setMobileNumber("98880985473");
        request.setFirstName("srinivas");
        request.setLastName("P");
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("0", response.getStatus());
    }
}
