package com.example.supportai.controller;

import com.example.supportai.model.Faq;
import com.example.supportai.repository.FaqRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faqs")
public class FaqController {

    private final FaqRepository faqRepository;

    public FaqController(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @PostMapping("/upload")
    public void uploadFaq(@RequestBody Faq faq) {
        faqRepository.save(faq);
    }
}
