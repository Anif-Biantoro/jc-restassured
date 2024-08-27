package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest {

    String baseUrl = "https://reqres.in/api";

    @Test
    public void testValidLogin(){
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityclicks");
        System.out.println(requestBody.toJSONString());

        request.header("content-type","application/json");
        request.body(requestBody.toJSONString());
        Response response = request.post("/login");

        String token = response.getBody().jsonPath().getString("token");
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
