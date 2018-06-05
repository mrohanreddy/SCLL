package com.admin.partners.archive;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.admin.partners.archive.PartnersArchiveHandler;
import com.scll.admin.partners.archive.model.PartnersArchiveRequest;
import com.scll.admin.partners.archive.model.PartnersArchiveResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class PartnersArchiveHandlerTest {

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
    public void testPartnersArchiveHandler() {
        PartnersArchiveHandler handler = new PartnersArchiveHandler();
        Context ctx = createContext();
        PartnersArchiveRequest request = new PartnersArchiveRequest();
        request.setStoreID("1");
        PartnersArchiveResponse response = new PartnersArchiveResponse();
        
        response.setPartnersList(handler.handleRequest(request, ctx));
        

        // TODO: validate output here if needed.
        Assert.assertEquals("110", response.getPartnersList().get(0).getPartnerID());
    }
}
