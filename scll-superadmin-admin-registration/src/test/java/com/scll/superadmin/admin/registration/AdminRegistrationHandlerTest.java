package com.scll.superadmin.admin.registration;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.superadmin.admin.registration.model.AdminRegistrationRequest;
import com.scll.superadmin.admin.registration.model.AdminRegistrationResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AdminRegistrationHandlerTest {

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
    public void testAdminRegistrationHandler() {
        AdminRegistrationHandler handler = new AdminRegistrationHandler();
        Context ctx = createContext();
        
        AdminRegistrationRequest request = new AdminRegistrationRequest();
        AdminRegistrationResponse response = new AdminRegistrationResponse();
        
        request.setStoreID("newstore1");
        request.setStoreAdd1("add1 street");
        request.setStoreAdd2("add2 street");
        request.setStoreZipCode("12434");
        request.setStorePhoneNumber("8847738839");
        request.setStoreEmailAddress("store1.scll@scll.com");
        

        response = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("0", response.getStatus());
    }
}
