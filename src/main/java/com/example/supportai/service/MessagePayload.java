package com.example.supportai.service;

// DTO for Message Payload in DeepSeek API
public class MessagePayload {
    private String role;
    private String content;

    public MessagePayload() {}

    public MessagePayload(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
