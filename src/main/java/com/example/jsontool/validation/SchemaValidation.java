package com.example.jsontool.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SchemaValidation implements Validation {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);

    @Override
    public List<String> validate(String jsonText, String schemaText) {
        try {
            JsonNode schemaNode = objectMapper.readTree(schemaText);
            JsonSchema schema = factory.getSchema(schemaNode);

            JsonNode jsonNode = objectMapper.readTree(jsonText);
            Set<ValidationMessage> errors = schema.validate(jsonNode);
            return errors.stream()
                    .map(ValidationMessage::getMessage)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            return List.of("Validation failed due to an error: " + e.getMessage());
        }
    }
}