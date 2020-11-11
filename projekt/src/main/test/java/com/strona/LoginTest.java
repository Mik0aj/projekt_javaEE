package com.strona;


import net.sourceforge.jwebunit.junit.WebTester;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.junit.Before;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class LoginTest {
    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl("http://localhost:8080/projekt_war/");
    }

    @Test
    public void testForgetPassword() {
        WebTester tester = new WebTester();
        beginAt("Login");
        assertTitleEquals("Logowanie");
        clickLink("przypomnienie");
        assertTitleEquals("Wprowadź email");
        setTextField("email", "celowo zly email");
        submit();
        assertTitleEquals("Wprowadź email");
        //tester.assertElementPresent("errorEmail");
        //tester.assertMatchInElement("errorEmail", "Niepoprawny format adresu email.");
        //tester.assertTextInElement("errorEmail", "Niepoprawny format adresu email.");
        //tester.assertTextInTable("errorEmail", "Niepoprawny format adresu email.");

    }

}
