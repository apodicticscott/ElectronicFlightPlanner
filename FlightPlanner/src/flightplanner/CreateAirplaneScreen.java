package flightplanner;

/*
 * Flight Planner Final Project: Create Airplane Screen Class
 * Group 1: Sophie Lyon, Caroline Peace, Scott Rostron
 * Program file name: CreateAirplaneScreen.java
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
 * The Create Airplane Screen Class implements:
 * FileReader and FileWriter to create and verify the "airplanes.txt" file.
 * RandomAccessFile to access & have read, write permissions within the "airplanes.txt" file.
 * A boolean method used to checkData().
 * BufferedReader and BufferedWriter to read and write to the "airplanes.txt" file.
 * Void methods to add & modify data within the "airplanes.txt" file.
 */
public class CreateAirplaneScreen extends javax.swing.JFrame {
    File f = new File("AirplaneDatabase"); // A new file (f), "AirplaneDatabase" is created.
    int ln; /* Initializes an integer variable (ln) that will be used in the countLines()
    		   method to count the lines in the "airports.txt" file.*/
    String Make, Model, FuelType; // String variables initialized for user input.
    String CruiseSpeed, TankSize, LPH; // String variables to be parsed as doubles or ints for user input.
  
    /*
     * The CreateAirplaneScreen() method is implemented in the main method & calls all of 
     * the initComponents() and sets the location of the GUI.
     */
    public CreateAirplaneScreen() {
        initComponents();
        super.setLocationRelativeTo(null); // Makes GUI appear in the center of the screen.
    }
    
    /*
     * The createFolder() method will create a directory into
     * the "AirplaneDatabase" file if one does not already exist.
     */
    void createFolder(){
        if(!f.exists()){
            f.mkdirs();
        }
    }

    /*
     * The readFile() method implements FileReader & FileWriter to check
     * for the creation of the "airplanes.txt" file, if one does not already
     * exist, then an instance of "airplanes.txt" file is created.
     */
    void readFile(){
        try { // Reads for the existence of the "airplanes.txt" file.
            FileReader fr = new FileReader(f+"/airplanes.txt");
            System.out.println("file exists!");
        } catch (FileNotFoundException ex) {
            try { // Will create "airplanes.txt" file if one doesn't exist.
                FileWriter fw = new FileWriter(f+"/airplanes.txt");
                System.out.println("File created");
            } catch (IOException ex1) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    /*
     * The checkData() method takes the string inputs: CruiseSpeed, Make, Model,
     * LPH, FuelType, & TankSize, and uses if statements
     * to verify that the inputs correspond to each variables' parameters.
     */
    boolean checkData(String CruiseSpeed, String Make, String Model, String LPH, String FuelType, String TankSize) {
    	// Verifying Make is only letters.
    	if(Make.matches("[a-zA-Z]+")==false)
    	{
    		JOptionPane.showMessageDialog(null, "Make must contain only letters!");
    		return false;
    	}
    	// Verifying FuelType is only letters.
    	if(FuelType.matches("[a-zA-Z]+")==false)
    	{
    		JOptionPane.showMessageDialog(null, "Fuel Type must contain only letters!");
    		return false;
    	}
    	else 
    		return true;
    }
    
    /*
     * The addData() method takes the string inputs: CruiseSpeed, Make, Model, LPH,
     * FuelType, & TankSize, and uses if statements to verify that the inputs correspond to each variables' parameters, if they do - 
     * then the data is added into the "airplanes.txt" file.
     */
    void addData(String CruiseSpeed, String Make, String Model, String LPH, String FuelType, String TankSize){
        try {
            int testCruiseSpeed = Integer.parseInt(CruiseSpeed); // CruiseSpeed pasred as int
            int testLPH = Integer.parseInt(LPH); // LPH parsed as int
            int testTankSize = Integer.parseInt(TankSize); // TankSize parsed as int
            RandomAccessFile raf = new RandomAccessFile(f+"/airplanes.txt", "rw"); /* Accesses the file, & gives read
		     																		   and write permissions. */
            int i=0; // Initializes int (i) for the if loop that traverses through the "logins.txt" file.
            String thisLine = raf.readLine(); // String in the RandomAccessFile that reads the lines of the file.
            if(i==ln) {
        		raf.writeBytes(Model+","+Make+","+FuelType+","+CruiseSpeed+","+LPH+","+TankSize+"\r\n");
            } else {
            	while(i<=ln-1) {
            		if(thisLine.length() < Model.length()) { 
            			thisLine = raf.readLine(); // Reads line if value is less than (model) length.
            			if(thisLine == null) { /* If the current line is empty, the first data 
        					  					  will be written to the "airplanes.txt" file. */
                 		   raf.writeBytes(Model+","+Make+","+FuelType+","+CruiseSpeed+","+LPH+","+TankSize+"\r\n");
                 		   JOptionPane.showMessageDialog(null,"Data Registered");
                 		   break;
                 		 }
            		}
            		else if(thisLine.substring(0, (Model.length())).equals(Model)) { /* If the (model) is equivalent to another (ICAO)
						  														        in the file, input will be rejected. */
            			JOptionPane.showMessageDialog(null, "Model name exists!");
            			break;
            		}else {
            		thisLine = raf.readLine();
            		System.out.println("next line is " + thisLine);
            		if(thisLine == null) { /* For all other data inputs. If the current line is null, and all above 
  					  						  conditions apply, the data will be written to the "airplanes.txt" file. */
            		   raf.writeBytes(Model+","+Make+","+FuelType+","+CruiseSpeed+","+LPH+","+TankSize+"\r\n");
            		   JOptionPane.showMessageDialog(null,"Data Registered");
            		   break;
            		 }
            		}
            		i++;
            	}
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
         System.out.println("Given Strings are not parsable to integer");
         JOptionPane.showMessageDialog(null,"Verify that Cruise Speed, Liters per Hour, and Tank Size are all numbers!");
      }
        
    }
    
    /*
     * The countLines() method uses RandomAccessFile and a for loop in order to
     * count the lines within the "airplanes.txt" file.
     */
    void countLines(){
        try {
            ln=0;
            RandomAccessFile raf = new RandomAccessFile(f+"/airplanes.txt", "rw"); /* Accesses the file, & gives read
				 																	  and write permissions. */ 
            for(int i=0;raf.readLine()!=null;i++){ // Every line that is not null is read.
                ln++;
            }
            System.out.println("Number of lines:"+ln);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        
    
    /*
     * The method initComponents(), a Netbeans machine-generated code, holds the GUI components for the 
     * CreateAirplaneScreen.java class. JButtons, Filler, JPanels, JLabels, & JTextFields are implemented. 
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
        tfCruiseSpeed = new javax.swing.JTextField();
        tfMake = new javax.swing.JTextField();
        tfModel = new javax.swing.JTextField();
        tfLPH = new javax.swing.JTextField();
        tfFuelType = new javax.swing.JTextField();
        tfTankSize = new javax.swing.JTextField();
        CreateButton = new javax.swing.JButton();

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
        jLabel2.setText("Create Airplane");

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
        jLabel1.setText("CruiseSpeed:");
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel3.setText("Make:");
        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel4.setText("Model:");
        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel5.setText("Liters Per Hour:");
        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel6.setText("Fuel Type:");
        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel7.setText("Tank Size:");
        tfModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfModelActionPerformed(evt);
            }
        });

        CreateButton.setText("Create"); // Create button is named.
        CreateButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Create button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateButtonActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(tfCruiseSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfMake))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfModel))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfLPH))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfFuelType))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfTankSize)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCruiseSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfLPH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfFuelType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfTankSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    // This method is the ActionEvent for the tfModel button.
    private void tfModelActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // This method is the ActionEvent for the Create button.
    private void CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createFolder(); 
        readFile();
        countLines();
        if (checkData(tfCruiseSpeed.getText(), tfMake.getText(), tfModel.getText(), tfLPH.getText(), tfFuelType.getText(), tfTankSize.getText())==true) {
            addData(tfCruiseSpeed.getText(), tfMake.getText(), tfModel.getText(), tfLPH.getText(), tfFuelType.getText(), tfTankSize.getText());  
        }    }

    // The main method sets the Create Airplane screen to "true" which allows the GUI to pop-up when ran.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAirplaneScreen().setVisible(true);
            }
        });
    }

    // Variable declaration
    private javax.swing.JButton CreateButton;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfCruiseSpeed;
    private javax.swing.JTextField tfFuelType;
    private javax.swing.JTextField tfLPH;
    private javax.swing.JTextField tfMake;
    private javax.swing.JTextField tfModel;
    private javax.swing.JTextField tfTankSize;
}