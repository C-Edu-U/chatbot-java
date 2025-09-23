package com.example.supportai.repository;

import com.example.supportai.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    Optional<Faq> findByQuestion(String question);
}
