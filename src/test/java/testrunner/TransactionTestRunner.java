package testrunner;

import controller.Transaction;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class TransactionTestRunner extends Setup {
    Transaction transaction;
    @Test(priority = 1, description = "calling API for deposit money from system to invalid Agent")
    public void depositToInvalidAgent() throws IOException {
       transaction = new Transaction();
        JsonPath jsonResponse = transaction.depositMoney("SYSTEM","01514214061",5000);
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Account does not exist"));
    }


    @Test(priority = 2, description = "calling API for deposit insufficient balance: 0 TK from System to Agent")
    public void depositToAgentWithInsufficientBalance() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.depositMoney("SYSTEM", prop.getProperty("Agent_phone"), 0);
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Minimum deposit amount 10 tk and maximum deposit amount 10000 tk"));
    }


    @Test(priority = 3, description = "calling API for deposit 5000 TK from System to valid Agent")
    public void depositToValidAgentWithSufficientBalance() throws IOException, ConfigurationException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.depositMoney("SYSTEM", prop.getProperty("Agent_phone"), 5000);
        System.out.println(jsonResponse.get().toString());
        Utils.setEnvVariable("Agent_TrnxId",jsonResponse.get("trnxId").toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Deposit successful"));
    }


    @Test(priority = 4, description = "calling API for deposit money from Agent to invalid customer")
    public void depositToInvalidCustomer() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.depositMoney( prop.getProperty("Agent_phone"),"01821542541",2000);
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Account does not exist"));
    }


    @Test(priority = 5, description = "calling API for deposit insufficient balance: 0 TK from Agent to customerc")
    public void depositToCustomerWithInsufficientBalance() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.depositMoney( prop.getProperty("Agent_phone"), prop.getProperty("Customer_phone1"), 0);
        String message = jsonResponse.get("message");
        System.out.println(message);

       Assert.assertTrue(message.contains("Minimum deposit amount 10 tk and maximum deposit amount 10000 tk"));
    }


    @Test(priority = 6, description = "calling API for deposit 2000 TK from Agent to Customer")
    public void depositToCustomerWithSufficientBalance() throws IOException, ConfigurationException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.depositMoney(prop.getProperty("Agent_phone"), prop.getProperty("Customer_phone1"), 2000);
        System.out.println(jsonResponse.get().toString());
        Utils.setEnvVariable("Customer_TrnxId",jsonResponse.get("trnxId").toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Deposit successful"));
    }



    @Test(priority = 7, description = "Check customer balance with Invalid Phone number")
    public void checkCustomerBalanceWithInvalidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkBalance("01521332342");
        String message = jsonResponse.get("message");
        System.out.println(message);

       Assert.assertTrue(message.contains("User not found"));
    }


    @Test(priority = 8, description = "Check customer balence with valid phone number")
    public void checkCustomerBalanceWithValidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkBalance(prop.getProperty("Customer_phone1"));
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("User balance"));
    }



    @Test(priority = 9, description = "Check agent balance with Invalid phone number")
    public void checkAgentBalanceWithInvalidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkBalance("01759989083");
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("User not found"));
    }


    @Test(priority = 10, description = "Check agent balance with valid phone number")
    public void checkAgentBalanceWithValidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkBalance(prop.getProperty("Agent_phone"));
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("User balance"));
    }



    @Test(priority = 11, description = "calling API for checking statement of user with invalid Transaction ID")
    public void checkStatementByInvalidTransactionId() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.searchByTracnsactionId("TXN130111");
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Transaction not found"));
    }


    @Test(priority = 12, description = "calling API for checking statement by valid Agent Deposit Transaction ID")
    public void checkStatementByvalidAgentDepositTransactionId() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.searchByTracnsactionId(prop.getProperty("Agent_TrnxId"));
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Transaction list"));
    }



    @Test(priority = 13, description = "calling API for checking statement by valid Customer Deposit Transaction ID")
    public void checkStatementByvalidCustomerDepositTransactionId() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.searchByTracnsactionId(prop.getProperty("Customer_TrnxId"));
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Transaction list"));
    }


    @Test(priority = 14, description = "Customer can not withdraw money to invalid agent account number")
    public void customerWithdrawMoneyToInvalidAgentAccount() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.withDrawBalence(prop.getProperty("Customer_phone1"),"01759989083",1000);
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Account does not exist"));
    }


    @Test(priority = 15, description = "Money withdraw by customer with Insufficent balance")
    public void CustomerWithdrawMoneyWithInsufficientBalance() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.withDrawBalence(prop.getProperty("Customer_phone1"), prop.getProperty("Agent_phone"), 0);
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

       Assert.assertTrue(message.contains("Minimum withdraw amount 10 tk"));
    }


    @Test(priority = 16, description = "Money withdraw by customer with sufficent balance")
    public void CustomerWithdrawMoneyWithsufficientBalance() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.withDrawBalence(prop.getProperty("Customer_phone1"), prop.getProperty("Agent_phone"), 1000);
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Withdraw successful"));
    }


    @Test(priority = 17, description = "calling API for sending money from one customer to another invalid customer")
    public void sendToInvalidCustomer() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.sendBalence(prop.getProperty("Customer_phone1"),"01759989083", 500);
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("From/To Account does not exist"));
    }


    @Test(priority = 18, description = "Send money by one customer to another customer with insufficent balance")
    public void sendMoneyOnecutomerToAnothercustoerWithInsufficentBalence() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.sendBalence(prop.getProperty("Customer_phone1"), prop.getProperty("Customer_phone2"), 0);
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Minimum amount 10 tk"));
    }


    @Test(priority = 19, description = "Send money by one customer to another customer with sufficent balance")
    public void sendMoneyOnecutomerToAnothercustoerWithsufficentBalence() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.sendBalence(prop.getProperty("Customer_phone1"), prop.getProperty("Customer_phone2"), 500);
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Send money successful"));
    }


    @Test(priority = 20, description = "Checking Customer statement with Invalid phone number")
    public void checkingCustomerStatementWithInvalidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkStatement("01759989083");
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("User not found"));
    }


    @Test(priority = 21, description = "Checking Customer statement with valid phone number")
    public void checkingCustomerStatementWithValidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkStatement(prop.getProperty("Customer_phone1"));
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Transaction list"));
    }

    @Test(priority = 22, description = "Checking Agent statement with Invalid phone number")
    public void checkingAgentStatementWithInvalidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkStatement("01759989083");
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("User not found"));
    }


    @Test(priority = 23, description = "Checking Customer statement with valid phone number")
    public void checkingAgentStatementWithValidPhoneNumber() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.checkStatement(prop.getProperty("Agent_phone"));
        System.out.println(jsonResponse.get().toString());
        String message = jsonResponse.get("message");
        System.out.println(message);

        Assert.assertTrue(message.contains("Transaction list"));
    }


    @Test(priority = 24, description = "Get Customer with Invalild customer id")
    public void getInvalidCustomer() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.getCustomer("1121144");
        System.out.println(jsonResponse.get().toString());
        String count = jsonResponse.get("count").toString();
        System.out.println(count);

        Assert.assertEquals(count,"0");
    }


    @Test(priority = 25, description = "Get Customer with valild customer id")
    public void getValidCustomer() throws IOException {
        transaction = new Transaction();
        JsonPath jsonResponse = transaction.getCustomer(prop.getProperty("Customer_id1"));
        System.out.println(jsonResponse.get().toString());
        String count = jsonResponse.get("count").toString();
        System.out.println(count);

        Assert.assertEquals(count,"0");
    }
















}
