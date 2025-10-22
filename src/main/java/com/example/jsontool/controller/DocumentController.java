package com.example.jsontool.controller;

import com.example.jsontool.model.JSONDocument;
import com.example.jsontool.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public String listDocuments(Model model) {
        model.addAttribute("documents", documentService.findAll());
        return "documents";
    }

    // Візуальна форма 2: Форма створення (GET-запит)
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("document", new JSONDocument());
        return "create-document";
    }

    // Обробка форми 2 (POST-запит)
    @PostMapping("/save")
    public String saveDocument(JSONDocument document) {
        documentService.save(document);
        return "redirect:/documents";
    }
}