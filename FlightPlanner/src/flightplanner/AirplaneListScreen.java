package flightplanner;

/*
 * Flight Planner Final Project: Airplane List Screen Class
 * Group 1: Sophie Lyon, Caroline Peace, Scott Rostron
 * Program file name: AirplaneListScreen.java
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
 * The Airplane List Screen Class implements:
 * FileReader and FileWriter to create and verify the "airplanes.txt" file.
 * RandomAccessFile to access & have read, write permissions within the "airplanes.txt" file.
 * A boolean method used to checkData().
 * Void methods to modify & delete data within the "airplanes.txt" file.
 * Methods used to display all of the data.
 */
public class AirplaneListScreen extends javax.swing.JFrame {
    File f = new File("AirplaneDatabase"); // A new file (f), "AirplaneDatabase" is created.
    int ln; /* Initializes an integer variable (ln) that will be used in the countLines()
	   		   method to count the lines in the "airports.txt" file.*/
    String Make, Model, FuelType, CruiseSpeed, TankSize, LPH; // String variables initialized for user input. 
    String func;

    /*
     * The AirplaneListScreen() method is implemented in the main method & calls all of 
     * the initComponents() and sets the location of the GUI.
     */
    public AirplaneListScreen() {
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
                Logger.getLogger(AirplaneListScreen.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    /*
     * the fillComboFromTxtFile() method fills out the 
     * Combobox from the "airplanes.txt" file.
     */
    void fillComboFromTxtFile(){
        
        String filePath = f+"/airplanes.txt"; // Path of the "airplanes.txt" file. 
        File file = new File(filePath);
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            dropDown.setModel(new javax.swing.DefaultComboBoxModel(lines));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AirportListScreen.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /*
     * The fillLabelsFromCombo() method fills out the labels
     * from the Combobox.
     */
    void fillLabelsFromCombo(){
        String selectedDrop = (String) dropDown.getSelectedItem();
        String[] arrOfDrop = selectedDrop.split(",");
        tfModel.setText(arrOfDrop[0]);
        tfMake.setText(arrOfDrop[1]);
        tfFuelType.setText(arrOfDrop[2]);
        tfCruiseSpeed.setText(arrOfDrop[3]);
        tfLPH.setText(arrOfDrop[4]);
        tfTankSize.setText(arrOfDrop[5]);
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
    
    void modifyData(String newCruiseSpeed, String newMake, String Model, String newLPH, String newFuelType, String newTankSize) {
    	String filePath = f+"/airplanes.txt"; // Path of the "airplanes.txt" file.  
        String oldString = (String) dropDown.getSelectedItem(); // String that will be deleted.
        String newString = Model+","+newMake+","+newFuelType+","+newCruiseSpeed+","+newLPH+","+newTankSize;
        
        try {
            int testCruiseSpeed = Integer.parseInt(newCruiseSpeed);
            int testLPH = Integer.parseInt(newLPH);
            int testTankSize = Integer.parseInt(newTankSize);
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = fileReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
            String fileContent = stringBuilder.toString();

            fileReader.close();

            // Replacing oldString with newString in the fileContent
            String newFileContent = fileContent.replaceAll(oldString, newString);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath));
            fileWriter.write(newFileContent);
            func += "M";
            fileWriter.close();

            System.out.println("File updated successfully.");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AirportListScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AirportListScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Given Strings are not parsable to doubles");
            JOptionPane.showMessageDialog(null,"Verify that Cruise Speed, Liters per Hour, and Tank Size are all numbers!");
        }         
    }
    
    /*
     * The deleteData() method takes the string (model) and uses a for loop to
     * delete the corresponding data. A temporary file is created to store info,
     * then it is written back into the original file, after deleted. 
     */
    void deleteData(String model){
      String filePath = f+"/airplanes.txt"; // Path of the "airplanes.txt" file.  
      String identifier = model; // The string that is being searched for to delete.

      try {
          if (tfModel.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null,"Fill out update/delete form");
        } else {
         // Create a temporary file
         File tempFile = File.createTempFile(f+"/temp", null);
         // Open the input file for reading
         BufferedReader reader = new BufferedReader(new FileReader(filePath));
         // Open the output file for writing
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
         // Traverse through the file line by line
         String currentLine;
         while ((currentLine = reader.readLine()) != null) {
            // If the line contains the identifier, skip it
            if (currentLine.contains(identifier)) {
               continue;
            }
            // Otherwise, write it to the temporary file
            writer.write(currentLine);
            writer.newLine();
         }

         // Close the streams
         reader.close();
         writer.close();
         func += "D";

         // Delete the original file
         File originalFile = new File(filePath);
         originalFile.delete();

         // Rename the temporary file to the original file name
         tempFile.renameTo(originalFile);
          }
      } catch (IOException e) {
         e.printStackTrace();
      }
    }
    
    /*
     * The showNotif() method uses an if statement to verify if the
     * airplane was able to be modified or not.
     */
    void showNotif(String uniqVar) {
    	String lastFunc = func.substring(func.length()-1);
    	if(lastFunc.equals("M")) {
    	 JOptionPane.showMessageDialog(null, "The Airplane  ''" + uniqVar + "''  was modified."); }
    	else if (lastFunc.equals("D")){
          JOptionPane.showMessageDialog(null,"The Airplane  ''" + uniqVar + "''  is gone!"); }
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
     * AirplaneListScreen.java class. JButtons, Filler, JPanels, JLabels, Dropdowns, & JTextFields are implemented. 
     */  
    private void initComponents() {

    	// JButtons, Filler, JPanels, JLabels, Dropdowns, & JTextFields are created.
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jPanel1 = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
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
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        dropDown = new javax.swing.JComboBox<>();
        fillForm = new javax.swing.JButton();
        fillDropDown = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        jButton1.setText("Create"); // Create button is named.
        jButton1.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Create button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Will close the GUI when exit is prompted.

        // Character format for JLabels created.
        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        backButton.setText("Back"); // Back button is named.
        backButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Back button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        // Character format for JLabels created.
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel2.setText("Airplane List");

        /* This is the formatting for the GUI. JPanels are set up on
         * a ContentPane() together. */
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Character format for JLabels created.
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        jLabel1.setText("Cruise Speed:");
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
        tfModel.setEditable(false);
        tfModel.setBackground(new java.awt.Color(255, 255, 255));
        tfModel.addActionListener(new java.awt.event.ActionListener() {  
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfModelActionPerformed(evt);
            }
        });

        updateButton.setText("Update"); // Update button is named.
        updateButton.addActionListener(new java.awt.event.ActionListener() { // Update button is named.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete"); // Delete button is named.
        deleteButton.addActionListener(new java.awt.event.ActionListener() {  // Action Listener created for the Delete button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        fillForm.setText("Fill Form"); // Fill Form button is named.
        fillForm.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Fill Form button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillFormActionPerformed(evt);
            }
        });

        fillDropDown.setText("Fill Dropdown"); // Fill Dropdown button is named.
        fillDropDown.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Fill Dropdown button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillDropDownActionPerformed(evt);
            }
        });

        clearButton.setText("Clear"); // Clear button is named.
        clearButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Clear button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        /* This is the formatting for the GUI. JPanels, Filler, Dropdowns, JButtons,
         * JLabels, & JTextFields are set up on a ContentPane() together. */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fillForm, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fillDropDown)
                                .addGap(11, 11, 11)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(tfCruiseSpeed))
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
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfTankSize))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dropDown, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(fillForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 17, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(updateButton)
                    .addComponent(fillDropDown)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // This method is the ActionEvent for the Back button.
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new HomeScreen().setVisible(true);
    }

    // This method is the ActionEvent for the Create button.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // This method is the ActionEvent for the tfModel button.
    private void tfModelActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // This method is the ActionEvent for the Update button.
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createFolder(); 
        readFile();
        countLines();
        if (checkData(tfCruiseSpeed.getText(), tfMake.getText(), tfModel.getText(), tfLPH.getText(), tfFuelType.getText(), tfTankSize.getText())==true) {
            modifyData(tfCruiseSpeed.getText(), tfMake.getText(), tfModel.getText(), tfLPH.getText(), tfFuelType.getText(), tfTankSize.getText());  
        }         showNotif(tfModel.getText());
    }

    // This method is the ActionEvent for the Delete button.
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createFolder(); 
        readFile();
        countLines();
    	deleteData(tfModel.getText());
    	showNotif(tfModel.getText());
    }

    // This method is the ActionEvent for the Fill Form button.
    private void fillFormActionPerformed(java.awt.event.ActionEvent evt) {
        fillLabelsFromCombo();
    }

    // This method is the ActionEvent for the Fill Dropdown button.
    private void fillDropDownActionPerformed(java.awt.event.ActionEvent evt) {
        createFolder();
        readFile();
        fillComboFromTxtFile();
    }

    // This method is the ActionEvent for the Clear button.
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        tfCruiseSpeed.setText("");
        tfMake.setText(""); 
        tfModel.setText("");
        tfLPH.setText("");
        tfFuelType.setText("");
        tfTankSize.setText("");
    }

    // The main method sets the Airplane List screen to "true" which allows the GUI to pop-up when ran.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirplaneListScreen().setVisible(true);
            }
        });
    }

    // Variable declaration
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> dropDown;
    private javax.swing.JButton fillDropDown;
    private javax.swing.JButton fillForm;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JButton updateButton;
}
