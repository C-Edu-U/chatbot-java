package com.example.supportai.controller;

// DTO for incoming chat message
public class ChatRequest {
    private String message;
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
