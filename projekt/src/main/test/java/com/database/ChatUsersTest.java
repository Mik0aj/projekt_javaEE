package com.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChatUsersTest {
    @Test
    public void getNick(){
        String nick = ChatUsers.getNick("6","36");
        System.out.println("Nick: " + nick);
        Assertions.assertEquals("pawel", nick);
    }

    @Test
    public void setNickTest(){
        String userID = "6";
        String chatID = "36";
        String oldNick = ChatUsers.getNick(userID, chatID);

        ChatUsers.setNick(userID, chatID, "nowy");
        String newNick = ChatUsers.getNick("6","36");
        System.out.println("newNick: " + newNick);
        Assertions.assertEquals("nowy", newNick);

        ChatUsers.setNick(userID, chatID, oldNick);

        newNick = ChatUsers.getNick("6","36");
        System.out.println("newNick: " + newNick);
        Assertions.assertEquals(oldNick, newNick);
    }

    @Test
    public void isOwnerTest(){
        String userID = "6";
        String chatID = "36";

        boolean isOwner = ChatUsers.isOwner(userID, chatID);
        Assertions.assertTrue(isOwner);

    }

    @Test
    public void deleteChatUserTest(){
        // test jednorazowy dla właściciela
        String userID = "6";
        String chatID = "35";

        ChatUsers.deleteChatUser(userID, chatID);
        // Zadziałał poprawnie
    }

    @Test
    public void deleteChatUserTestStandardUser(){
        // test jednorazowy dla gościa
        String userID = "6";
        String chatID = "3";

        ChatUsers.deleteChatUser(userID, chatID);
        // Zadziałał poprawnie
    }
}
