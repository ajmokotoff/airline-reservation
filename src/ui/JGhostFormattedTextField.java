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
import javax.swing.JFormattedTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 *
 */
public class JGhostFormattedTextField extends JFormattedTextField {

    String ghostText_;
    static Font defaultFont_;
    static Font ghostFont_;
    String inputText_;
    String displayText_;

    public JGhostFormattedTextField() {

    }

    public JGhostFormattedTextField(String ghostText) {
        this.ghostText_ = ghostText;

        defaultFont_ = new Font("Tahoma", Font.PLAIN, 11);
        ghostFont_ = new Font("Tahoma", Font.ITALIC, 12);

        inputText_ = "";
        displayText_ = "";

        setGhostText();

        getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {

            }
        });

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

    private void appendToInput(String str) {
        try {
            int val = Integer.parseInt(str);
            
            // Handle month input
            if(inputText_.length() == 0)
            {
                if(val > 1)
                {
                    inputText_ = inputText_.concat("0");
                }
            }
            
            // Handle day input
            if(inputText_.length() == 2)
            {
                if(val > 3)
                {
                    inputText_ = inputText_.concat("0");
                }
            }
            
            if(inputText_.length() < 6)
            {
                inputText_ = inputText_.concat(Integer.toString(val));
            }
            
            
            
            
        } catch (NumberFormatException e) {
            
        } catch (NullPointerException e) {
            
        }
    }
    
    private void updateDisplayText()
    {
        
    }

}
