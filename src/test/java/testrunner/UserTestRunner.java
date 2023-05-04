package testrunner;

import controller.User;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    User user;

    // Negative Test Case
    @Test(priority = 1, description = "calling login API for invalid Password")
    public void doLoginWithInvalidCred() throws IOException {
        user = new User();
        JsonPath jsonResponse =user.callLoginAPI("salman@roadtocareer.net","12345");
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Password incorrect"));

    }
    @Test(priority = 2, description = "calling login API for invalid Email")
    public void doLoginWithInvalidEmail() throws IOException {
        user = new User();
        JsonPath jsonResponse = user.callLoginAPI("salmann@roadtocareer.net","1234");
        String message = jsonResponse.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("User not found"));
    }


    @Test(priority = 3, description = "calling login API for valid user")
    public void doLoginWithvalidCred() throws IOException, ConfigurationException {
        user = new User();
        JsonPath jsonResponse = user.callLoginAPI("salman@roadtocareer.net","1234");
        String message = jsonResponse.get("message");
        String token = jsonResponse.get("token");
        Utils.setEnvVariable("token", token);
        System.out.println(token);
        System.out.println(message);

        Assert.assertTrue(message.contains("Login successfully"));
    }


    @Test(priority = 4, description = "calling API for creating user with existing information")
    public void createUserWithExistingInfo() throws IOException, ConfigurationException {
        user = new User();
        JsonPath jsonResponse = user.createUser("Jordan Stroman MD","alvaro.ryan@yahoo.com","1234","01700211618","1100154448","Customer");
        //System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("User already exists"));
    }


    @Test(priority = 5, description = "calling API for creating new user")
    public void createUser1() throws IOException, ConfigurationException {
        user = new User();
        Utils utils = new Utils();
        utils.generateRandomUser();
        JsonPath jsonResponse = user.createUser(utils.getName(),utils.getEmail(),"1234",utils.generatePhoneNumber(),"123456789","Customer");
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Utils.setEnvVariable("Customer_id1",jsonResponse.get("user.id").toString());
        Utils.setEnvVariable("Customer_name",jsonResponse.get("user.name"));
        Utils.setEnvVariable("Customer_email",jsonResponse.get("user.email"));
        Utils.setEnvVariable("Customer_phone1",jsonResponse.get("user.phone_number"));

        Assert.assertTrue(message.contains("User created"));
    }


    @Test(priority = 6, description = "calling API for creating new user")
    public void createUser2() throws IOException, ConfigurationException {
        user = new User();
        Utils utils = new Utils();
        utils.generateRandomUser();
        JsonPath jsonResponse = user.createUser(utils.getName(),utils.getEmail(),"1234",utils.generatePhoneNumber(),"123456789","Customer");
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Utils.setEnvVariable("Customer_id2",jsonResponse.get("user.id").toString());
        Utils.setEnvVariable("Customer_name",jsonResponse.get("user.name"));
        Utils.setEnvVariable("Customer_email",jsonResponse.get("user.email"));
        Utils.setEnvVariable("Customer_phone2",jsonResponse.get("user.phone_number"));

        Assert.assertTrue(message.contains("User created"));
    }


    @Test(priority = 7, description = "calling API for creating Agent with existing information")
    public void createAgentWithExistingInfo() throws IOException, ConfigurationException {
        user = new User();
        JsonPath jsonResponse = user.createAgent("Wen Mertz","hoyt.gutkowski@yahoo.com","1234","01772617680","1100154450","Agent");
        System.out.println(jsonResponse.get().toString());

        String message = jsonResponse.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("User already exists"));
    }


    @Test(priority = 8, description = "calling API for creating new Agent")
    public void createAgent() throws IOException, ConfigurationException {
        user = new User();
        Utils utils = new Utils();
        utils.generateRandomUser();
        JsonPath jsonResponse = user.createAgent(utils.getName(),utils.getEmail(),"1234",utils.generatePhoneNumber(),"1100154450","Agent");
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);
//        String name = jsonResponse.get("user.name");
//        System.out.println(name);
//        String email = jsonResponse.get("user.email");
//        System.out.println(email);
//        String phone = jsonResponse.get("user.phone_number");
//        System.out.println(phone);
//        String nid = jsonResponse.get("user.nid");
//        System.out.println(nid);

        Utils.setEnvVariable("Agent_id",jsonResponse.get("user.id").toString());
        Utils.setEnvVariable("Agent_name",jsonResponse.get("user.name"));
        Utils.setEnvVariable("Agent_email",jsonResponse.get("user.email"));
        Utils.setEnvVariable("Agent_phone",jsonResponse.get("user.phone_number"));

        Assert.assertTrue(message.contains("User created"));

    }


    @Test(priority = 9, description = "calling API for searching customer by invalid phone number")
    public void searchCustomerByInValidPhoneNumber() throws IOException {
        user = new User();
        JsonPath jsonResponse = user.searchCustomerByPhoneNumber("01700211619");
        String message = jsonResponse.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("User not found"));

    }


    @Test(priority = 10, description = "calling API for searching customer by valid phone number")
    public void searchCustomerByValidPhoneNumber() throws IOException {
        user = new User();
        JsonPath jsonResponse = user.searchCustomerByPhoneNumber(prop.getProperty("Customer_phone1"));
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);
       Assert.assertTrue(message.contains("User found"));

    }





}
