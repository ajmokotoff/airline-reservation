/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import client.Airports;
import client.Flight;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author Mike
 */
public class ExpandedFlightItem extends javax.swing.JPanel {

    private static SimpleDateFormat dateFormat;
    private final Flight flight;

    /**
     * Creates new form ExpandedFlightItem
     *
     * @param flight
     * @param coachSeatingSelected
     * @param tz
     */
    public ExpandedFlightItem(Flight flight, boolean coachSeatingSelected, TimeZone tz) {
        this.flight = flight;
        initComponents();

        dateFormat = new SimpleDateFormat("hh:mm a");
        dateFormat.setTimeZone(tz);
        flightNumberTextField.setText("#" + flight.getFlightNo());
        flightCodesTextField.setText(flight.getDepCode() + "->" + flight.getArrCode());
        departTimeTextField.setText(dateFormat.format(flight.getDepTime()));
        arriveTimeTextField.setText(dateFormat.format(flight.getArrTime()));

        jComboBox.removeAllItems();

        if (flight.checkCoachLeft() > 0) {
            jComboBox.addItem("Coach");
        }

        if (flight.checkFirstLeft() > 0) {
            jComboBox.addItem("First Class");
        }

        if (coachSeatingSelected) {
            jComboBox.setSelectedItem("Coach");
        } else {
            jComboBox.setSelectedItem("First Class");
        }

        jComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                ExpandedFlightPanel panel = ((ExpandedFlightPanel) getParent().getParent().getParent());
                boolean coachSelected = jComboBox.getSelectedItem().equals("Coach");
                panel.updateSeatingChoice(flight, coachSelected);
            }
        });

    }

    public String getSeatingChoice() {
        return (String) jComboBox.getSelectedItem();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flightNumberTextField = new javax.swing.JTextField();
        departTimeTextField = new javax.swing.JTextField();
        arriveTimeTextField = new javax.swing.JTextField();
        jComboBox = new javax.swing.JComboBox();
        flightCodesTextField = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(322, 54));
        setPreferredSize(new java.awt.Dimension(322, 54));

        flightNumberTextField.setEditable(false);
        flightNumberTextField.setText("flight #");
        flightNumberTextField.setFocusable(false);
        flightNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flightNumberTextFieldActionPerformed(evt);
            }
        });

        departTimeTextField.setEditable(false);
        departTimeTextField.setText("depart time");
        departTimeTextField.setFocusable(false);

        arriveTimeTextField.setEditable(false);
        arriveTimeTextField.setText("arrive time");
        arriveTimeTextField.setFocusable(false);
        arriveTimeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arriveTimeTextFieldActionPerformed(evt);
            }
        });

        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        flightCodesTextField.setEditable(false);
        flightCodesTextField.setText("arrCode");
        flightCodesTextField.setFocusable(false);
        flightCodesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flightCodesTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(flightNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flightCodesTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(departTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arriveTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arriveTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flightCodesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flightNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void flightNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flightNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flightNumberTextFieldActionPerformed

    private void arriveTimeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arriveTimeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arriveTimeTextFieldActionPerformed

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed

        /*
         System.out.println(getParent().getClass().toString());
        
         ExpandedFlightPanel panel = ((ExpandedFlightPanel) getParent());
         boolean coachSelected = jComboBox.getSelectedItem().equals("Coach");
         panel.updateSeatingChoice(flight, coachSelected);
         */
        //((ExpandedFlightPanel) getParent().getParent()).updateSeatingChoice(flight, jComboBox.getSelectedItem().equals("Coach"));
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void flightCodesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flightCodesTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flightCodesTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arriveTimeTextField;
    private javax.swing.JTextField departTimeTextField;
    private javax.swing.JTextField flightCodesTextField;
    private javax.swing.JTextField flightNumberTextField;
    private javax.swing.JComboBox jComboBox;
    // End of variables declaration//GEN-END:variables
}
