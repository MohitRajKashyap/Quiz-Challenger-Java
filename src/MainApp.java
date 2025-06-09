package com.quizapp;

import com.quizapp.ui.QuizUI;

import javax.swing.SwingUtilities;

/**
 * Entry point for the Quiz Challenger Application.
 * Launches the GUI on the Event Dispatch Thread.
 */
public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizUI quizUI = new QuizUI();
            quizUI.setVisible(true);
        });
    }
}
