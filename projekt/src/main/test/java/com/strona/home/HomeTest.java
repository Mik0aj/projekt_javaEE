package com.strona.home;

import net.sourceforge.jwebunit.junit.WebTester;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import org.junit.Before;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class HomeTest {
    private final WebTester tester = new WebTester();

    @Before
    public void prepare() {
        setScriptingEnabled(false);
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        setBaseUrl("http://localhost:8080/projekt_war/");
    }

    @Test
    public void testHomePage() {
        beginAt("home");
        assertTitleEquals("Logowanie");
    }

    @Test
    public void testLoginHomePage() {
        beginAt("home");
        assertTitleEquals("Logowanie");

        setTextField("login", "NieUsuwać");
        setTextField("password", "NieUsuwać");
        clickButtonWithText("Zaloguj się");



        //assertLinkPresent("home");
        //clickLink("home");
        //assertTitleEquals("Discord2");
    }

    /*
    @Test
    public void testHomePage() {
        beginAt("home.jsp");
        assertTitleEquals("Home");
        assertLinkPresent("login");
        clickLink("login");
        assertTitleEquals("Login");
    }

     */
}
