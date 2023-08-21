# 🎮 API Game Trivia 

API Game Trivia is a robust backend service designed to power the Game Trivia application. Developed with Spring Boot, it provides a flexible and scalable solution for trivia game enthusiasts.

## 📌 Table of Contents

- [Structure](#structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the Application](#running-the-application)
  - [Setting up in IntelliJ](#setting-up-in-intellij)
- [Testing](#testing)
  - [Integration Tests](#integration-tests)
- [Observations](#observations)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🏗 Structure

The codebase is organized to facilitate both development and scaling. Here's a brief overview:

### `com.example.trivia`
This is the main package, and it contains several pivotal sub-packages:
(… previous content ...)

## 🚀 Getting Started

### Prerequisites

- Java (Version X.X.X recommended)
- Gradle

### Running the Application

(… previous content ...)

### Setting up in IntelliJ

🔗 **For the frontend repository, visit:** [Frontend Game Trivia on GitHub](https://github.com/PolSurriel/front-game-trivia)

1. Clone the repository to your local machine.
2. Open IntelliJ IDEA.
3. Click on "Open" and select the `api-game-trivia` directory.
4. Once the project is imported, IntelliJ should automatically detect and use the Gradle settings. 
5. Right-click on `TriviaApplication.java` and select `Run` to start the server.

## 🧪 Testing

Our commitment to quality means we place a heavy emphasis on testing.

- **Unit Tests**: Located in the `test` directory. Run as you would any other Java tests.

### Integration Tests

Integration tests are provided as a JSON collection exported from Postman. Import this collection into Postman to execute them.

## 📝 Observations

- **Database Choice**: We've chosen to use an in-memory database (H2) to streamline development. Using DAOs to generate the database is a significant time-saver. This approach is suitable for a technical test, but for scaling to a real product, the database choice would need a re-evaluation.
  
- **Database Design Flaw**: There's an accumulation of records of complete and incomplete games (e.g., from closed tabs). It would be beneficial to either develop an SQL process to remove older records or introduce a batch microservice for this purpose.

- **Project Management**: The project leverages the GitFlow branching model, ensuring structured and efficient management of features, releases, and fixes.

### 🤝 Contributing
We're open to contributions! If you have ideas or improvements, feel free to fork the repository and create a pull request.

### 📜 License
This project is licensed under the MIT License. See the LICENSE.md file for details.

### 📞 Contact
For more details or inquiries, please reach out to psurrielm@gmail.com.
