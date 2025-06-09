

import com.quizapp.model.Question;
import com.quizapp.model.UserAnswer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file IO backup for quiz questions and answers.
 */
public class FileHandler {

    // Save list of questions to file (simple serialization)
    public static void saveQuestionsToFile(List<Question> questions, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(questions);
        }
    }

    // Load list of questions from file
    @SuppressWarnings("unchecked")
    public static List<Question> loadQuestionsFromFile(File file) throws IOException, ClassNotFoundException {
        List<Question> questions;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            questions = (List<Question>) ois.readObject();
        }
        return questions;
    }

    // Save user answers
    public static void saveUserAnswersToFile(List<UserAnswer> answers, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(answers);
        }
    }

    // Load user answers
    @SuppressWarnings("unchecked")
    public static List<UserAnswer> loadUserAnswersFromFile(File file) throws IOException, ClassNotFoundException {
        List<UserAnswer> answers;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            answers = (List<UserAnswer>) ois.readObject();
        }
        return answers;
    }
}
