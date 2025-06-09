package com.quizapp.ui.panels;

import com.quizapp.model.Question;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPanel to render a single quiz question with checkbox options.
 */
public class QuestionPanel extends JPanel {

    private JLabel questionLabel;
    private List<JCheckBox> optionCheckboxes;

    public QuestionPanel() {
        setLayout(new BorderLayout(10, 10));
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        questionLabel.setForeground(new Color(51, 51, 51));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 5, 5));
        optionCheckboxes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            JCheckBox cb = new JCheckBox();
            cb.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            cb.setFocusable(false);
            optionCheckboxes.add(cb);
            optionsPanel.add(cb);
        }

        add(optionsPanel, BorderLayout.CENTER);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
    }

    /**
     * Sets the content for this question panel.
     * @param question The Question object to display.
     */
    public void setQuestion(Question question) {
        questionLabel.setText("<html><body style='width: 600px'>" + question.getQuestionText() + "</body></html>");
        List<String> opts = question.getOptions();
        for (int i = 0; i < 4; i++) {
            JCheckBox cb = optionCheckboxes.get(i);
            cb.setText(opts.get(i));
            cb.setSelected(false);
        }
    }

    /**
     * Returns the list of selected option indices.
     */
    public List<Integer> getSelectedOptions() {
        List<Integer> selected = new ArrayList<>();
        for (int i = 0; i < optionCheckboxes.size(); i++) {
            if (optionCheckboxes.get(i).isSelected()) {
                selected.add(i);
            }
        }
        return selected;
    }

    /**
     * Clears all checkbox selections.
     */
    public void clearSelection() {
        for (JCheckBox cb : optionCheckboxes) {
            cb.setSelected(false);
        }
    }
}
