/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import client.Airports;
import client.Flight;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import search.FlightPlan;
import search.FlightPlanOneWay;
import search.FlightPlanRoundTrip;

/**
 *
 * @author Mike
 */
public class FlightPanel extends javax.swing.JPanel {

    private static SimpleDateFormat dateFormat;

    private FlightPlan flightPlan;

    /**
     * Creates new form FlightPanel
     *
     * @param flightPlan
     * @param mouseAdapter
     */
    public FlightPanel(FlightPlan flightPlan, MouseAdapter mouseAdapter) {
        // Need to have access to Flight parameters

        this.flightPlan = flightPlan;

        initComponents();

        dateFormat = new SimpleDateFormat("hh:mm a");
        
        if (flightPlan instanceof FlightPlanOneWay) {
            FlightPlanOneWay oneWay = (FlightPlanOneWay) flightPlan;
            Flight departureFlight = oneWay.getFlightList().get(0);
            dateFormat.setTimeZone(Airports.get().get(departureFlight.getDepCode()).getTimezone());
            updatePanel(departureFlight, departureFlight.getFlightNo(), oneWay.getCoachPrice(), oneWay.getNumberOfTransfers(), departureFlight.getDepTime());
        } else if (flightPlan instanceof FlightPlanRoundTrip) {
            FlightPlanRoundTrip roundTrip = (FlightPlanRoundTrip) flightPlan;
            Flight departureFlight = roundTrip.getDepartingFlightPlan().getFlightList().get(0);
            dateFormat.setTimeZone(Airports.get().get(departureFlight.getDepCode()).getTimezone());
            updatePanel(departureFlight, departureFlight.getFlightNo(), roundTrip.getCoachPrice(), roundTrip.getDepartingFlightPlan().getNumberOfTransfers(), departureFlight.getDepTime());
        }

        //FlightPanelMouseAdapter mouseAdapter = new FlightPanelMouseAdapter();
        
        this.addMouseListener(mouseAdapter);
        flightNumberTextField.addMouseListener(mouseAdapter);
        costTextField.addMouseListener(mouseAdapter);
        numTransfersTextField.addMouseListener(mouseAdapter);
        departTimeTextField.addMouseListener(mouseAdapter);
    }

    protected FlightPanel() {
    }

    protected final void updatePanel(Flight flight, String flightNumber, double price, int numTransfers, Date departTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(departTime);
        cal.setTimeZone(Airports.get().get(flight.getDepCode()).getTimezone());
        
        flightNumberTextField.setText("#" + flightNumber);
        costTextField.setText("$" + String.format("%.2f", price));
        numTransfersTextField.setText(getNumTransfersString(numTransfers));
        departTimeTextField.setText(dateFormat.format(cal.getTime()));
    }

    public void displayCoachPrice() {
        costTextField.setText("$" + String.format("%.2f", flightPlan.getCoachPrice()));
    }

    public void displayFirstClassPrice() {
        costTextField.setText("$" + String.format("%.2f", flightPlan.getFirstClassPrice()));
    }
    
    public FlightPlan getFlightPlan()
    {
        return flightPlan;
    }

    private String getNumTransfersString(int numTransfers) {
        switch (numTransfers) {
            case 0:
                return "Nonstop";
            case 1:
                return "1 transfer";
            default:
                return Integer.toString(numTransfers) + " transfers";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flightNumberTextField = new javax.swing.JTextField();
        numTransfersTextField = new javax.swing.JTextField();
        departTimeTextField = new javax.swing.JTextField();
        costTextField = new javax.swing.JFormattedTextField();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMaximumSize(new java.awt.Dimension(322, 54));

        flightNumberTextField.setEditable(false);
        flightNumberTextField.setText("flight #");
        flightNumberTextField.setFocusable(false);
        flightNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flightNumberTextFieldActionPerformed(evt);
            }
        });

        numTransfersTextField.setEditable(false);
        numTransfersTextField.setText("# transfers");
        numTransfersTextField.setFocusable(false);
        numTransfersTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numTransfersTextFieldActionPerformed(evt);
            }
        });

        departTimeTextField.setEditable(false);
        departTimeTextField.setText("depart time");
        departTimeTextField.setFocusable(false);

        costTextField.setEditable(false);
        costTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0"))));
        costTextField.setFocusable(false);
        costTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        costTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(costTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numTransfersTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(departTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flightNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(costTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flightNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numTransfersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void flightNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flightNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flightNumberTextFieldActionPerformed

    private void numTransfersTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numTransfersTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numTransfersTextFieldActionPerformed

    private void costTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField costTextField;
    private javax.swing.JTextField departTimeTextField;
    private javax.swing.JTextField flightNumberTextField;
    private javax.swing.JTextField numTransfersTextField;
    // End of variables declaration//GEN-END:variables
}
