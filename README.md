# Quiz Challenger

## Overview
Quiz Challenger is a Java-based desktop designed to create and take quizzes with a user-friendly graphical interface. It supports multiple-choice questions with single or multiple correct answers, storing questions in a MySQL database and providing file-based backup for questions and user answers. The application is built using Swing for the UI and follows a modular MVC-like architecture.

## Features
- **Interactive Quiz UI**: Navigate through questions, select answers via checkboxes, and view results.
- **Database Integration**: Questions are stored and retrieved from a MySQL database.
- **File Backup**: Questions and user answers can be saved to and loaded from files using serialization.
- **Navigation Controls**: Next, Previous, Submit, and Restart buttons for a seamless quiz experience.
- **Custom UI Components**: Styled buttons with hover effects for improved user experience.
- **Error Handling**: Displays user-friendly error messages for invalid actions (e.g., no option selected).
- **Logging**: Simple console-based logging for debugging and monitoring.
- **Input Validation**: Ensures proper input handling for quiz options and other data.
- **Detailed Results**: Displays a breakdown of correct answers and user selections after quiz completion.
- **User Management**: Stores user credentials and scores in the database (though user authentication is not implemented in the current UI).

## Prerequisites
- **Java**: JDK 8 or higher
- **MySQL**: MySQL Server 5.7 or higher
- **MySQL JDBC Driver**: Ensure `mysql-connector-java` is included in the project dependencies
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE
- **Maven/Gradle** (optional): For dependency management

## Setup Instructions

### 1. Database Setup
1. Install MySQL Server and ensure it's running.
2. Create a database named `quizdb` and set up the schema using the provided `questions.sql` file. Run the following SQL commands (or execute the `questions.sql` file directly in your MySQL client):
   ```sql
   CREATE DATABASE quizdb;
   USE quizdb;

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(100) NOT NULL UNIQUE,
       password VARCHAR(100) NOT NULL,
       score INT DEFAULT 0
   );

   CREATE TABLE questions (
       id INT AUTO_INCREMENT PRIMARY KEY,
       question_text TEXT NOT NULL,
       option_a VARCHAR(255),
       option_b VARCHAR(255),
       option_c VARCHAR(255),
       correct_answer CHAR(1)
   );

   INSERT INTO questions (question_text, option_a, option_b, option_c, correct_answer)
   VALUES
   ("What is the size of an int variable in Java?", "4 bytes", "2 bytes", "8 bytes", "A"),
   ("Which of these is not a Java keyword?", "static", "Boolean", "void", "B"),
   ("What does JVM stand for?", "Java Variable Machine", "Java Virtual Machine", "Java Visual Model", "B"),
   ("Which method is the entry point of a Java program?", "start()", "main()", "init()", "B"),
   ("Which of these is used for inheritance in Java?", "extends", "implements", "inherits", "A"),
   ("What is the default value of a boolean variable in Java?", "true", "false", "null", "B"),
   ("Which of these access modifiers makes a member accessible only within its own class?", "public", "protected", "private", "C"),
   ("Which exception is thrown when an array is accessed with an illegal index?", "NullPointerException", "ArrayIndexOutOfBoundsException", "ClassCastException", "B"),
   ("What keyword is used to create an object in Java?", "new", "create", "instantiate", "A"),
   ("Which of the following is a valid declaration of a Java array?", "int arr[];", "int arr;", "array int arr;", "A");
   ```
   **Note**: The `questions.sql` file includes 10 sample questions for testing the application.
3. Update the database connection details in `DBConnection.java` to match the database name and your credentials:
   ```java
   private static final String URL = "jdbc:mysql://127.0.0.1:3306/quizdb?user=root";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```

### 2. Project Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/quiz-challenger.git
   ```
2. Open the project in your IDE.
3. Add the MySQL JDBC driver to your project:
   - For Maven, add to `pom.xml`:
     ```xml
     <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
         <version>8.0.28</version>
     </dependency>
     ```
   - For Gradle, add to `build.gradle`:
     ```groovy
     implementation 'mysql:mysql-connector-java:8.0.28'
     ```
4. Build and run the project using your IDE or via the command line:
   ```bash
   mvn clean install
   java -cp target/quizapp.jar com.quizapp.MainApp
   ```

### 3. Running the Application
- Run the `MainApp.java` class to launch the application.
- The GUI will open, allowing you to take a quiz, navigate questions, and view results.

## Project Structure
```
QuizChallenger/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── quizapp/
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   └── EventHandler.java
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   └── QuestionDAO.java
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Question.java
│   │   │   │   │   │   └── UserAnswer.java
│   │   │   │   │   ├── service/
│   │   │   │   │   │   └── QuizService.java
│   │   │   │   │   ├── ui/
│   │   │   │   │   │   ├── components/
│   │   │   │   │   │   │   └── CustomButton.java
│   │   │   │   │   │   ├── panels/
│   │   │   │   │   │   │   ├── QuestionPanel.java
│   │   │   │   │   │   │   └── ResultPanel.java
│   │   │   │   │   │   └── QuizUI.java
│   │   │   │   │   ├── util/
│   │   │   │   │   │   ├── DBConnection.java
│   │   │   │   │   │   ├── LoggerUtil.java
│   │   │   │   │   │   ├── ValidationUtil.java
│   │   │   │   │   │   └── FileHandler.java
│   │   │   │   │   └── MainApp.java
│   │   └── resources/
│   │       └── questions.sql
├── .gitignore
├── God.iml
├── pom.xml
└── README.md
```

## Usage
1. **Start the Quiz**: Launch the application to load questions from the database.
2. **Answer Questions**: Select one or more options using checkboxes and navigate using Next/Previous buttons.
3. **Submit Quiz**: On the last question, click Submit to view your score and detailed results.
4. **Restart**: Click Restart to retake the quiz.
5. **Backup**: Questions and answers can be saved to files using `FileHandler` for offline storage.

## Known Issues
- `QuestionPanel.java` has incomplete code (e.g., syntax errors in instantiation of `optionCheckboxes` and missing type parameters in `getSelectedOptions()`). This needs to be fixed for proper functionality.
- The database schema in `questions.sql` (`correct_answer CHAR(1)`) does not match the schema expected by `QuestionDAO.java` (`correct_options VARCHAR(50)` for storing multiple correct answers as comma-separated indices). This mismatch needs to be resolved by either updating the database schema or modifying the application logic.
- The `users` table in the database is not utilized by the current application logic (e.g., no login functionality). Future enhancements could include user authentication.
- The database password in `DBConnection.java` is hardcoded, which is a security risk. Consider using environment variables or a configuration file for sensitive data.

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit (`git commit -m "Add feature"`).
4. Push to your branch (`git push origin feature-branch`).
5. Create a pull request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details (if applicable).
