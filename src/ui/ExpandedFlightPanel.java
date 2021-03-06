/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import client.Airports;
import client.Flight;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.TimeZone;
import javax.swing.JPanel;
import search.FlightPlan;
import search.FlightPlanOneWay;
import search.FlightPlanRoundTrip;

/**
 *
 * @author Mike
 */
public class ExpandedFlightPanel extends JPanel {

    FlightPlan flightPlan;

    /**
     * Creates new form ExpandedFlightPanel
     */
    public ExpandedFlightPanel() {
        super();
        initComponents();

        flightItemListPanel.setLayout(new GridLayout(0, 1));
    }

    public void updateFlightPlan(FlightPlan flightPlan) {
        flightItemListPanel.removeAll();
        this.flightPlan = flightPlan;

        int numFlights;

        if (flightPlan instanceof FlightPlanOneWay) {
            addFlightPlanOneWay((FlightPlanOneWay) flightPlan, null);
            numFlights = 1 + ((FlightPlanOneWay) flightPlan).getNumberOfTransfers();
        } else {
            FlightPlanOneWay departingFlightPlan = ((FlightPlanRoundTrip) flightPlan).getDepartingFlightPlan();
            TimeZone tz = Airports.get().get(departingFlightPlan.getFlightList().get(0).getDepCode()).getTimezone();
            
            addFlightPlanOneWay(((FlightPlanRoundTrip) flightPlan).getDepartingFlightPlan(), tz);
            addFlightPlanOneWay(((FlightPlanRoundTrip) flightPlan).getReturningFlightPlan(), tz);

            numFlights = 2;
            numFlights += ((FlightPlanRoundTrip) flightPlan).getDepartingFlightPlan().getNumberOfTransfers();
            numFlights += ((FlightPlanRoundTrip) flightPlan).getReturningFlightPlan().getNumberOfTransfers();
        }
        
        this.setPreferredSize(new Dimension(322, 120 + 54*numFlights));

        priceTextField.setText("$" + String.format("%.2f", flightPlan.getPrice()));
        numFlightsTextField.setText(String.valueOf(numFlights) + " flight" + (numFlights == 1 ? "" : "s"));
        travelTimeTextField.setText("~" + flightPlan.getTravelTime() / (1000 * 60 * 60) + "h travel time");
    }

    private void addFlightPlanOneWay(FlightPlanOneWay flightPlan, TimeZone tz) {

        if (tz == null) {
            tz = Airports.get().get(flightPlan.getFlightList().get(0).getDepCode()).getTimezone();
        }
        
        for (Flight flight : flightPlan.getFlightList()) {
            ExpandedFlightItem item = new ExpandedFlightItem(flight, flightPlan.coachSeatingSelected(flight), tz);

            JPanel wrappingPanel = new JPanel();
            wrappingPanel.setLayout(new BorderLayout());
            wrappingPanel.add(item, BorderLayout.NORTH);
            flightItemListPanel.add(wrappingPanel);
            System.out.println(flight.getDepTime().toString() + " - " + flight.getArrTime().toString());
        }

        flightItemListPanel.revalidate();
    }

    public void updateSeatingChoice(Flight flight, boolean coachSeatingSelected) {
        flightPlan.setSeating(flight, coachSeatingSelected);
        priceTextField.setText("$" + String.format("%.2f", flightPlan.getPrice()));
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        priceTextField = new javax.swing.JFormattedTextField();
        numFlightsTextField = new javax.swing.JTextField();
        travelTimeTextField = new javax.swing.JTextField();
        jButtonReserve = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        flightItemListPanel = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(322, 32767));
        setPreferredSize(new java.awt.Dimension(322, 300));

        priceTextField.setEditable(false);
        priceTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0"))));
        priceTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        priceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextFieldActionPerformed(evt);
            }
        });

        numFlightsTextField.setEditable(false);
        numFlightsTextField.setText("# flights");
        numFlightsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numFlightsTextFieldActionPerformed(evt);
            }
        });

        travelTimeTextField.setEditable(false);
        travelTimeTextField.setText("travel time");

        jButtonReserve.setText("Reserve Flight");
        jButtonReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReserveActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout flightItemListPanelLayout = new javax.swing.GroupLayout(flightItemListPanel);
        flightItemListPanel.setLayout(flightItemListPanelLayout);
        flightItemListPanelLayout.setHorizontalGroup(
            flightItemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        flightItemListPanelLayout.setVerticalGroup(
            flightItemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(flightItemListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numFlightsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(travelTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonReserve, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numFlightsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(travelTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flightItemListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonReserve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void priceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextFieldActionPerformed

    private void numFlightsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numFlightsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numFlightsTextFieldActionPerformed

    private void jButtonReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReserveActionPerformed
        MainJFrame.reserveFlightPlan(flightPlan);
    }//GEN-LAST:event_jButtonReserveActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        MainJFrame.displaySearchResult();
    }//GEN-LAST:event_jButtonCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel flightItemListPanel;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonReserve;
    private javax.swing.JTextField numFlightsTextField;
    private javax.swing.JFormattedTextField priceTextField;
    private javax.swing.JTextField travelTimeTextField;
    // End of variables declaration//GEN-END:variables
}
