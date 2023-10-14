package co.inventorsoft.academy.spring.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDDeserializer implements JsonDeserializer<UUID> {
    @Override
    public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
        JsonParseException {
        try {
            return UUID.fromString(json.getAsString());
        } catch (IllegalArgumentException e) {
            throw new JsonParseException(e);
        }
    }
}
