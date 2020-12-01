package com.strona.chat;

import com.changePassword.GenerateCode;

public class Chat {
    private String chatID;
    private String chatName;
    private String enterCode;

    public void setChatID(String chatID){
        this.chatID = chatID;
    }

    public void setChatName(String chatName){
        this.chatName = chatName;
    }

    public void setEnterCode(String enterCode){
        this.enterCode = enterCode;
    }

    public String getChatID(){
        return chatID;
    }

    public String getChatName(){
        return chatName;
    }

    public String getEnterCode(){
        return enterCode;
    }
}
