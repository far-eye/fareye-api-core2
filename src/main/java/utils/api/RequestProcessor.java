package utils.api;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import utils.common.GenericMethods;
import utils.common.enums.HttpMethod;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static io.restassured.config.RestAssuredConfig.config;


/**
 * @Author - Vinod Kumar
 */

@Slf4j
public class RequestProcessor {
    private RequestSpecification requestSpec;
    private Map<String, String> cookies = new HashMap<>();

    /**
     * This method is for setting/initializing the api request based on supplied params.
     * This is being called by RequestWrapper class
     */
    protected void initRequest(String baseURI, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams, List<Map<String, String>> multiPartData, Map<String, String> formDataPayload, Object jsonPayload) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.addCookies(cookies);
        requestSpecBuilder.addHeaders(headerParams);
        requestSpecBuilder.addPathParams(pathParams);

        for (Map.Entry<String, String> cookiesMap : cookies.entrySet()) {
            if (cookiesMap.getKey().contains("SESSIONID")) {
                requestSpecBuilder.setSessionId(cookiesMap.getValue());
            }
        }
        requestSpecBuilder.setConfig(config().redirect(redirectConfig().followRedirects(false)));
        requestSpec = requestSpecBuilder.build();
        requestSpec.queryParams(queryParams);

        if (multiPartData != null) {
            updateMultiPart(requestSpec, multiPartData);
        }

        if (formDataPayload != null)
            requestSpec.formParams(formDataPayload);

        if (jsonPayload != null)
            requestSpec.body(jsonPayload.toString());

        requestSpec.filter(new RequestLogFilters());

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentType.JSON);
    }


    /**
     * This method is to hit the rest api with Rest-Assured library and get the response.
     * This is being called by RequestWrapper class
     */
    protected Response getResponse(String endPoint, String requestType) {
        Response response = null;
        if (requestType.equalsIgnoreCase(HttpMethod.GET.name())) {
            response = given().spec(requestSpec).get(endPoint);
        } else if (requestType.equalsIgnoreCase(HttpMethod.POST.name())) {
            response = given().spec(requestSpec).post(endPoint);
        } else if (requestType.equalsIgnoreCase(HttpMethod.PUT.name())) {
            response = given().spec(requestSpec).put(endPoint);
        } else if (requestType.equalsIgnoreCase(HttpMethod.PATCH.name())) {
            response = given().spec(requestSpec).patch(endPoint);
        } else if (requestType.equalsIgnoreCase(HttpMethod.DELETE.name())) {
            response = given().spec(requestSpec).delete(endPoint);
        }
        if (response == null) {
            log.error("API response is null ");
        } else {
            log.info("API Response Time: " + response.time() / 1000 + " Sec");
            log.info("API Response: " + response.body().asString());
            if (response.getCookies().size() > 0)
                cookies = response.getCookies();
            log.info(endPoint + "- Response Cookies: " + cookies);
        }
        return response;
    }


    /**
     * Used for adding multipart for attachments.
     * This is being called by initRequest Method.
     */
    private void updateMultiPart(RequestSpecification requestSpec, List<Map<String, String>> multiPartData) {
        for (Map<String, String> multiPartDatum : multiPartData) {
            GenericMethods.waitInSec(5);
            if (multiPartDatum.get("fileName").contains(".")) {
                requestSpec.multiPart(multiPartDatum.get("controlName"), new File(multiPartDatum.get("file")));
            } else {
                requestSpec.multiPart(new MultiPartSpecBuilder(multiPartDatum.get("file"))
                        .controlName(multiPartDatum.get("controlName"))
                        .fileName(multiPartDatum.get("fileName"))
                        .mimeType(multiPartDatum.get("mimeType"))
                        .build());
            }
        }

    }


}
