package com.scll.admin.financial.report;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.admin.financial.report.model.CustomerRedemptionDetails;
import com.scll.admin.financial.report.model.FinancialReportRequest;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class FinancialReportHandlerTest {

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
    public void testFinancialReportHandler() throws ParseException {
        FinancialReportHandler handler = new FinancialReportHandler();
        Context ctx = createContext();
       FinancialReportRequest request = new FinancialReportRequest();
       request.setStoreID("1");
       request.setPartnerID("1");
       request.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-07"));
       request.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-9"));
       List<CustomerRedemptionDetails> customerRedemptionDetails = new ArrayList<CustomerRedemptionDetails>();
       customerRedemptionDetails = handler.handleRequest(request, ctx);
       System.out.println("List----------->" +customerRedemptionDetails.toString());
        // TODO: validate output here if needed.
        Assert.assertEquals(6, customerRedemptionDetails.get(0).getScllContribution().toBigInteger());
    }
}
