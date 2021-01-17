package com.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChatsTest {
    @Test
    public void deleteChatTest(){
        String chatID = "37";
        Chats.deleteChat(chatID);
    }

    @Test
    public void getIdByEnterCodeTest(){
        String code = "V0fbx3jChA";
        Assertions.assertEquals("44", Chats.getIdByEnterCode(code));
    }

    @Test
    public void getNameTest(){
        Assertions.assertEquals("test1", Chats.getName("44"));
    }

    @Test
    public void getIdTest(){
        Assertions.assertEquals("44", Chats.getId("test1"));
    }
}
