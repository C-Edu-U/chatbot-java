package com.example.supportai.controller;

import com.example.supportai.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest chatRequest) {
        String reply = chatService.handleChatMessage(chatRequest.getMessage());
        return new ChatResponse(reply);
    }
}
