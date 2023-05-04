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

## Test case for this report:

- Google Drive Link: https://docs.google.com/spreadsheets/d/1EeCZVx-rPdSjUAGwF3ykkY0Zo-H8k-i5/edit?usp=sharing&ouid=113777387930637997484&rtpof=true&sd=true

## Allure Report:

![report 1](https://user-images.githubusercontent.com/45961823/236181138-7a616ba7-74cc-4c49-91a7-4d04c72111d6.PNG)

![3](https://user-images.githubusercontent.com/45961823/236181170-01220fca-44f6-4ebc-9a03-7d6d0d2fb4bd.PNG)

![report 2](https://user-images.githubusercontent.com/45961823/236181194-a9e9ab24-ae6d-4c73-880d-df9d79d1f08b.PNG)


## Video Output:

https://user-images.githubusercontent.com/45961823/236181272-c96d8695-3513-4ea8-8212-708aa18f203b.mp4


