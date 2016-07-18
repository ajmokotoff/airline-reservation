/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 *
 */
public class FlightPanelMouseAdapter extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {

            FlightPanel flightPanel;

            if (e.getSource() instanceof FlightPanel) {
                flightPanel = (FlightPanel) e.getSource();
            } else if (e.getComponent().getParent() instanceof FlightPanel) {
                flightPanel = (FlightPanel) e.getComponent().getParent();
            } else {
                return;
            }

            MainJFrame.displayExpandedFlightPlan(flightPanel.getFlightPlan());
        }
    }
}
