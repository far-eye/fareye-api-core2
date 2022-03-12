package utils.api;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author - Vinod Kumar
 */

@Slf4j
public class RequestLogFilters implements Filter {

    /**
     * This is for filtering the request details for logging and reporting purpose
     */
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        log.info("Request URI : " + requestSpec.getURI());
        log.info("Request method : " + requestSpec.getMethod());
        log.info("Request header params: " + requestSpec.getHeaders());
        log.info("Request params : " + requestSpec.getRequestParams());
        log.info("Request form params: " + requestSpec.getFormParams());
        log.info("Request cookies: " + requestSpec.getCookies());
        log.info("Request body : " + requestSpec.getBody());
        log.info("Multipart data : " + requestSpec.getMultiPartParams());

        return ctx.next(requestSpec, responseSpec);
    }
}
