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