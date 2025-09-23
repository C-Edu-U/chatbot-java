package com.example.supportai.repository;

import com.example.supportai.model.PromptTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PromptTemplateRepository extends JpaRepository<PromptTemplate, Long> {
    Optional<PromptTemplate> findByName(String name);
}
