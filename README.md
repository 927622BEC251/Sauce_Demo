# README.md – SauceDemo Selenium Automation Framework

# SauceDemo – Selenium Java Automation Framework

Automation testing framework developed for the [SauceDemo Website](https://www.saucedemo.com?utm_source=chatgpt.com) using Selenium WebDriver, Java, and TestNG.

---

# Technologies Used

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* ExtentReports

---

# Features

* Page Object Model (POM)
* Explicit Waits
* Screenshot on Failure
* Extent Reports
* Data-Driven Testing
* Config.properties Support
* Retry Mechanism

---

# Modules Covered

* Login
* Products
* Cart
* Checkout
* Logout

---

# Framework Structure

```text id="njlwmq"
pages/        → Page Classes
tests/        → Test Classes
utilities/    → Reusable Methods
listeners/    → Reports & Screenshots
resources/    → Config & Test Data
```

---

# Design Principles Followed

* Readability
* Reusability
* Maintainability
* DRY Principle
* KISS Principle
* Modularity
* Encapsulation
* Scalability

---

# Important Rules Followed

✅ No Thread.sleep()
✅ No Hardcoded Values
✅ POM Strictly Followed
✅ Explicit Wait Used
✅ TestNG @Test Annotation Used

---

# Test Coverage

* Valid Login
* Invalid Login
* Add to Cart
* Remove from Cart
* Checkout Process
* Logout Validation

---

# Run Project

```bash id="j3e2tb"
mvn clean test
```

---

# Reporting

* HTML Extent Report
* Screenshot on Failure
* Pass/Fail Status

