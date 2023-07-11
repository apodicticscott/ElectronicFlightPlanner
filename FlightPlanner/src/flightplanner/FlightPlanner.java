package flightplanner;

/*
 * Flight Planner Final Project: Flight Planner Class
 * Group 1: Sophie Lyon, Caroline Peace, Scott Rostron
 * Program file name: FlightPlanner.java
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * The Flight Planner Class implements:
 * FileReader and FileWriter to create and verify the "airports.txt" & "airplanes.txt" file.
 * BufferedReader to read the "airports.txt" & "airplanes.txt" file.
 * Void methods to fill the DropDowns with data from the above files & a calculation based on
 * the distance between two airports in one airplane.
 */
public class FlightPlanner extends javax.swing.JFrame {
	
    /*
     * The FlightPlaner() method is implemented in the main method & calls all of 
     * the initComponents() and sets the location of the GUI.
     */
    public FlightPlanner() {
        initComponents();
        super.setLocationRelativeTo(null); // Makes GUI appear in the center of the screen.
    }
    File fAirplane = new File("AirplaneDatabase"); // A new file (f), "AirplaneDatabase" is created.
    File fAirport = new File("AirportDatabase"); // A new file (f), "AirportDatabase" is created.
    
    
    /*
     * The createAirportFolder() method will create a directory into
     * the "AirportDatabase" file if one does not already exist.
     */
    void createAirportFolder(){
        if(!fAirport.exists()){
            fAirport.mkdirs();
        }
    }
    
    /*
     * The readAirportFile() method implements FileReader & FileWriter to check
     * for the creation of the "airports.txt" file, if one does not already
     * exist, then an instance of "airports.txt" file is created.
     */
    void readAirportFile(){
        try { // Reads for the existence of the "airports.txt" file.
            FileReader fr = new FileReader(fAirport+"/airports.txt");
            System.out.println("File exists!");
        } catch (FileNotFoundException ex) {
                try { // Will create "airports.txt" file if one doesn't exist.
                FileWriter fw = new FileWriter(fAirport+"/airports.txt");
                System.out.println("File created");
            } catch (IOException ex1) {
                Logger.getLogger(FlightPlanner.class.getName()).log(Level.SEVERE, null, ex1);
            }
            }
        }
    
    /*
     * The fillStartingAirportDropFromDbFile() method will create a file path to the
     * "airports.txt" file and allow a user to select a starting airport for the flight planner.
     */
    void fillStartingAirportDropFromDbFile(){
        
        String filePath = fAirport+"/airports.txt"; // Path of the "airports.txt" file. 
        File file = new File(filePath);
        
        try { // This method fills out the Starting Airport Dropdown from the Airport Database
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            startingDropDown.setModel(new javax.swing.DefaultComboBoxModel(lines));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightPlanner.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /*
     * The fillArrivalAirportDropFromDbFile() method will create a file path to the
     * "airports.txt" file and allow a user to select a destination airport for the flight planner.
     */
    void fillArrivalAirportDropFromDbFile(){
        
        String filePath = fAirport+"/airports.txt"; // Path of the "airports.txt" file. 
        File file = new File(filePath);
        
        try { // This method fills out the Arrival Airport Dropdown from the Airport Database
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            arrivalDropDown.setModel(new javax.swing.DefaultComboBoxModel(lines));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightPlanner.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /*
     * The createAirplaneFolder() method will create a directory into
     * the "AirplaneDatabase" file if one does not already exist.
     */
    void createAirplaneFolder(){
        if(!fAirplane.exists()){
            fAirplane.mkdirs();
        }
    }
    
    /*
     * The readAirplaneFile() method implements FileReader & FileWriter to check
     * for the creation of the "airplanes.txt" file, if one does not already
     * exist, then an instance of "airplaness.txt" file is created.
     */
    void readAirplaneFile(){
        try { // Reads for the existence of the "airplanes.txt" file.
            FileReader fr = new FileReader(fAirplane+"/airplanes.txt");
            System.out.println("File exists!");
        } catch (FileNotFoundException ex) {
            try { // Will create "airplanes.txt" file if one doesn't exist.
                FileWriter fw = new FileWriter(fAirplane+"/airplanes.txt");
                System.out.println("File created");
            } catch (IOException ex1) {
                Logger.getLogger(FlightPlanner.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    /*
     * The fillAirplaneDropFromDbFile() method will create a file path to the
     * "airplanes.txt" file and allow a user to select an airplane to travel
     * in for the flight planner.
     */
    void fillAirplaneDropFromDbFile(){
        
        String filePath = fAirplane+"/airplanes.txt"; // Path of the "airplanes.txt" file. 
        File file = new File(filePath);
        
        try { // This method fills out the Airplane Dropdown from the Airplane Database
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            airplaneDropDown.setModel(new javax.swing.DefaultComboBoxModel(lines));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightPlanner.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /*
     * The fillLabels() method fills out all of the data inputs for the: starting airport & its
     * radio frequency, heading, duration of flight, distance, destination airport & its radio
     * frequency, and refuel stops needed for the flight between the two airports.
     */
    void fillLabels() {
        String selectedDrop = (String) startingDropDown.getSelectedItem();
        String[] arrOfDrop = selectedDrop.split(",");
        startAirportLabel.setText("Airport: "+ arrOfDrop[2] + " Radio Freqency: " + arrOfDrop[4]);
        String selectedDrop1 = (String) startingDropDown.getSelectedItem();
        String[] arrOfDrop1 = selectedDrop1.split(",");
        String selectedDrop2 = (String) arrivalDropDown.getSelectedItem();
        String[] arrOfDrop2 = selectedDrop2.split(",");
        String airplaneDrop = (String) airplaneDropDown.getSelectedItem();
        String[] arrOfDrop3 = airplaneDrop.split(",");
        String city1 =(arrOfDrop1[2]);
        String city2 =(arrOfDrop2[2]);
        
        try {
            double lat1 = Double.parseDouble(arrOfDrop1[6]);
            double lon1 = Double.parseDouble(arrOfDrop1[5]);
            double lat2 = Double.parseDouble(arrOfDrop2[6]);
            double lon2 = Double.parseDouble(arrOfDrop2[5]);
            final DecimalFormat round22 = new DecimalFormat("0.00");

            // Heading formula to output radians
            double theta = Math.acos(((lat2-lat1)/(Math.sqrt((Math.pow(lon2-lon1, 2))+(Math.pow(lat2-lat1, 2))))));
            // Theta converted to degrees
            theta = theta*180/Math.PI;
            double heading;
            if(lon2>lon1) { // If second airport is on the right
            	heading = theta;
            } else { // If second airport is on the left, subtract 360
            	heading = 360-theta;
            }
            	
            headingLabel.setText("The angle of the flight path is: " +  round22.format(theta) + " degrees");

            // Distance in kilometers based on Haversine formula            
            double latminRads = Math.toRadians(lat2-lat1);
            double lonminRads = Math.toRadians(lon2-lon1);
            double lat1Rads = Math.toRadians(lat1);
            double lat2Rads = Math.toRadians(lat2);
            int eRad = 6371;
            
            double formula = Math.pow((Math.sin(latminRads/2)), 2) + Math.pow((Math.sin(lonminRads/2)), 2) * Math.cos(lat1Rads)*Math.cos(lat2Rads);
            double squareForm = 2*Math.asin(Math.sqrt(formula));
            double distKM = eRad *squareForm;
             
            distanceLabel.setText("The distance between " + arrOfDrop1[5] + ", " + arrOfDrop1[6] + " and "+ arrOfDrop2[5]+ ", " + arrOfDrop2[6] + " is: " + round22.format(distKM) +" kilometers");
            
            double testLPH = Double.parseDouble(arrOfDrop3[4]);
            double testTankSize = Double.parseDouble(arrOfDrop3[5]);
            double testCruiseSpeed = Double.parseDouble(arrOfDrop3[3]);
            
            double time = distKM/ testCruiseSpeed;
            int hrs = (int)time;
            double minSec = (time - hrs)*60;
            int mins = (int)minSec;
            int secs = (int)((minSec - mins)*60);

            durationLabel.setText("The duration of the flight was: " +  hrs + " hours, " + mins + " minutes, " + secs + " seconds.");
            destAirportLabel.setText("Airport: "+ arrOfDrop2[2] + " Radio Freqency: " + arrOfDrop2[4]); 
            double maxRange = (testTankSize/testLPH)*testCruiseSpeed;
            int stops = 0;
            String kmNotif = "Stopped with: ";
            
            if(maxRange > distKM) {
            	stops = 0;
            	numberOfRefills.setText("Refills: " +  round22.format(stops));
            }
            else if (maxRange <= distKM){
	        	for(double i = distKM-maxRange; i>0; i = i - maxRange ) {
	        		stops = stops + 1;
	        		double timeLeft = i/ testCruiseSpeed;
	        		System.out.println(timeLeft);
	                int hrsLeft = (int)timeLeft;
	                System.out.println(hrsLeft);
	                double minsLeft = (timeLeft - hrsLeft)*60;
	        		kmNotif = kmNotif + "<br>" + String.valueOf(round22.format(i)) + "kms left and " + (int)hrsLeft + " hours, " + (int)minsLeft + " minutes remaining -- ";
	        		}
	        	numberOfRefills.setText("<html>Refills: " +  round22.format(stops) + kmNotif + "<html>"); 
            }
            else {
    	        JOptionPane.showMessageDialog(null,"No flight plan available!");
            }
	    } catch (NumberFormatException ex) {
	        System.out.println("Given Strings are not parsable to double");
	        JOptionPane.showMessageDialog(null,"Given Strings are not parsable to double");
	    }
    }
    
    /*
     * The method initComponents(), a Netbeans machine-generated code, holds the GUI components for the 
     * FlightPlanner.java class. JButton, JLabel, JPanels, & filler components are implemented. 
     */    
    private void initComponents() {

         // Character format for JLabels, DropDowns, & JPanels created.
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jPanel1 = new javax.swing.JPanel();
        fillForm = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        startingDropDown = new javax.swing.JComboBox<>();
        arrivalDropDown = new javax.swing.JComboBox<>();
        airplaneDropDown = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        calcFlight = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        startAirportLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        distanceLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        destAirportLabel = new javax.swing.JLabel();
        numberOfRefills = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Will close the GUI when exit is prompted.
        setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        fillForm.setText("Fill Dropdown"); // Fill Dropdown button is named.
        fillForm.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Fill Dropdown button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillFormActionPerformed(evt);
            }
        });

        // Character format for JLabels created.
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 3, 24)); 
        jLabel2.setText("USCA Flight Planner");
        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel10.setText("Starting Airport:");
        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel11.setText("Arrival Airport:");
        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel12.setText("Airplane:");
        calcFlight.setText("Calculate Flight");
        
        calcFlight.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Calculate Flight button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcFlightActionPerformed(evt);
            }
        });

        backButton.setText("Back"); // Back button is named.
        backButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Back button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        /* This is the formatting for the GUI. JLabels, JPanels, and the corresponding 
         * DropDown fields are set up on a ContentPane() together. */
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startingDropDown, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(airplaneDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(arrivalDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(calcFlight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fillForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 114, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startingDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arrivalDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(airplaneDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 337, Short.MAX_VALUE)
                .addComponent(calcFlight)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fillForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        // Character format for JLabels created.
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel3.setText("Flight Ticket:");
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); 
        jLabel1.setText("Starting Airport and Radio Frequency:");
        startAirportLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); 
        jLabel5.setText("Heading:");
        headingLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); 
        jLabel7.setText("Duration of Flight:");
        durationLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); 
        jLabel9.setText("Distance:");
        distanceLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); 
        jLabel14.setText("Destination Airport and Radio Frequency:");
        destAirportLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        numberOfRefills.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); 
        jLabel4.setText("Refills:");

        /* This is the formatting for the GUI. JLabels and JPanels are set up on
         * a ContentPane() together. */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startAirportLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(headingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(durationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(distanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                    .addComponent(destAirportLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberOfRefills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startAirportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(durationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(distanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destAirportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberOfRefills, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    // This method is the ActionEvent for the Fill Form button.
    private void fillFormActionPerformed(java.awt.event.ActionEvent evt) {
        createAirportFolder();
        createAirplaneFolder();
        readAirportFile();
        readAirplaneFile();
        fillStartingAirportDropFromDbFile();
        fillArrivalAirportDropFromDbFile();
        fillAirplaneDropFromDbFile();
    }

    // This method is the ActionEvent for the Calculate Flight button.
    private void calcFlightActionPerformed(java.awt.event.ActionEvent evt) {
        fillLabels();
    }

    // This method is the ActionEvent for the Back button.
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new HomeScreen().setVisible(true);
    }

    // The main method sets the Flight Planner screen to "true" which allows the GUI to pop-up when ran.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FlightPlanner().setVisible(true);
            }
        });
    }

    // Variable declaration
    private javax.swing.JComboBox<String> airplaneDropDown;
    private javax.swing.JComboBox<String> arrivalDropDown;
    private javax.swing.JButton backButton;
    private javax.swing.JButton calcFlight;
    private javax.swing.JLabel destAirportLabel;
    private javax.swing.JLabel distanceLabel;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JButton fillForm;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel numberOfRefills;
    private javax.swing.JLabel startAirportLabel;
    private javax.swing.JComboBox<String> startingDropDown;
}
