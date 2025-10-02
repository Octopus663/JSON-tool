package com.example.jsontool.repository;


import com.example.jsontool.model.JSONDocument;
import com.example.jsontool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JSONDocumentRepository extends JpaRepository<JSONDocument, Long> {
    List<JSONDocument> findByUser(User user);
}
