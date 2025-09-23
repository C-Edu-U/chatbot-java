package com.example.supportai.config;

import com.example.supportai.model.PromptTemplate;
import com.example.supportai.repository.PromptTemplateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PromptTemplateRepository promptTemplateRepository;

    public DataSeeder(PromptTemplateRepository promptTemplateRepository) {
        this.promptTemplateRepository = promptTemplateRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (promptTemplateRepository.findByName("Default Customer Support").isEmpty()) {
            PromptTemplate template = new PromptTemplate();
            template.setName("Default Customer Support");
            template.setTemplate("You are a friendly and helpful customer support assistant for a small e-commerce store. Your goal is to provide concise and accurate information. The user's query is: '%s'. Respond clearly and politely.");
            promptTemplateRepository.save(template);
        }
    }
}
