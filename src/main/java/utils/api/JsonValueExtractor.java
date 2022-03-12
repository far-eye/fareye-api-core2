package utils.api;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author - Vinod Kumar
 */

@Slf4j
public class JsonValueExtractor {
    private JsonValueExtractor(){}
    /**
     * Method to extract value of string type key from response json based on jsonpath
     * @param response
     * @param jsonPath
     * @return
     */
    public static String extractStringValueFromResponse(Response response, String jsonPath){
            return response.jsonPath().getString(jsonPath);
    }

    /**
     * Method to extract value of integer type key from response json based on jsonpath
     * @param response
     * @param jsonPath
     * @return
     */
    public static Integer extractIntValueFromResponse(Response response, String jsonPath){
        return response.jsonPath().getInt(jsonPath);
    }


    /**
     * Method to extract list of values from json based on jsonpath
     * @param response
     * @param jsonPath
     * @return
     */
    public static List<String> extractListOfValuesFromResponseString(Response response, String jsonPath){
        return response.jsonPath().getList(jsonPath);
    }



    /**
     * Method to extract list of Integer values from json based on jsonpath
     * @param response
     * @param jsonPath
     * @return
     */
    public static List<Integer> extractListOfValuesFromResponseInteger(Response response, String jsonPath){
        return response.jsonPath().getList(jsonPath);
    }


}
