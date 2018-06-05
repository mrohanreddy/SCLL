package com.scll.admin.list.partners;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.admin.list.partners.model.ListPartnersRequest;
import com.scll.admin.list.partners.model.ListPartnersResponse;
import com.scll.admin.list.partners.model.PartnerDetails;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListPartnersHandlerTest {

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
    public void testListPartnersHandler() {
        ListPartnersHandler handler = new ListPartnersHandler();
        Context ctx = createContext();
        ListPartnersRequest request = new ListPartnersRequest();
        request.setStoreID("1");
        List<PartnerDetails> response = new ArrayList<PartnerDetails>();
        response = handler.handleRequest(request, ctx);
        System.out.println(response.get(1).getPartnerID());
        // TODO: validate output here if needed.
        Assert.assertEquals("102", response.get(1).getPartnerID());
    }
}
