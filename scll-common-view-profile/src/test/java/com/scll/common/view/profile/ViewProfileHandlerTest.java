package com.scll.common.view.profile;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.common.view.profile.model.ViewProfileRequest;
import com.scll.common.view.profile.model.ViewProfileResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ViewProfileHandlerTest {

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
    public void testViewProfileHandler() {
        ViewProfileHandler handler = new ViewProfileHandler();
        Context ctx = createContext();
        ViewProfileRequest request = new ViewProfileRequest();
        ViewProfileResponse response = new ViewProfileResponse();
        request.setUserId("1");
        request.setUserType("partner");
        response = handler.handleRequest(request, ctx);
        

        // TODO: validate output here if needed.
        Assert.assertEquals("0", response.getStatus());
    }
}
