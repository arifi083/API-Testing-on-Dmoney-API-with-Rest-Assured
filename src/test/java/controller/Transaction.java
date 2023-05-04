package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.TransactionModel;
import model.UserModel;
import setup.Setup;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Transaction extends Setup {
    public Transaction() throws IOException {
        initConfig();
    }

    public JsonPath depositMoney(String fromAccount, String toAccount,int amount){
        TransactionModel transactionModel = new TransactionModel(fromAccount,toAccount,amount);
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when()
                        .post("/transaction/deposit");


        return res.jsonPath();

    }


    public JsonPath checkBalance(String phoneNumber){
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/balance/" +phoneNumber);


        return res.jsonPath();

    }


    public JsonPath searchByTracnsactionId(String TransactionId){
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/search/" +TransactionId);


        return res.jsonPath();

    }


    public JsonPath withDrawBalence(String fromAccount, String toAccount,int amount){
        TransactionModel transactionModel = new TransactionModel(fromAccount,toAccount,amount);
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when()
                        .post("/transaction/withdraw");


        return res.jsonPath();

    }


    public JsonPath sendBalence(String fromAccount, String toAccount,int amount){
        TransactionModel transactionModel = new TransactionModel(fromAccount,toAccount,amount);
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when()
                        .post("/transaction/sendmoney");


        return res.jsonPath();

    }


    public JsonPath checkStatement(String phoneNumber){
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/statement/" +phoneNumber);


        return res.jsonPath();

    }



    public JsonPath getCustomer(String customerId){
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization",prop.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/user/search/" +customerId);


        return res.jsonPath();

    }



}
