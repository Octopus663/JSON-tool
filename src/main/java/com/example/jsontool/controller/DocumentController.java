package com.example.jsontool.controller;

import com.example.jsontool.model.JSONDocument;
import com.example.jsontool.service.DocumentService;
import com.example.jsontool.service.ValidationService;
import com.example.jsontool.validation.SchemaValidation;
import com.example.jsontool.validation.SyntaxValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ValidationService validationService;

    // ... (ваш старий код listDocuments, showCreateForm, saveDocument залишається тут) ...
    @GetMapping
    public String listDocuments(Model model) {
        model.addAttribute("documents", documentService.findAll());
        return "documents";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("document", new JSONDocument());
        return "create-document";
    }

    @PostMapping("/save")
    public String saveDocument(JSONDocument document) {
        documentService.save(document);
        return "redirect:/documents";
    }

    @PostMapping("/validate")
    @ResponseBody
    public Map<String, List<String>> validateDocument(
            @RequestParam("jsonText") String jsonText,
            @RequestParam("schemaText") String schemaText) {

        List<String> errors;
        if (schemaText == null || schemaText.trim().isEmpty()) {
            errors = validationService.executeValidation(new SyntaxValidation(), jsonText, null);
        } else {
            errors = validationService.executeValidation(new SchemaValidation(), jsonText, schemaText);
        }
        return Map.of("errors", errors);
    }
}