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
│           ├── base/              # Base test setup
│           │   └── Base.java
│           ├── pages/             # Page Object classes
│           │   ├── App.java
│           │   ├── LoginPage.java
│           │   ├── CartPage.java
│           │   ├── CheckoutPage.java
│           │   └── ConfirmationPage.java
│           └── utils/             # Helper functions
│               └── Utils.java
└── test/
    └── java/
        └── com.automation.selenium_automation/
            ├── tests/             # Main test cases
            │   ├── AppTest.java
            │   ├── e2eTest.java
            │   └── e2eTestTestNG.java
            └── errorTests/        # Negative test scenarios
                └── LoginErrorTest.java
```



## 🚀 How to Run

1. Clone the repository:
    ```bash
   git clone https://github.com/seongbaelee/AutomationProject1.git
2. Install dependencies:
    ```bash
   mvn clean install
3. Run tests:
    ```bash
   mvn test

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
