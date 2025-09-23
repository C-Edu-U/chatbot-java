package com.example.supportai.service;

import com.example.supportai.model.Conversation;
import com.example.supportai.model.Message;
import com.example.supportai.model.PromptTemplate;
import com.example.supportai.repository.ConversationRepository;
import com.example.supportai.repository.FaqRepository;
import com.example.supportai.repository.PromptTemplateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;

@Service
public class ChatService {

    private final ConversationRepository conversationRepository;
    private final PromptTemplateRepository promptTemplateRepository;
    private final FaqRepository faqRepository;
    private final RestTemplate restTemplate;

    @Value("${chatbot.ia.api.key}")
    private String apiKey;

    @Value("${chatbot.ia.api.url}")
    private String apiUrl;

    private boolean iaEnabled = true;

    public ChatService(ConversationRepository conversationRepository, PromptTemplateRepository promptTemplateRepository, FaqRepository faqRepository) {
        this.conversationRepository = conversationRepository;
        this.promptTemplateRepository = promptTemplateRepository;
        this.faqRepository = faqRepository;
        this.restTemplate = new RestTemplate();
    }

    public String handleChatMessage(String userMessageContent) {
        // 1. Create and save conversation and user message
        Conversation conversation = new Conversation();
        
        Message userMessage = new Message();
        userMessage.setSender("USER");
        userMessage.setContent(userMessageContent);
        userMessage.setConversation(conversation);
        conversation.getMessages().add(userMessage);
        conversationRepository.save(conversation);

        String responseContent;
        if (iaEnabled) {
            // 2. Find template and build prompt
            PromptTemplate promptTemplate = promptTemplateRepository.findByName("Default Customer Support")
                    .orElseThrow(() -> new RuntimeException("Default template not found"));
            
            String finalPrompt = String.format(promptTemplate.getTemplate(), userMessageContent);

            // 3. Call DeepSeek API
            responseContent = getAiResponse(finalPrompt);
        } else {
            // 2. Find answer in FAQ
            responseContent = faqRepository.findByQuestion(userMessageContent)
                    .map(faq -> faq.getAnswer())
                    .orElse("Sorry, I don\'t have an answer for that.");
        }

        // 4. Save AI message
        Message aiMessage = new Message();
        aiMessage.setSender("AI");
        aiMessage.setContent(responseContent);
        aiMessage.setConversation(conversation);
        conversation.getMessages().add(aiMessage);
        conversationRepository.save(conversation);

        return responseContent;
    }

    private String getAiResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        MessagePayload messagePayload = new MessagePayload("user", prompt);
        DeepSeekRequest request = new DeepSeekRequest(Collections.singletonList(messagePayload));

        HttpEntity<DeepSeekRequest> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<DeepSeekResponse> response = restTemplate.postForEntity(apiUrl, entity, DeepSeekResponse.class);
            if (response.getBody() != null && !response.getBody().getChoices().isEmpty()) {
                return response.getBody().getChoices().get(0).getMessage().getContent();
            }
        } catch (Exception e) {
            // Basic error handling
            return "Error communicating with AI service: " + e.getMessage();
        }
        return "Sorry, I could not get a response.";
    }

    public boolean isIaEnabled() {
        return iaEnabled;
    }

    public void setIaEnabled(boolean iaEnabled) {
        this.iaEnabled = iaEnabled;
    }
}
