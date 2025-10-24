package com.example.jsontool.service;

import com.example.jsontool.validation.Validation;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ValidationService {

    public List<String> executeValidation(Validation strategy, String jsonText, String schemaText) {
        return strategy.validate(jsonText, schemaText);
    }
}