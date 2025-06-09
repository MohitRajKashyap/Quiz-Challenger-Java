package com.quizapp.model;

import java.util.List;

/**
 * Represents a quiz question with multiple options.
 */
public class Question {
    private int id;
    private String questionText;
    private List<String> options; // List of options (e.g., 4 options)
    private List<Integer> correctOptionIndices; // Indices of the correct option(s)

    public Question() {}

    public Question(int id, String questionText, List<String> options, List<Integer> correctOptionIndices) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndices = correctOptionIndices;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getCorrectOptionIndices() {
        return correctOptionIndices;
    }

    public void setCorrectOptionIndices(List<Integer> correctOptionIndices) {
        this.correctOptionIndices = correctOptionIndices;
    }
}
