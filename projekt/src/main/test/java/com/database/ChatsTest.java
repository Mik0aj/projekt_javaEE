package com.database;

import org.junit.jupiter.api.Test;

public class ChatsTest {
    @Test
    public void deleteChatTest(){
        String chatID = "37";
        Chats.deleteChat(chatID);
    }
}
