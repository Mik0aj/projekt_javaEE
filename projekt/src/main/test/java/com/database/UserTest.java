package com.database;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private String newEmail = "test100@gmail.com";
    private String oldEmail;
    private String login = "test1";

    @BeforeEach
    public void prepare() {
        oldEmail = User.getEmail(login);
    }

    @AfterEach
    public void close() {
        User.setEmail(newEmail, oldEmail);
    }

    @Test
    public void getEmailTest(){
        String email =  User.getEmail("pawel");
        Assertions.assertEquals(email, "paswitalski@gmail.com");

        email =  User.getEmail("Bartek");
        Assertions.assertEquals(email, "bpietrzczyk@gmail.com");

        email =  User.getEmail("test1");
        Assertions.assertEquals(email, oldEmail);
    }

    @Test
    public void getLoginTest(){
        String login =  User.getLogin("paswitalski@gmail.com");
        Assertions.assertEquals(login, "pawel");
    }

    @Test
    public void getIdTest(){
        String id = User.getId("pawel");
        Assertions.assertEquals(id, "6");

        Assertions.assertEquals(Integer.parseInt(id), 6);
    }


    @Test
    public void setEmailTest(){

        boolean result =  User.setEmail(oldEmail, newEmail);

        Assertions.assertTrue(result);

        String email = User.getEmail(login);
        Assertions.assertEquals(email, newEmail);


    }


}
