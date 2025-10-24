package com.example.jsontool.validation;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.List;
import java.util.Collections;

public class SyntaxValidation implements Validation {

    @Override
    public List<String> validate(String jsonText, String schemaText) {
        try {
            new JSONObject(jsonText);
        } catch (JSONException e1) {
            try {
                new JSONArray(jsonText);
            } catch (JSONException e2) {
                return List.of("Invalid JSON syntax: " + e2.getMessage());
            }
        }
        return Collections.emptyList();
    }
}