package com.scll.admin.partners;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.admin.partners.model.ListPartnersRequest;
import com.scll.admin.partners.model.PartnerDetails;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AdminPartnersHandlerTest {

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
    public void testAdminPartnersHandler() {
        AdminPartnersHandler handler = new AdminPartnersHandler();
        Context ctx = createContext();
        ListPartnersRequest request = new ListPartnersRequest();
        request.setStoreID("1");
        
        List<PartnerDetails> partnerDetailsList = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("1", partnerDetailsList.get(0).getPartnerID());
    }
}
