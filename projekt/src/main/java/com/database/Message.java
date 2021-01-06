package com.database;

public class Message {
    public String id;
    public String chat_id;
    public String user_id;
    public String content;
    public String send_time;

    public Message(String id, String chat_id, String user_id, String content, String send_time) {
        this.id = id;
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.content = content;
        this.send_time = send_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }
}
