package utils.api;


import io.restassured.response.Response;
import utils.common.enums.HttpMethod;

import java.util.List;
import java.util.Map;

/**
 * @Author - Vinod Kumar
 */
public class Rest {
    RequestProcessor requestProcessor = new RequestProcessor();

    /**
     * This is the GET request method and will be called by the client to get the api response
     */
    public Response get(String baseURI, String endPoint, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams) {
        requestProcessor.initRequest(baseURI, headerParams, pathParams, queryParams, null, null, null);
        return requestProcessor.getResponse(endPoint, HttpMethod.GET.name());
    }

    /**
     * This is the POST request method for form data and will be called by the client to get the api response
     */
    public Response post(String baseURI, String endPoint, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams, Map<String, String> formDataPayload) {
        requestProcessor.initRequest(baseURI, headerParams, pathParams, queryParams, null, formDataPayload, null);
        return requestProcessor.getResponse(endPoint, HttpMethod.POST.name());
    }

    /**
     * This is the POST request method for payload and will be called by the client to get the api response
     */
    public Response post(String baseURI, String endPoint, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams, Object jsonPayload) {
        requestProcessor.initRequest(baseURI, headerParams, pathParams, queryParams, null, null, jsonPayload);
        return requestProcessor.getResponse(endPoint, HttpMethod.POST.name());
    }


    /**
     * This is the POST request Method with Multipart Data Support
     */
    public Response post(String baseURI, String endPoint, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams, List<Map<String, String>> multiPartData) {
        requestProcessor.initRequest(baseURI, headerParams, pathParams, queryParams, multiPartData, null, null);
        return requestProcessor.getResponse(endPoint, HttpMethod.POST.name());
    }

    /**
     * This is the PATCH request method for payload and will be called by the client to get the api response
     */
    public Response patch(String baseURI, String endPoint, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams, Object jsonPayload) {
        requestProcessor.initRequest(baseURI, headerParams, pathParams, queryParams, null, null, jsonPayload);
        return requestProcessor.getResponse(endPoint, HttpMethod.PATCH.name());
    }


    /**
     * This is the DELETE request method for payload and will be called by the client to get the api response
     */
    public Response delete(String baseURI, String endPoint, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams) {
        requestProcessor.initRequest(baseURI, headerParams, pathParams, queryParams, null, null, null);
        return requestProcessor.getResponse(endPoint, HttpMethod.DELETE.name());
    }

    /**
     * This is the PUT request method  and will be called by the client to get the api response
     */
    public Response put(String baseURI, String endPoint, Map<String, String> headerParams, Map<String, String> pathParams, Map<String, String> queryParams) {
        requestProcessor.initRequest(baseURI, headerParams, pathParams, queryParams, null, null, null);
        return requestProcessor.getResponse(endPoint, HttpMethod.PUT.name());
    }

}
