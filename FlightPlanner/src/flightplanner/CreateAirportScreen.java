package flightplanner;

/*
 * Flight Planner Final Project: Create Airport Screen Class
 * Group 1: Sophie Lyon, Caroline Peace, Scott Rostron
 * Program file name: CreateAirportScreen.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * The Create Airport Screen Class implements:
 * FileReader and FileWriter to create and verify the "airports.txt" file.
 * RandomAccessFile to access & have read, write permissions within the "airports.txt" file.
 * A boolean method used to checkData().
 * BufferedReader and BufferedWriter to read and write to the "airports.txt" file.
 * Void methods to add & modify data within the "airports.txt" file.
 */
public class CreateAirportScreen extends javax.swing.JFrame {
    File f = new File("AirportDatabase"); // A new file (f), "AirportDatabase" is created.
    int ln; /* Initializes an integer variable (ln) that will be used in the countLines()
               method to count the lines in the "airports.txt" file.*/
    String Name, ICAO, City, RadioType; // String variables initialized for user input.
    String Long, Lat, RadioFreq, Runway; // String variables to be parsed as doubles or ints for user input.

    /*
     * The CreateAirportScreen() method is implemented in the main method & calls all of 
     * the initComponents() and sets the location of the GUI.
     */
    public CreateAirportScreen() {
        initComponents();
        super.setLocationRelativeTo(null); // Makes GUI appear in the center of the screen.
    }
    
    /*
     * The createFolder() method will create a directory into
     * the "AirportDatabase" file if one does not already exist.
     */
    void createFolder(){
        if(!f.exists()){
            f.mkdirs();
        }
    }
    
    /*
     * The readFile() method implements FileReader & FileWriter to check
     * for the creation of the "airports.txt" file, if one does not already
     * exist, then an instance of "airports.txt" file is created.
     */
    void readFile(){
            try { // Reads for the existence of the "airports.txt" file.
                FileReader fr = new FileReader(f+"/airports.txt");
                System.out.println("file exists!");
            } catch (FileNotFoundException ex) {
                try { // Will create "airports.txt" file if one doesn't exist.
                    FileWriter fw = new FileWriter(f+"/airports.txt");
                    System.out.println("File created");
                } catch (IOException ex1) {
                    Logger.getLogger(CreateAirportScreen.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
    }
    
    /*
     * The checkData() method takes the string inputs: name, ICAO, City,
     * RadioType, RadioFreq, Long, Lat, & RunwayLen, and uses if statements
     * to verify that the inputs correspond to each variables' parameters.
     */
    boolean checkData(String name, String ICAO, String City, String RadioType, String RadioFreq, String Long, String Lat, String RunwayLen) {
    	// If nothing is entered, return an error.
        if(name == null|| ICAO == null|| RadioType == null|| RadioFreq == null|| Long == null|| Lat == null|| RunwayLen == null) {
    		JOptionPane.showMessageDialog(null, "Please fill all inputs!");
    		return false;
        }
        
    	// Verifying Airport Name is only letters & spaces.
    	if(name.matches("^[ A-Za-z]+$") == false) {
    		JOptionPane.showMessageDialog(null, "Name must contain only letters!");
    		return false;
    	}
    	// Verifying ICAO is only 3-4 letters.
    	if(ICAO.matches("[a-zA-Z]+") == false) {
    		JOptionPane.showMessageDialog(null, "ICAO must contain only letters!");
    		return false;
    	}
		if(ICAO.length() < 3 || ICAO.length() > 4) {
			JOptionPane.showMessageDialog(null, "ICAO must contain only 3-4 letters!");
			return false;
		}
    	// Verifying Longitude is between -180 and 180.
    	if(Double.parseDouble(Long) < -181 || Double.parseDouble(Long) > 181) {
    		JOptionPane.showMessageDialog(null, "Longitude must be a number between -180 and 180!");
    		return false;
    	}
    	// Verifying Latitude is between -90 and 90.
    	if(Double.parseDouble(Lat) < -91 || Double.parseDouble(Lat) > 91) {
    		JOptionPane.showMessageDialog(null, "Latitude must be a number between -90 and 90!");
    		return false;
    	}
    	// Verifying City is only letters & spaces.	
    	if(City.matches("^[ A-Za-z]+$") == false) {
    		JOptionPane.showMessageDialog(null, "City must contain only letters!");
    		return false;
    	}
    	// Verifying Radio Type is only letters.
    	if(RadioType.matches("[a-zA-Z]+") == false) {
    		JOptionPane.showMessageDialog(null, "Radio Type must contain only letters!");
    		return false;
    	}
    	// Verifying Radio Frequency is an integer between 108 & 137
    	if(Integer.parseInt(RadioFreq) < 108 || Integer.parseInt(RadioFreq) > 137) {
    		JOptionPane.showMessageDialog(null, "Radio Frequency must be a number between 108-137!");
    		return false;
    	}
    	// Verifying Runway Length is between 500 and 100000
    	if(Integer.parseInt(RunwayLen) < 500 || Integer.parseInt(RunwayLen) > 100000) {
    		JOptionPane.showMessageDialog(null, "Runway Length must be a number between 500-99999!");
    		return false;
    	}
    	else 
    		return true;
        }
        
    /*
     * The addData() method takes the string inputs: name, ICAO, City,
     * RadioType, RadioFreq, Long, Lat, & RunwayLen, and uses if statements
     * to verify that the inputs correspond to each variables' parameters, if they do - 
     * then the data is added into the "airports.txt" file.
     */
    void addData(String name, String ICAO, String City, String RadioType, String RadioFreq, String Long, String Lat, String RunwayLen){
        try {
            Double testLong = Double.parseDouble(Long); // Long parsed as double.
            Double testLat = Double.parseDouble(Lat); // Lat parsed as double.
            Integer testRadioFreq = Integer.parseInt(RadioFreq); // RadioFreq parsed as integer.
            Integer testRunway = Integer.parseInt(RunwayLen); // RunwayLen parsed as integer.
            RandomAccessFile raf = new RandomAccessFile(f+"/airports.txt", "rw"); /* Accesses the file, & gives read
            																	     and write permissions. */
            int i=0; // Initializes int (i) for the if loop that traverses through the "logins.txt" file.
            String thisLine = raf.readLine(); // String in the RandomAccessFile that reads the lines of the file.
            if(i==ln) {
        		raf.writeBytes(ICAO+","+name+","+City+","+RadioType+","+RadioFreq+","+Long+","+Lat+","+RunwayLen+"\r\n");
            } else {
            	while(i<=ln-1) {
            		if(thisLine.length() < ICAO.length()) {
            			thisLine = raf.readLine(); // Reads line if value is less than (ICAO) length.
            			if(thisLine == null) { /* If the current line is empty, the first data 
	                          					  will be written to the "airports.txt" file. */
                 		   raf.writeBytes(ICAO+","+name+","+City+","+RadioType+","+RadioFreq+","+Long+","+Lat+","+RunwayLen+"\r\n");
                 		   JOptionPane.showMessageDialog(null,"Data Registered");
                 		   break;
                 		 }
            		}
            		else if(thisLine.substring(0, (ICAO.length())).equals(ICAO)) { /* If the (ICAO) is equivalent to another (ICAO)
                        															  in the file, input will be rejected. */
            			JOptionPane.showMessageDialog(null, "ICAO string exists!");
            			break;
            		}else {
            		thisLine = raf.readLine();
            		System.out.println("next line is " + thisLine);
            		if(thisLine == null) { /* For all other data inputs. If the current line is null, and all above 
                        					  conditions apply, the data will be written to the "airports.txt" file. */
            		   raf.writeBytes(ICAO+","+name+","+City+","+RadioType+","+RadioFreq+","+Long+","+Lat+","+RunwayLen+"\r\n");
            		   JOptionPane.showMessageDialog(null,"Data Registered");
            		   break;
            		 }
            		}
            		i++;
            	}
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateAirportScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateAirportScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
         System.out.println("Given String is not parsable to double");
         JOptionPane.showMessageDialog(null,"Verify that Radio Frequency, Longitude, Latitude, and Runway are all numbers!");
      }
        
    }
        
    /*
     * The countLines() method uses RandomAccessFile and a for loop in order to
     * count the lines within the "airports.txt" file.
     */
    void countLines(){
        try {
            ln=0;
            RandomAccessFile raf = new RandomAccessFile(f+"/airports.txt", "rw"); /* Accesses the file, & gives read
			   																		 and write permissions. */ 
            for(int i=0;raf.readLine()!=null;i++){ // Every line that is not null is read.
                ln++;
            }
            System.out.println("Number of lines:"+ln);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateAirportScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateAirportScreen.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /*
     * The method initComponents(), a Netbeans machine-generated code, holds the GUI components for the 
     * CreateAirportScreen.java class. JButtons, Filler, JPanels, JLabels, & JTextFields are implemented. 
     */  
    private void initComponents() {

    	// JButtons, Filler, JPanels, JLabels, & JTextFields are created.
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfICAO = new javax.swing.JTextField();
        tfLong = new javax.swing.JTextField();
        tfLat = new javax.swing.JTextField();
        tfCity = new javax.swing.JTextField();
        tfRadioType = new javax.swing.JTextField();
        tfRadioFreq = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tfRunwayLength = new javax.swing.JTextField();

        jButton1.setText("Create"); // Create button is named.
        jButton1.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Create button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Will close the GUI when exit is prompted.

        // Character format for JPanel & JLabels created.
        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        
        jButton6.setText("Back"); // Back button is named.
        jButton6.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Back button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        // Character format for JLabels created.
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel2.setText("Create Airport");

        /* This is the formatting for the GUI. JPanels & JLabels are set up on
         * a ContentPane() together. */
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Character format for JLabels created.
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel1.setText("Name:");
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel3.setText("ICAO:");
        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel4.setText("Longitude:");
        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel5.setText("Latitude:");
        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel6.setText("City:");
        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel7.setText("Radio Type:");
        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel8.setText("Radio Frequency:");
        tfLong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLongActionPerformed(evt);
            }
        });

        createButton.setText("Create"); // Create button is named.
        createButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Create button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        // Character format for JLabels created.
        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel9.setText("Runway Length:");

        /* This is the formatting for the GUI. JPanels, fillers,JLabels, & JTextFields are set up on
         * a ContentPane() together. */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfICAO))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfLong))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfLat))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCity))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfRadioType))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfRadioFreq))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfRunwayLength))))
                            .addComponent(createButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfICAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfRadioType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfRadioFreq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRunwayLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(createButton)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // This method is the ActionEvent for the Back button.
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new HomeScreen().setVisible(true);
    }

    // This method is the ActionEvent for the Create button.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // This method is the ActionEvent for the tfLong button.
    private void tfLongActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // This method is the ActionEvent for the Create button.
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createFolder(); 
        readFile();
        countLines();
        if (checkData(tfName.getText(), tfICAO.getText(), tfCity.getText(), tfRadioType.getText(), tfRadioFreq.getText(), tfLong.getText(), tfLat.getText(), tfRunwayLength.getText())==true) {
            addData(tfName.getText(), tfICAO.getText(), tfCity.getText(), tfRadioType.getText(), tfRadioFreq.getText(), tfLong.getText(), tfLat.getText(), tfRunwayLength.getText());
        }    }

    // The main method sets the Create Airport screen to "true" which allows the GUI to pop-up when ran.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAirportScreen().setVisible(true);
            }
        });
    }

    // Variable declaration
    private javax.swing.JButton createButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfCity;
    private javax.swing.JTextField tfICAO;
    private javax.swing.JTextField tfLat;
    private javax.swing.JTextField tfLong;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfRadioFreq;
    private javax.swing.JTextField tfRadioType;
    private javax.swing.JTextField tfRunwayLength;
}
