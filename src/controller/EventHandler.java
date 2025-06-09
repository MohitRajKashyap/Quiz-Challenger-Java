package com.quizapp.controller;

import com.quizapp.model.UserAnswer;
import com.quizapp.service.QuizService;
import com.quizapp.ui.QuizUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all event interactions between UI and backend.
 */
public class EventHandler {
    private QuizUI quizUI;
    private QuizService quizService;

    private int currentQuestionIndex = 0;
    private final List<UserAnswer> userAnswers = new ArrayList<>();

    public EventHandler(QuizUI quizUI, QuizService quizService) {
        this.quizUI = quizUI;
        this.quizService = quizService;

        loadQuestions();

        initListeners();
    }

    private void loadQuestions() {
        try {
            quizService.loadQuestions();
            quizUI.loadQuestions(quizService.getQuestions());
            showCurrentQuestion();
        } catch (Exception ex) {
            quizUI.showError("Failed to load questions from database. " + ex.getMessage());
        }
    }

    private void initListeners() {
        quizUI.setNextButtonListener(e -> {
            recordAnswerAndAdvance();
        });

        quizUI.setPrevButtonListener(e -> {
            recordAnswerAndGoBack();
        });

        quizUI.setSubmitButtonListener(e -> {
            recordAnswerAndSubmit();
        });

        quizUI.setRestartButtonListener(e -> {
            restartQuiz();
        });
    }

    private void showCurrentQuestion() {
        quizUI.showQuestion(currentQuestionIndex);
        updateNavigationButtons();
    }

    private void recordAnswerAndAdvance() {
        if (!quizUI.validateAtLeastOneOptionSelected()) {
            quizUI.showError("Please select at least one option to continue.");
            return;
        }
        saveCurrentAnswer();

        if (currentQuestionIndex < quizService.getQuestions().size() - 1) {
            currentQuestionIndex++;
            showCurrentQuestion();
        }
    }

    private void recordAnswerAndGoBack() {
        if (currentQuestionIndex > 0) {
            saveCurrentAnswer();
            currentQuestionIndex--;
            showCurrentQuestion();
        }
    }

    private void recordAnswerAndSubmit() {
        if (!quizUI.validateAtLeastOneOptionSelected()) {
            quizUI.showError("Please select at least one option before submitting.");
            return;
        }
        saveCurrentAnswer();
        int score = quizService.scoreQuiz(userAnswers);
        quizUI.showResult(score, quizService.getQuestions().size(), quizService.getQuestions(), userAnswers);
    }

    private void saveCurrentAnswer() {
        int qId = quizService.getQuestions().get(currentQuestionIndex).getId();
        List<Integer> selected = quizUI.getSelectedOptions();
        // Replace existing answer or add
        userAnswers.removeIf(a -> a.getQuestionId() == qId);
        userAnswers.add(new UserAnswer(qId, selected));
    }

    private void updateNavigationButtons() {
        quizUI.setPrevButtonEnabled(currentQuestionIndex > 0);
        quizUI.setNextButtonEnabled(currentQuestionIndex < quizService.getQuestions().size() - 1);
        quizUI.setSubmitButtonEnabled(currentQuestionIndex == quizService.getQuestions().size() - 1);
    }

    private void restartQuiz() {
        currentQuestionIndex = 0;
        userAnswers.clear();
        quizUI.resetUI();
        showCurrentQuestion();
    }
}