package com.example.supportai.service;

// DTO for DeepSeek API Request
public class DeepSeekRequest {
    private java.util.List<MessagePayload> messages;
    private String model = "deepseek-chat";
    private boolean stream = false;

    public DeepSeekRequest(java.util.List<MessagePayload> messages) {
        this.messages = messages;
    }

    public java.util.List<MessagePayload> getMessages() { return messages; }
    public String getModel() { return model; }
    public boolean isStream() { return stream; }
}
