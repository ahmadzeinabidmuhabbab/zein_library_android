package com.example.zeinlibrary;

public class Token {
    String status;
    String error;
    String messages;
    int id;
    String token;

    public Token(String status, String error, String messages, int id, String token) {
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.id = id;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
