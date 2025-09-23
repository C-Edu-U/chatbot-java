package com.example.supportai.controller;

import com.example.supportai.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    private final ChatService chatService;

    public SettingsController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/ia")
    public Map<String, Boolean> isIaEnabled() {
        return Map.of("iaEnabled", chatService.isIaEnabled());
    }

    @PostMapping("/ia")
    public void setIaEnabled(@RequestBody Map<String, Boolean> payload) {
        chatService.setIaEnabled(payload.get("iaEnabled"));
    }
}
