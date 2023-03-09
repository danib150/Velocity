package com.velocitypowered.api.proxy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;

public class ApiProfileSerializer extends JsonSerializer<ApiProfile> {
    @Override
    public void serialize(ApiProfile profile, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        for (Field field : profile.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(profile);
                if (value != null) {
                    jsonGenerator.writeObjectField(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        jsonGenerator.writeEndObject();
    }
}
