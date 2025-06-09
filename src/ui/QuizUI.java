package com.quizapp.ui;

import com.quizapp.model.Question;
import com.quizapp.model.UserAnswer;
import com.quizapp.ui.components.CustomButton;
import com.quizapp.ui.panels.QuestionPanel;
import com.quizapp.ui.panels.ResultPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Main GUI frame for Quiz Challenger.
 */
public class QuizUI extends JFrame {

    private List<Question> questions;
    private QuestionPanel questionPanel;
    private ResultPanel resultPanel;
    private JPanel navigationPanel;
    private CustomButton nextButton, prevButton, submitButton, restartButton;
    private JLabel headingLabel;

    public QuizUI() {
        setTitle("Quiz Challenger");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        initComponents();
    }

    private void initComponents() {
        headingLabel = new JLabel("Quiz Challenger", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headingLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        headingLabel.setForeground(new Color(51, 51, 51));
        add(headingLabel, BorderLayout.NORTH);

        questionPanel = new QuestionPanel();
        add(questionPanel, BorderLayout.CENTER);

        resultPanel = new ResultPanel();
        resultPanel.setVisible(false);
        add(resultPanel, BorderLayout.CENTER);

        navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.WHITE);
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        prevButton = new CustomButton("Previous");
        nextButton = new CustomButton("Next");
        submitButton = new CustomButton("Submit");
        restartButton = new CustomButton("Restart");

        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);
        navigationPanel.add(submitButton);
        navigationPanel.add(restartButton);

        submitButton.setEnabled(false);
        restartButton.setVisible(false);

        add(navigationPanel, BorderLayout.SOUTH);
    }

    // Public methods for the controller

    public void loadQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void showQuestion(int index){
        if (index < 0 || index >= questions.size()) return;
    Question q = questions.get(index);
        questionPanel.setQuestion(q);
        questionPanel.setVisible(true);
        resultPanel.setVisible(false);
        restartButton.setVisible(false);
        headingLabel.setText(String.format("Question %d of %d", index + 1, questions.size()));
}

public void showResult(int score, int total, List<Question> questions, List<UserAnswer> userAnswers) {
    questionPanel.setVisible(false);
    resultPanel.displayResult(score, total, questions, userAnswers);
    resultPanel.setVisible(true);
    navigationPanel.setVisible(false);
    restartButton.setVisible(true);
    headingLabel.setText("Your Result");
}

public void resetUI() {
    navigationPanel.setVisible(true);
    submitButton.setEnabled(false);
    restartButton.setVisible(false);
    questionPanel.clearSelection();
    questionPanel.setVisible(true);
    resultPanel.setVisible(false);
    headingLabel.setText("Quiz Challenger");
}

public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
}

public boolean validateAtLeastOneOptionSelected() {
    return !getSelectedOptions().isEmpty();
}

public List<Integer> getSelectedOptions() {
    return questionPanel.getSelectedOptions();
}

public void setNextButtonEnabled(boolean enabled) {
    nextButton.setEnabled(enabled);
}

public void setPrevButtonEnabled(boolean enabled) {
    prevButton.setEnabled(enabled);
}

public void setSubmitButtonEnabled(boolean enabled) {
    submitButton.setEnabled(enabled);
}

// Action Listener setters for MVC pattern

public void setNextButtonListener(ActionListener listener) {
    nextButton.addActionListener(listener);
}

public void setPrevButtonListener(ActionListener listener) {
    prevButton.addActionListener(listener);
}

public void setSubmitButtonListener(ActionListener listener) {
    submitButton.addActionListener(listener);
}

public void setRestartButtonListener(ActionListener listener) {
    restartButton.addActionListener(listener);
}
}