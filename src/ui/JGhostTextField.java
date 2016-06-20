/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 *
 */
public class JGhostTextField extends JTextField {

    String ghostText_;
    static Font defaultFont_;
    static Font ghostFont_;

    public JGhostTextField() {

    }

    public JGhostTextField(String ghostText) {
        this.ghostText_ = ghostText;

        defaultFont_ = new Font("Tahoma", Font.PLAIN, 11);
        ghostFont_ = new Font("Tahoma", Font.ITALIC, 12);

        setGhostText();

        addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setGhostText();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(ghostText_)) {
                    clearGhostText();
                }
            }
        });
    }

    private void setGhostText() {
        setText(ghostText_);
        setForeground(Color.GRAY);
        setFont(ghostFont_);
    }

    private void clearGhostText() {
        setText("");
        setForeground(Color.BLACK);
        setFont(defaultFont_);
    }

}
