package com.example.supportai.service;

// DTO for DeepSeek API Response
public class DeepSeekResponse {
    private java.util.List<Choice> choices;

    public java.util.List<Choice> getChoices() { return choices; }
    public void setChoices(java.util.List<Choice> choices) { this.choices = choices; }

    public static class Choice {
        private MessagePayload message;
        public MessagePayload getMessage() { return message; }
        public void setMessage(MessagePayload message) { this.message = message; }
    }
}
