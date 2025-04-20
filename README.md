# 🧪 Selenium Automation Project - POM based

This is a structured Selenium WebDriver project using **Java, Maven, and TestNG** with the Page Object Model (POM) design pattern. It automates end-to-end scenarios on the demo site [rahulshettyacademy.com](https://rahulshettyacademy.com/client).

## 🧩 Features

- Login and authentication flow
- Product search and add-to-cart
- Checkout and order confirmation
- Invalid login error testing
- Reusable utility methods

## 🛠 Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- ChromeDriver

## 🗂️ Project Structure
```plaintext
src/
├── main/
│   └── java/
│       └── com.automation.selenium_automation/
│           ├── pages/             # Page Object classes
│           │   ├── CartPage.java
│           │   ├── CheckoutPage.java
│           │   ├── ConfirmationPage.java
│           │   ├── HomePage.java
│           │   ├── LoginPage.java
│           │   └── OrdersPage.java
│           ├── resources/         # browser property to run tests on maven
│           │   └── GlobalData.properties
│           └── utils/             # Helper functions for Page Object
│               └── Utils.java
└── test/
    └── java/
        └── com.automation.selenium_automation/
            ├── data/
            │   └── info.json      # Data to run test cases 
            ├── helpers/           # Helper functions for test cases
            │   ├── Base.java
            │   └── Listeners.java
            └── tests/             # Main test cases
                ├── E2eTestTestNG.java
                └── ErrorValidations.java
```



## 🚀 How to Run

1. Clone the repository:
    ```bash
   git clone https://github.com/seongbaelee/AutomationProject1.git
2. Change to the project directory:
    ```bash
    cd AutomationProject1/selenium-automation  
3. Install dependencies (optional if already working):
    ```bash
   mvn clean install
4. Run tests:<br>
   Run Regression tests on Chrome
    ```bash
   mvn test -PRegression -Dbrowser=Chrome
    ```
   Run Error Validation tests on Chrome Headless
   ```bash
   mvn test -PErrorValidation -Dbrowser=Chromeheadless
    ```
   Run Error Validation tests on Firefox
   ```bash
   mvn test -PErrorValidation -Dbrowser=Firefox
    ```
   Run Error Validation tests on Edge
   ```bash
   mvn test -PErrorValidation -Dbrowser=Edge
    ```

⚙️ Prerequisites
Java 17

Maven

Chrome browser & ChromeDriver

Test account at rahulshettyacademy.com

## 💡 Notes

- Test user account required to log in (create one manually before running).
- ChromeDriver version must match your browser.

## 📷 Screenshots (optional)
_Add test run screenshots here if available._

---
