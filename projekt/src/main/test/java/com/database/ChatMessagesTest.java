package com.database;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ChatMessagesTest {

    @Test
    public void getChatMessagesTest(){
        ArrayList<Message> messages = ChatMessages.getChatMessages("2");

        messages.forEach(message -> System.out.println(message.getContent()));
    }


}
