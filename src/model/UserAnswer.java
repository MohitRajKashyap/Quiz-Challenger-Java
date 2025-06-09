package com.quizapp.model;

import java.util.List;

/**
 * Represents user's answer to a question.
 */
public class UserAnswer {
    private int questionId;
    private List<Integer> selectedOptionIndices;

    public UserAnswer(int questionId, List<Integer> selectedOptionIndices) {
        this.questionId = questionId;
        this.selectedOptionIndices = selectedOptionIndices;
    }

    public int getQuestionId() {
        return questionId;
    }

    public List<Integer> getSelectedOptionIndices() {
        return selectedOptionIndices;
    }
}
