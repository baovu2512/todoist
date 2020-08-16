package utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class MappingUtils {
    public static <T> List<T> parseJsonToModelList(String json, Class<T[]> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        T[] jsonToObject = gson.fromJson(json, clazz);
        return Arrays.asList(jsonToObject);
    }

    public static <T> Object parseJsonToModel(String json, Class<T> clazz) {

        JsonDeserializer<T> deserializer = new JsonDeserializer<T>() {
            public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.isJsonNull()) {
                    return null;
                }
                return new Gson().fromJson(json, typeOfT);
            }
        };
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(clazz, deserializer);
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, clazz);
    }
}
