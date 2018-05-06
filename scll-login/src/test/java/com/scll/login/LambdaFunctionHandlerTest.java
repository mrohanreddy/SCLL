package com.scll.login;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.scll.login.model.LoginResponse;
import com.scll.login.model.SignIn;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

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
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();
        
        SignIn signIn = new SignIn();        
        signIn.setUserID("1");
        signIn.setUserPassword("Welcome01");

        LoginResponse output = handler.handleRequest(signIn, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("0", output.getStatus());
    }
}
