package com.quizapp.service;

import com.quizapp.dao.QuestionDAO;
import com.quizapp.model.Question;
import com.quizapp.model.UserAnswer;

import java.sql.SQLException;
import java.util.List;

/**
 * Service layer for Quiz logic.
 */
public class QuizService {
    private QuestionDAO questionDAO;

    private List<Question> questions;

    public QuizService() {
        questionDAO = new QuestionDAO();
    }

    public void loadQuestions() throws SQLException {
        questions = questionDAO.getAllQuestions();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Check user's answers correctness count.
     * Returns number of correctly answered questions.
     */
    public int scoreQuiz(List<UserAnswer> userAnswers) {
        int score = 0;
        for (UserAnswer ua : userAnswers) {
            Question q = findQuestionById(ua.getQuestionId());
            if (q != null && compareAnswers(q.getCorrectOptionIndices(), ua.getSelectedOptionIndices())) {
                score++;
            }
        }
        return score;
    }

    private Question findQuestionById(int id) {
        if (questions == null) return null;
        for (Question q : questions)
            if (q.getId() == id) return q;
        return null;
    }

    private boolean compareAnswers(List<Integer> correct, List<Integer> given) {
        if (correct == null || given == null) return false;
        if (correct.size() != given.size()) return false;
        return correct.containsAll(given) && given.containsAll(correct);
    }
}
