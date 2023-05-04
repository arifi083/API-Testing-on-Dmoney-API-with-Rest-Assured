package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import setup.Setup;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class User extends Setup {
    public User() throws IOException {
        initConfig();

    }

    public JsonPath callLoginAPI(String email,String password){
        UserModel userModel = new UserModel(email,password);
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login");


        return res.jsonPath();

    }

    public JsonPath createUser(String name,String email,String password,String phoneNumber,String nid,String role){
        UserModel regModel = new UserModel(name,email,password,phoneNumber,nid,role);
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(regModel)
                        .when()
                        .post("/user/create");


        return res.jsonPath();

    }

    public JsonPath createAgent(String name,String email,String password,String phoneNumber,String nid,String role){
        UserModel agentModel = new UserModel(name,email,password,phoneNumber,nid,role);
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(agentModel)
                        .when()
                        .post("/user/create");


        return res.jsonPath();

    }


    public JsonPath searchCustomerByPhoneNumber(String phoneNumber){
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/user/search/Phonenumber/" + phoneNumber);


        return res.jsonPath();

    }

}
