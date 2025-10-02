package com.example.jsontool.repository;

import com.example.jsontool.model.Template;
import com.example.jsontool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByCreatedBy(User user);
}

