package com.quizapp.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Custom styled button with smooth hover effect.
 */
public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);
        setBackground(new Color(33, 150, 243));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(true);
        setContentAreaFilled(true);
        setPreferredSize(new Dimension(140, 40));
        initHoverEffect();
    }

    private void initHoverEffect() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(30, 136, 229));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(33, 150, 243));
            }
        });
    }
}
