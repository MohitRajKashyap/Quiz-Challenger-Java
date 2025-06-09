package com.quizapp.ui.panels;

import com.quizapp.model.Question;
import com.quizapp.model.UserAnswer;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel to display the quiz results.
 */
public class ResultPanel extends JPanel {
    private JLabel resultLabel;

    public ResultPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        resultLabel.setForeground(new Color(33, 150, 243));
        add(resultLabel, BorderLayout.CENTER);
    }

    /**
     * Show the score and detailed results.
     * @param score Number of correct answers.
     * @param total Total number of questions.
     * @param questions List of questions in the quiz.
     * @param userAnswers List of user's answers.
     */
    public void displayResult(int score, int total, List<Question> questions, List<UserAnswer> userAnswers) {
        if (questions == null || userAnswers == null) {
            resultLabel.setText("Error: No results available");
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format("<html><body>Quiz complete! Your score: %d out of %d<br><br>", score, total));
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            UserAnswer ua = userAnswers.stream()
                    .filter(a -> a.getQuestionId() == q.getId())
                    .findFirst()
                    .orElse(null);
            String correct = q.getCorrectOptionIndices().stream()
                    .map(idx -> q.getOptions().get(idx))
                    .collect(Collectors.joining(", "));
            String user = ua != null ? ua.getSelectedOptionIndices().stream()
                    .map(idx -> q.getOptions().get(idx))
                    .collect(Collectors.joining(", ")) : "None";
            result.append(String.format("Q%d: %s<br>Correct: %s<br>Your Answer: %s<br><br>",
                    i + 1, q.getQuestionText(), correct, user));
        }
        result.append("</body></html>");
        resultLabel.setText(result.toString());
    }
}