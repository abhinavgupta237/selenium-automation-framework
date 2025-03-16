package com.qasmarts.tests;

import com.qasmarts.framework.utils.ApiUtils;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for REST API testing
 * This class uses RestAssured for API testing
 * 
 * @author Abhinav Gupta
 */
public class RestApiTest {
    
    private static final String BASE_URL = "https://reqres.in/api";
    private ApiUtils apiUtils;
    
    /**
     * Setup method that runs before the test class
     */
    @BeforeClass
    public void setUp() {
        apiUtils = new ApiUtils(BASE_URL);
    }
    
    /**
     * Test to get a list of users
     */
    @Test(description = "Verify GET request to fetch users list")
    public void testGetUsers() {
        // Send GET request to fetch users
        Response response = apiUtils.sendGetRequest("/users?page=2");
        
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Validate response has expected structure
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0,
                "Response should contain user data");
        
        // Log some information for demonstration
        int page = response.jsonPath().getInt("page");
        int total = response.jsonPath().getInt("total");
        int perPage = response.jsonPath().getInt("per_page");
        
        System.out.println("Page: " + page);
        System.out.println("Total users: " + total);
        System.out.println("Users per page: " + perPage);
    }
    
    /**
     * Test to get a single user
     */
    @Test(description = "Verify GET request to fetch a single user")
    public void testGetSingleUser() {
        // Send GET request to fetch a specific user
        Response response = apiUtils.sendGetRequest("/users/2");
        
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Validate user data
        String firstName = response.jsonPath().getString("data.first_name");
        String lastName = response.jsonPath().getString("data.last_name");
        String email = response.jsonPath().getString("data.email");
        
        Assert.assertNotNull(firstName, "First name should not be null");
        Assert.assertNotNull(lastName, "Last name should not be null");
        Assert.assertNotNull(email, "Email should not be null");
        
        System.out.println("User details - Name: " + firstName + " " + lastName + ", Email: " + email);
    }
    
    /**
     * Test to create a new user
     */
    @Test(description = "Verify POST request to create a user")
    public void testCreateUser() {
        // Prepare request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Abhinav Gupta");
        requestBody.put("job", "QA Engineer");
        
        // Send POST request to create a user
        Response response = apiUtils.sendPostRequest("/users", requestBody.toString());
        
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        
        // Validate response has expected data
        Assert.assertEquals(response.jsonPath().getString("name"), "Abhinav Gupta",
                "Response should contain the correct name");
        Assert.assertEquals(response.jsonPath().getString("job"), "QA Engineer",
                "Response should contain the correct job");
        Assert.assertNotNull(response.jsonPath().getString("id"),
                "Response should contain an ID");
        Assert.assertNotNull(response.jsonPath().getString("createdAt"),
                "Response should contain creation timestamp");
        
        System.out.println("Created user with ID: " + response.jsonPath().getString("id"));
    }
    
    /**
     * Test to update a user
     */
    @Test(description = "Verify PUT request to update a user")
    public void testUpdateUser() {
        // Prepare request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Abhinav Gupta");
        requestBody.put("job", "Senior QA Engineer");
        
        // Send PUT request to update a user
        Response response = apiUtils.sendPutRequest("/users/2", requestBody.toString());
        
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Validate response has expected data
        Assert.assertEquals(response.jsonPath().getString("name"), "Abhinav Gupta",
                "Response should contain the correct name");
        Assert.assertEquals(response.jsonPath().getString("job"), "Senior QA Engineer",
                "Response should contain the updated job");
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"),
                "Response should contain update timestamp");
        
        System.out.println("Updated user at: " + response.jsonPath().getString("updatedAt"));
    }
    
    /**
     * Test to delete a user
     */
    @Test(description = "Verify DELETE request to remove a user")
    public void testDeleteUser() {
        // Send DELETE request to remove a user
        Response response = apiUtils.sendDeleteRequest("/users/2");
        
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 204, "Status code should be 204");
    }
    
    /**
     * Test to register a user
     */
    @Test(description = "Verify POST request for user registration")
    public void testRegisterUser() {
        // Prepare request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");
        
        // Send POST request to register a user
        Response response = apiUtils.sendPostRequest("/register", requestBody.toString());
        
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Validate response has token
        Assert.assertNotNull(response.jsonPath().getString("token"),
                "Response should contain a token");
        Assert.assertNotNull(response.jsonPath().getInt("id"),
                "Response should contain user ID");
        
        System.out.println("Registered user with token: " + response.jsonPath().getString("token"));
    }
    
    /**
     * Test unsuccessful login
     */
    @Test(description = "Verify error handling for invalid login")
    public void testUnsuccessfulLogin() {
        // Prepare request body with missing password
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "peter@klaven");
        
        // Send POST request with invalid data
        Response response = apiUtils.sendPostRequest("/login", requestBody.toString());
        
        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400");
        
        // Validate error message
        Assert.assertEquals(response.jsonPath().getString("error"), "Missing password",
                "Response should contain correct error message");
    }
} 