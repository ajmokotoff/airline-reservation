/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneLayout;
import javax.swing.SpinnerDateModel;
import search.ErrorResult;
import search.FlightPlan;
import search.Result;
import search.SearchResult;
import search.Searcher;

/**
 * MainJFrame class
 * <p>
 * JFrame window that is provided to the user to interface with the reservation system.
 * </p>
 *
 * @author Mike
 */
public class MainJFrame extends javax.swing.JFrame {

    private enum TravelState {

        ONE_WAY,
        ROUND_TRIP
    }

    private enum SeatingClassState {

        COACH,
        FIRST_CLASS
    }

    private enum SortByState {

        PRICE,
        TIME
    }

    /**
     * Creates new form MainJFrame
     */
    private final Calendar calendar;
    private static FlightSearchPanel flightSearchPanel;

    private static TravelState currentTravelState;
    private static SeatingClassState currentClassState;
    private static SortByState currentSortByState;

    private final Searcher searcher;
    private static SearchResult latestSearchResult;

    JMenuBar menuBar;
    JCheckBoxMenuItem priceMenuItem;
    JCheckBoxMenuItem timeMenuItem;

    public MainJFrame() {

        currentTravelState = TravelState.ONE_WAY;
        currentClassState = SeatingClassState.COACH;
        currentSortByState = SortByState.PRICE;

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        initComponents();

        flightSearchPanel = new FlightSearchPanel();
        jScrollPane.setLayout(new ScrollPaneLayout());
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        jScrollPane.setViewportView(flightSearchPanel);

        searcher = new Searcher();

        toggleRoundTrip(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu sortByMenu = new JMenu("Sort By");

        priceMenuItem = new JCheckBoxMenuItem(new AbstractAction("Price") {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceMenuItem.setSelected(true);
                timeMenuItem.setSelected(false);
                currentSortByState = SortByState.PRICE;
                displaySearchResult();
            }
        });
        priceMenuItem.setSelected(true);

        timeMenuItem = new JCheckBoxMenuItem(new AbstractAction("Time") {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceMenuItem.setSelected(false);
                timeMenuItem.setSelected(true);
                currentSortByState = SortByState.TIME;
                displaySearchResult();
            }
        });

        sortByMenu.add(priceMenuItem);
        sortByMenu.add(timeMenuItem);

        menuBar.add(sortByMenu);

        jMenuBarPanel.setLayout(new BorderLayout());
        jMenuBarPanel.add(menuBar, BorderLayout.NORTH);

    }

    // Toggles Travel State of Round Trip depending on bool input
    private void toggleRoundTrip(boolean bool) {
        currentTravelState = bool ? TravelState.ROUND_TRIP : TravelState.ONE_WAY;
        toggleButtonOneWay.setSelected(!bool);
        toggleButtonRoundTrip.setSelected(bool);
        returnDateChooser.setVisible(bool);
        timeReturnStart.setVisible(bool);
        timeReturnEnd.setVisible(bool);
        jTextField2.setVisible(bool);
    }

    // Toggles Class State of Coach depending on bool input
    private void toggleCoach(boolean bool) {
        currentClassState = bool ? SeatingClassState.COACH : SeatingClassState.FIRST_CLASS;
        toggleButtonFirstClassSeating.setSelected(!bool);
        toggleButtonCoachSeating.setSelected(bool);
    }

    // Displays search results in flightSearchPanel depending on current Travel State and Class State
    public static void displaySearchResult() {
        if (latestSearchResult != null) {

            if (currentSortByState.equals(SortByState.PRICE)) {
                if (currentClassState.equals(SeatingClassState.COACH)) {
                    latestSearchResult.sortByCoachPrice();

                } else {
                    latestSearchResult.sortByFirstClassPrice();
                }
            } else {
                if (currentClassState.equals(SeatingClassState.COACH)) {
                    latestSearchResult.sortByCoachTime();
                } else {
                    latestSearchResult.sortByFirstClassTime();
                }
            }

            flightSearchPanel.updateFlightResults(latestSearchResult);
            flightSearchPanel.displayFlightResults();

            if (currentClassState.equals(SeatingClassState.COACH)) {
                flightSearchPanel.displayCoachPrices();
            } else {
                flightSearchPanel.displayFirstClassPrices();
            }

            jScrollPane.setViewportView(flightSearchPanel);
        }
    }
    
    public static void displayExpandedFlightPlan(FlightPlan flightPlan)
    {
        if (currentClassState.equals(SeatingClassState.COACH)) {
            flightPlan.setAllCoachSeating();
        } else {
            flightPlan.setAllFirstClassSeating();
        }
        flightSearchPanel.displayExpandedFlight(flightPlan);
        jScrollPane.setViewportView(flightSearchPanel);
    }

    // Display error text in flightSearchPanel
    public static void displayError(String errorStr) {
        flightSearchPanel.displayText("Error: " + errorStr);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toggleButtonOneWay = new javax.swing.JToggleButton();
        toggleButtonRoundTrip = new javax.swing.JToggleButton();
        toggleButtonCoachSeating = new javax.swing.JToggleButton();
        toggleButtonFirstClassSeating = new javax.swing.JToggleButton();
        textFieldDepartureLocation = new JGhostTextField("Departure Location");
        textFieldArrivalLocation = new JGhostTextField("Arrivial Location");
        departureDateChooser = new com.toedter.calendar.JDateChooser();
        returnDateChooser = new com.toedter.calendar.JDateChooser();
        timeDepartStart = new javax.swing.JSpinner();
        jTextField1 = new javax.swing.JTextField();
        timeDepartEnd = new javax.swing.JSpinner();
        jPanel1 = new TimeInputPanel();
        timeReturnStart = new javax.swing.JSpinner();
        jTextField2 = new javax.swing.JTextField();
        timeReturnEnd = new javax.swing.JSpinner();
        jScrollPane = new javax.swing.JScrollPane();
        searchButton = new javax.swing.JButton();
        jMenuBarPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        toggleButtonOneWay.setSelected(true);
        toggleButtonOneWay.setText("One-Way");
        toggleButtonOneWay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonOneWayActionPerformed(evt);
            }
        });

        toggleButtonRoundTrip.setText("Round-Trip");
        toggleButtonRoundTrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonRoundTripActionPerformed(evt);
            }
        });

        toggleButtonCoachSeating.setSelected(true);
        toggleButtonCoachSeating.setText("Coach");
        toggleButtonCoachSeating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonCoachSeatingActionPerformed(evt);
            }
        });

        toggleButtonFirstClassSeating.setText("First Class");
        toggleButtonFirstClassSeating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonFirstClassSeatingActionPerformed(evt);
            }
        });

        textFieldDepartureLocation.setToolTipText("");
        textFieldDepartureLocation.setMinimumSize(new java.awt.Dimension(170, 20));
        textFieldDepartureLocation.setPreferredSize(new java.awt.Dimension(170, 20));

        textFieldArrivalLocation.setMinimumSize(new java.awt.Dimension(170, 20));
        textFieldArrivalLocation.setPreferredSize(new java.awt.Dimension(170, 20));

        timeDepartStart.setModel(new SpinnerDateModel(calendar.getTime(), null, null, Calendar.MINUTE));
        timeDepartStart.setEditor(new JSpinner.DateEditor(timeDepartStart, "hh:mm a"));
        timeDepartStart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timeDepartStart.setFocusable(false);

        jTextField1.setEditable(false);
        jTextField1.setText("-");
        jTextField1.setFocusable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        timeDepartEnd.setModel(new SpinnerDateModel(calendar.getTime(), null, null, Calendar.MINUTE));
        timeDepartEnd.setEditor(new JSpinner.DateEditor(timeDepartEnd, "hh:mm a"));
        timeDepartEnd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timeDepartEnd.setFocusable(false);

        jPanel1.setVisible(true);

        timeReturnStart.setModel(new SpinnerDateModel(calendar.getTime(), null, null, Calendar.MINUTE));
        timeReturnStart.setEditor(new JSpinner.DateEditor(timeReturnStart, "hh:mm a"));
        timeReturnStart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timeReturnStart.setFocusable(false);

        jTextField2.setEditable(false);
        jTextField2.setText("-");
        jTextField2.setFocusable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        timeReturnEnd.setModel(new SpinnerDateModel(calendar.getTime(), null, null, Calendar.MINUTE));
        timeReturnEnd.setEditor(new JSpinner.DateEditor(timeReturnEnd, "hh:mm a"));
        timeReturnEnd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timeReturnEnd.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(timeReturnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeReturnEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeReturnStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeReturnEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane.setHorizontalScrollBar(null);

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jMenuBarPanelLayout = new javax.swing.GroupLayout(jMenuBarPanel);
        jMenuBarPanel.setLayout(jMenuBarPanelLayout);
        jMenuBarPanelLayout.setHorizontalGroup(
            jMenuBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jMenuBarPanelLayout.setVerticalGroup(
            jMenuBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(searchButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(departureDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(toggleButtonOneWay)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(toggleButtonRoundTrip))
                                        .addComponent(textFieldDepartureLocation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(timeDepartStart, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(timeDepartEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(toggleButtonCoachSeating)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(toggleButtonFirstClassSeating))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(returnDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textFieldArrivalLocation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jMenuBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toggleButtonOneWay)
                    .addComponent(toggleButtonRoundTrip)
                    .addComponent(toggleButtonCoachSeating)
                    .addComponent(toggleButtonFirstClassSeating))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldDepartureLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldArrivalLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(departureDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(timeDepartStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(timeDepartEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMenuBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void toggleButtonOneWayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonOneWayActionPerformed
        toggleRoundTrip(false);
    }//GEN-LAST:event_toggleButtonOneWayActionPerformed

    private void toggleButtonCoachSeatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonCoachSeatingActionPerformed
        toggleCoach(true);
        displaySearchResult();
    }//GEN-LAST:event_toggleButtonCoachSeatingActionPerformed

    private void toggleButtonRoundTripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonRoundTripActionPerformed
        toggleRoundTrip(true);
    }//GEN-LAST:event_toggleButtonRoundTripActionPerformed

    private void toggleButtonFirstClassSeatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonFirstClassSeatingActionPerformed
        toggleCoach(false);
        displaySearchResult();
    }//GEN-LAST:event_toggleButtonFirstClassSeatingActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    // On Search button press 
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String depCode = textFieldDepartureLocation.getText();
        String arrCode = textFieldArrivalLocation.getText();

        Result result;

        // If one-way
        if (currentTravelState.equals(TravelState.ONE_WAY)) {
            result = searcher.searchOneWayTrip(depCode, arrCode, departureDateChooser.getDate());
        } else { // Round trip
            result = searcher.searchRoundTrip(depCode, arrCode, departureDateChooser.getDate(), returnDateChooser.getDate());
        }

        // If result is valid
        if (result != null) {

            // If Search Result
            if (result instanceof SearchResult) {

                latestSearchResult = (SearchResult) result;
                displaySearchResult();

            } // Else if error
            else if (result instanceof ErrorResult) {

                displayError(((ErrorResult) result).getError());

            }
        } else {
            displayError("An unknown error has occurred!");
        }
    }//GEN-LAST:event_searchButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser departureDateChooser;
    private javax.swing.JPanel jMenuBarPanel;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private com.toedter.calendar.JDateChooser returnDateChooser;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField textFieldArrivalLocation;
    private javax.swing.JTextField textFieldDepartureLocation;
    private javax.swing.JSpinner timeDepartEnd;
    private javax.swing.JSpinner timeDepartStart;
    private javax.swing.JSpinner timeReturnEnd;
    private javax.swing.JSpinner timeReturnStart;
    private javax.swing.JToggleButton toggleButtonCoachSeating;
    private javax.swing.JToggleButton toggleButtonFirstClassSeating;
    private javax.swing.JToggleButton toggleButtonOneWay;
    private javax.swing.JToggleButton toggleButtonRoundTrip;
    // End of variables declaration//GEN-END:variables
}
