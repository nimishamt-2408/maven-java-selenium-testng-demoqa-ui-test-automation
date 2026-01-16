# DemoQA UI Test Automation Framework

A Maven-based **Selenium WebDriver + TestNG** automation framework for the [DemoQA](https://demoqa.com) website.  
This project provides ready-to-run UI test scripts, reusable page objects, and reporting utilities for end-to-end test automation practice.

---

## 📦 Features

- **Maven Project**: Manage dependencies and build with ease.  
- **Selenium WebDriver**: Automate browser interactions.  
- **TestNG Framework**: Organize and execute tests with suites, groups, and parallel execution.  
- **Page Object Model (POM)**: Clean and maintainable test code structure.  
- **Screenshots & Reports**: Automatic screenshots on failure and HTML test reports.  
- **CI Integration**: Ready for GitHub Actions or other CI/CD pipelines.

---

## 🧰 Project Structure

├── src
│ ├── main/java # Framework utilities and helpers
│ └── test/java # Test scripts
├── screenshots # Captured screenshots of failed tests
├── reports # TestNG HTML reports
├── pom.xml # Maven dependencies
└── testng.xml # TestNG suite configuration


---

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or above  
- Maven 3.x or above  
- Chrome/Firefox/WebDriver binaries (can be managed via WebDriverManager)

### Run Tests

Clone the repo:

```bash
git clone https://github.com/nimishamt-2408/maven-java-selenium-testng-demoqa-ui-test-automation.git
cd maven-java-selenium-testng-demoqa-ui-test-automation

Run all tests using Maven:

mvn clean test
