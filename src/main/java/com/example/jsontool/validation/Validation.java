package com.example.jsontool.validation;

import java.util.List;

public interface Validation {

    List<String> validate(String jsonText, String schemaText);
}
