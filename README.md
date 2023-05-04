# API-Testing-on-Dmoney-API-with-Rest-Assured

## What is Rest Assured in API Automation?

Rest Assured is an open-source Java library used to test RESTful APIs. It provides a domain-specific language (DSL) for writing API tests in a simple and readable format. Rest Assured supports various HTTP methods such as GET, POST, PUT, DELETE, etc., and allows us to set request headers, query parameters, and request bodies. It also provides easy-to-use assertion methods to validate the response received from the server. Rest Assured is widely used in the industry for API testing due to its simplicity and effectiveness.

## Technology Used:

- Rest Assured
- commons-configuration
- Jackson Databind
- TestNG
- Java
- Gradle
- intellij idea
- Allure

## How to run this project:

- Clone this project
- hit the following command: `gradle clean test`
- for Allure Report hit: `allure generate allure-results --clean -o allure-report` and `allure serve allure-results`

## Project Scenerio:

- Call login API
- Create a new customer and an agent
- Search by the customer phone number
- deposit money from system to invalid Agent"
- Deposit 2000 tk by agent to customer
- Check balance of customer
- Check balance of Agent
- Check Customer statement
- Check Agent statement
- Check statement by trnxId
- Withdraw 1000 tk by customer and assert expected balance

## Test case Report based on Scenerio:

- Google Drive Link: https://docs.google.com/spreadsheets/d/10p-oGjvae8Sym4mF2KTysWvbABNuhPsj/edit?usp=sharing&ouid=102526777056504026911&rtpof=true&sd=true

## Allure Report:

![Screenshot (43)](https://user-images.githubusercontent.com/123433625/224722042-2e4d3176-6b4d-46ce-95fd-f49cdc44508f.png)

![Screenshot (44)](https://user-images.githubusercontent.com/123433625/224722075-446ecbfd-7e43-4aa9-91ec-05e94bf631d4.png)

## Video Output:

https://user-images.githubusercontent.com/123433625/224334272-286fc7dd-dc42-4332-abd7-b6c9ee52e59b.mp4
