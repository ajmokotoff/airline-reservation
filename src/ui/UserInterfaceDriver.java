/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Mike
 */
public class UserInterfaceDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JFrame window = new MainJFrame();

        JFrame window = new MainJFrame();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);



    }

}
