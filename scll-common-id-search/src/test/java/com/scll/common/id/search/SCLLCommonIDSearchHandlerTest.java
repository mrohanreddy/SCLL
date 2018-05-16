package com.scll.common.id.search;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.common.id.search.model.IDSearchRequest;
import com.scll.common.id.search.model.IDSearchResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class SCLLCommonIDSearchHandlerTest {

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
    public void testSCLLCommonIDSearchHandler() {
        SCLLCommonIDSearchHandler handler = new SCLLCommonIDSearchHandler();
        Context ctx = createContext();
        IDSearchRequest request = new IDSearchRequest();
        IDSearchResponse response = new IDSearchResponse();
        request.setUserID("22");
        request.setUserType("partner");
        response = handler.handleRequest(request, ctx);
        System.out.println("status--->"+response.getStatus());
        // TODO: validate output here if needed.
        Assert.assertEquals("1", response.getStatus());
    }
}
