package utils.common;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * @Author - Vinod Kumar
 */
@Slf4j
public class JsonUtils {
    private JsonUtils(){}

    /**
     * Method to convert json into JsonObject
     * @param jsonFilePath
     * @return
     */
    public static JsonObject convertJsonIntoJsonObject(String jsonFilePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(jsonFilePath)) {
            return gson.fromJson(reader, JsonObject.class);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * Method to convert json into JsonArray
     * @param jsonFilePath
     * @return
     */
    public static JsonArray convertJsonIntoJsonArray(String jsonFilePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(jsonFilePath)) {
            return gson.fromJson(reader, JsonArray.class);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public static JsonArray getStringJsonArray(String ...jsonArrayValues) {
        JsonArray array = new JsonArray();
        for (String value : jsonArrayValues)
            array.add(value);
        return array;
    }

    public static JsonArray getIntJsonArray(Integer ...jsonArrayValues) {
        JsonArray array = new JsonArray();
        for (Integer value : jsonArrayValues)
            array.add(value);
        return array;
    }



    public static JsonArray getJsonArrayOfObjects(JsonObject... jsonObjects){
        JsonArray array = new JsonArray();
        for (JsonObject value : jsonObjects)
            array.add(value);
        return array;
    }


    public static JsonArray getJsonArrayWithMap(Map<Object,Object> map){
        JsonArray array = new JsonArray();
            String str = new Gson().toJson(map);
            array.add(new Gson().fromJson(str, JsonObject.class));
            return array;
    }


}
