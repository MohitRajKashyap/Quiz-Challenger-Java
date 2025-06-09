package com.quizapp.util;

/**
 * Provides input validation utilities.
 */
public class ValidationUtil {

    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidOptionCount(int count) {
        // Accept only 4 options for our quiz
        return count == 4;
    }
}
