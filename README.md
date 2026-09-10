# CURA Healthcare Appointment Testing

## 📌 Project Overview

This project focuses on **testing the appointment booking functionality** of the CURA Healthcare Service application.

The objective is to verify that users can successfully book an appointment and that the application handles different input and validation scenarios correctly.

## 🌐 Application Under Test

**CURA Healthcare Service**

https://katalon-demo-cura.herokuapp.com/

## 🧪 Testing Scope

The following scenarios are covered:

* Login with valid credentials
* Login with invalid credentials
* Navigate to the appointment page
* Select healthcare facility
* Select hospital readmission option
* Select healthcare program
* Select appointment date
* Enter appointment comment
* Book an appointment with valid information
* Verify appointment confirmation
* Validate required fields
* Logout functionality

## 🛠️ Tools & Technologies

* Selenium WebDriver
* Java
* TestNG
* Maven
* Git
* GitHub
* Page Object Model (POM)

## 🔐 Demo Credentials

```text
Username: John Doe
Password: ThisIsNotAPassword
```

## 📂 Project Structure

```text
CURA-Appointment-Testing/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │
│   └── test/
│       └── java/
│           └── tests/
│
├── pom.xml
├── testng.xml
└── README.md
```

## 📋 Test Scenarios

| Test Case | Scenario                        | Expected Result                     |
| --------- | ------------------------------- | ----------------------------------- |
| TC01      | Login with valid credentials    | User successfully logs in           |
| TC02      | Login with invalid credentials  | Error message is displayed          |
| TC03      | Navigate to Make Appointment    | Appointment page is displayed       |
| TC04      | Select healthcare facility      | Facility is selected                |
| TC05      | Select hospital readmission     | Option is selected                  |
| TC06      | Select healthcare program       | Program is selected                 |
| TC07      | Select appointment date         | Date is selected                    |
| TC08      | Enter appointment comment       | Comment is accepted                 |
| TC09      | Book appointment                | Appointment is successfully created |
| TC10      | Verify appointment confirmation | Confirmation details are displayed  |
| TC11      | Logout                          | User is successfully logged out     |

## ▶️ How to Run

### Clone the Repository

```bash
git clone <my-github-repository-url>
```

### Install Dependencies

```bash
mvn clean install
```

### Run Tests

```bash
mvn test
```

## 🎯 Objective

The goal of this project is to demonstrate **web automation testing skills** by automating the CURA Healthcare appointment workflow and validating both positive and negative test scenarios.

## 👨‍💻 Author

**Ridmi Sansala**

GitHub: <your-github-profile-url>
