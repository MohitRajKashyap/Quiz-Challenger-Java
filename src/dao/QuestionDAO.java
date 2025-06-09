package com.quizapp.dao;

import com.quizapp.model.Question;
import com.quizapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DAO for Questions - handles DB CRUD operations.
 */
public class QuestionDAO {

    // Insert question
    public boolean insertQuestion(Question question) throws SQLException {
        String sql = "INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_options) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, question.getQuestionText());
            List<String> opts = question.getOptions();
            ps.setString(2, opts.get(0));
            ps.setString(3, opts.get(1));
            ps.setString(4, opts.get(2));
            ps.setString(5, opts.get(3));
            // Store correct options as comma-separated indices string e.g. "0,2"
            ps.setString(6, listToString(question.getCorrectOptionIndices()));
            int res = ps.executeUpdate();
            return res == 1;
        }
    }

    // Retrieve all questions
    public List<Question> getAllQuestions() throws SQLException {
        String sql = "SELECT * FROM questions";
        List<Question> questions = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String questionText = rs.getString("question_text");
                List<String> options = Arrays.asList(
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d")
                );
                List<Integer> correctIndices = stringToList(rs.getString("correct_options"));
                questions.add(new Question(id, questionText, options, correctIndices));
            }
        }
        return questions;
    }

    // Update question by id
    public boolean updateQuestion(Question question) throws SQLException {
        String sql = "UPDATE questions SET question_text=?, option_a=?, option_b=?, option_c=?, option_d=?, correct_options=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, question.getQuestionText());
            List<String> opts = question.getOptions();
            ps.setString(2, opts.get(0));
            ps.setString(3, opts.get(1));
            ps.setString(4, opts.get(2));
            ps.setString(5, opts.get(3));
            ps.setString(6, listToString(question.getCorrectOptionIndices()));
            ps.setInt(7, question.getId());
            int res = ps.executeUpdate();
            return res == 1;
        }
    }

    // Delete question by id
    public boolean deleteQuestion(int id) throws SQLException {
        String sql = "DELETE FROM questions WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int res = ps.executeUpdate();
            return res == 1;
        }
    }

    // Utility: converts List<Integer> to comma-separated string
    private String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i).append(",");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // remove last comma
        return sb.toString();
    }

    // Utility: converts comma-separated string to List<Integer>
    private List<Integer> stringToList(String str) {
        List<Integer> list = new ArrayList<>();
        if (str == null || str.isEmpty()) return list;
        for (String s : str.split(",")) {
            try {
                list.add(Integer.parseInt(s.trim()));
            } catch (NumberFormatException ignored) {}
        }
        return list;
    }
}
