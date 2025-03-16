package com.qasmarts.framework.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Utility class for handling REST API operations
 * This class uses RestAssured for API testing
 * 
 * @author Abhinav Gupta
 */
public class ApiUtils {
    private static final Logger logger = LogManager.getLogger(ApiUtils.class);
    private final String baseUrl;
    
    /**
     * Constructor with base URL
     * 
     * @param baseUrl Base URL for all API requests
     */
    public ApiUtils(String baseUrl) {
        this.baseUrl = baseUrl;
        logger.info("Initialized ApiUtils with base URL: {}", baseUrl);
    }
    
    /**
     * Get the base request specification with common settings
     * 
     * @return RequestSpecification with base settings
     */
    private RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
    
    /**
     * Send a GET request to the specified endpoint
     * 
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response sendGetRequest(String endpoint) {
        logger.info("Sending GET request to endpoint: {}", endpoint);
        Response response = getBaseRequest().get(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Send a GET request to the specified endpoint with query parameters
     * 
     * @param endpoint API endpoint
     * @param queryParams Map of query parameters
     * @return Response object
     */
    public Response sendGetRequest(String endpoint, Map<String, String> queryParams) {
        logger.info("Sending GET request to endpoint: {} with query params: {}", endpoint, queryParams);
        Response response = getBaseRequest()
                .queryParams(queryParams)
                .get(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Send a POST request to the specified endpoint with request body
     * 
     * @param endpoint API endpoint
     * @param requestBody Request body as string (JSON)
     * @return Response object
     */
    public Response sendPostRequest(String endpoint, String requestBody) {
        logger.info("Sending POST request to endpoint: {}", endpoint);
        logger.debug("Request body: {}", requestBody);
        Response response = getBaseRequest()
                .body(requestBody)
                .post(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Send a PUT request to the specified endpoint with request body
     * 
     * @param endpoint API endpoint
     * @param requestBody Request body as string (JSON)
     * @return Response object
     */
    public Response sendPutRequest(String endpoint, String requestBody) {
        logger.info("Sending PUT request to endpoint: {}", endpoint);
        logger.debug("Request body: {}", requestBody);
        Response response = getBaseRequest()
                .body(requestBody)
                .put(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Send a PATCH request to the specified endpoint with request body
     * 
     * @param endpoint API endpoint
     * @param requestBody Request body as string (JSON)
     * @return Response object
     */
    public Response sendPatchRequest(String endpoint, String requestBody) {
        logger.info("Sending PATCH request to endpoint: {}", endpoint);
        logger.debug("Request body: {}", requestBody);
        Response response = getBaseRequest()
                .body(requestBody)
                .patch(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Send a DELETE request to the specified endpoint
     * 
     * @param endpoint API endpoint
     * @return Response object
     */
    public Response sendDeleteRequest(String endpoint) {
        logger.info("Sending DELETE request to endpoint: {}", endpoint);
        Response response = getBaseRequest()
                .delete(endpoint);
        logResponse(response);
        return response;
    }
    
    /**
     * Log response details for debugging
     * 
     * @param response Response object
     */
    private void logResponse(Response response) {
        logger.info("Response status code: {}", response.getStatusCode());
        logger.debug("Response body: {}", response.getBody().asString());
    }
} 