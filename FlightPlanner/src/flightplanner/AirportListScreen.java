package flightplanner;

/*
 * Flight Planner Final Project: Airport List Screen Class
 * Group 1: Sophie Lyon, Caroline Peace, Scott Rostron
 * Program file name: AirportListScreen.java
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
 * The Airport List Screen Class implements:
 * FileReader and FileWriter to create and verify the "airports.txt" file.
 * RandomAccessFile to access & have read, write permissions within the "airports.txt" file.
 * A boolean method used to checkData().
 * Void methods to modify & delete data within the "airports.txt" file.
 * Methods used to display all of the data.
 */
public class AirportListScreen extends javax.swing.JFrame {
    File f = new File("AirportDatabase"); // A new file (f), "AirportDatabase" is created.
    int ln; /* Initializes an integer variable (ln) that will be used in the countLines()
    		   method to count the lines in the "airports.txt" file.*/
    String Name,ICAO,City,RadioType,RadioFreq; // String variables initialized for user input. 
    String Long, Lat, Runway; // String variables to be parsed as doubles or ints for user input.
    String func;
    
    /*
     * The AirportListScreen() method is implemented in the main method & calls all of 
     * the initComponents() and sets the location of the GUI.
     */
    public AirportListScreen() {
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
                Logger.getLogger(AirportListScreen.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    /*
     * the fillComboFromTxtFile() method fills out the 
     * Combobox from the "airports.txt" file.
     */
    void fillComboFromTxtFile(){
        
        String filePath = f+"/airports.txt"; // Path of the "airports.txt" file. 
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
        tfICAO.setText(arrOfDrop[0]);
        tfName.setText(arrOfDrop[1]);
        tfCity.setText(arrOfDrop[2]);
        tfRadioType.setText(arrOfDrop[3]);
        tfRadioFreq.setText(arrOfDrop[4]);
        tfLong.setText(arrOfDrop[5]);
        tfLat.setText(arrOfDrop[6]);
        tfRunwayLength.setText(arrOfDrop[7]);
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
    
    void modifyData(String newName, String ICAO, String newCity, String newRadioType, String newRadioFreq, String newLong, String newLat, String newRunway) {
   	String filePath = f+"/airports.txt"; // Path of the "airports.txt" file.   
        String oldString = (String) dropDown.getSelectedItem(); // String that will be deleted.
        String newString = ICAO+","+newName+","+newCity+","+newRadioType+","+newRadioFreq+","+newLong+","+newLat+","+newRunway;
        try {
            Double testLong = Double.parseDouble(newLong);
            Double testLat = Double.parseDouble(newLat);
            Integer testRunway = Integer.parseInt(newRunway);
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
            System.out.println("Given Strings are not parsable to double");
            JOptionPane.showMessageDialog(null,"Verify that Radio Frequency, Longitude, Latitude, and Runway are all numbers!");
        }
    }
   
    /*
     * The deleteData() method takes the string (icao) and uses a for loop to
     * delete the corresponding data. A temporary file is created to store info,
     * then it is written back into the original file, after deleted. 
     */
   void deleteData(String icao){
      String filePath = f+"/airports.txt"; // Path of the "airports.txt" file.   
      String identifier = icao; // The string that is being searched for to delete.
      
      try {
        if (tfICAO.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null,"Fill out update/delete form");
        } else {
         // Creates a temp. file
         File tempFile = File.createTempFile(f+"/temp", null);
         // Open the input file for reading
         BufferedReader reader = new BufferedReader(new FileReader(filePath));
         // Open the output file for writing
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
         // Traverse through the file line by line
         String currentLine;
         while ((currentLine = reader.readLine()) != null) {
            // Ff the line contains the identifier, skip it
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
    * airport was able to be modified or not.
    */
   void showNotif(String uniqVar) {
   	String lastFunc = func.substring(func.length()-1);
   	if(lastFunc.equals("M")) {
   	 JOptionPane.showMessageDialog(null, "The Airport  ''" + uniqVar + "''  was modified."); }
   	else if (lastFunc.equals("D")){
         JOptionPane.showMessageDialog(null,"The Airport  ''" + uniqVar + "''  is gone!"); }
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
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       } 
   }
    
   /*
    * The method initComponents(), a Netbeans machine-generated code, holds the GUI components for the 
    * AirportListScreen.java class. JButtons, Filler, JPanels, JLabels, Dropdowns, & JTextFields are implemented. 
    */  
    private void initComponents() {

    	// JButtons, Filler, JPanels, JLabels, Dropdowns, & JTextFields are created.
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jPanel1 = new javax.swing.JPanel();
        BackButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        labelICAO = new javax.swing.JLabel();
        labelLog = new javax.swing.JLabel();
        labelLat = new javax.swing.JLabel();
        labelCity = new javax.swing.JLabel();
        labelRadioType = new javax.swing.JLabel();
        labelRadioFreq = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfICAO = new javax.swing.JTextField();
        tfLong = new javax.swing.JTextField();
        tfLat = new javax.swing.JTextField();
        tfCity = new javax.swing.JTextField();
        tfRadioType = new javax.swing.JTextField();
        tfRadioFreq = new javax.swing.JTextField();
        UpdateButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        dropDown = new javax.swing.JComboBox<>();
        FillFormButton = new javax.swing.JButton();
        fillDropDown = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        labelRunwayLength = new javax.swing.JLabel();
        tfRunwayLength = new javax.swing.JTextField();

        jButton1.setText("Create"); // Create button is named.
        jButton1.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Create button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Will close the GUI when exit is prompted.

        // Character format for JLabels created.
        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        
        BackButton.setText("Back"); // Back button is named.
        BackButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Back button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        
        // Character format for JLabels created.
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel2.setText("Airport List");

        /* This is the formatting for the GUI. JPanels are set up on
         * a ContentPane() together. */
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Character format for JLabels created.
        labelName.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelName.setText("Name:");
        labelICAO.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelICAO.setText("ICAO:");
        labelLog.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelLog.setText("Longitude:");
        labelLat.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelLat.setText("Latitude:");
        labelCity.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelCity.setText("City:");
        labelRadioType.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelRadioType.setText("Radio Type:");
        labelRadioFreq.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelRadioFreq.setText("Radio Frequency:");
        tfICAO.setEditable(false);
        tfICAO.setBackground(new java.awt.Color(255, 255, 255));
        tfLong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfLongActionPerformed(evt);
            }
        });

        UpdateButton.setText("Update"); // Update button is named.
        UpdateButton.addActionListener(new java.awt.event.ActionListener() { // Update button is named.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete"); // Delete button is named.
        DeleteButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Delete button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        FillFormButton.setText("Fill Form"); // Fill Form button is named.
        FillFormButton.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Fill Form button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FillFormButtonActionPerformed(evt);
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

        // Character format for JLabels created.
        labelRunwayLength.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); 
        labelRunwayLength.setText("Runway Length:");

        /* This is the formatting for the GUI. JPanels, Filler, Dropdowns, JButtons,
         * JLabels, & JTextFields are set up on a ContentPane() together. */
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FillFormButton, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelLog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelLat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelRadioFreq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelRadioType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelCity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelICAO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelRunwayLength, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfICAO, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfLong, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfLat, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfCity, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfRadioType, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfRadioFreq)
                                    .addComponent(tfName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfRunwayLength)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fillDropDown)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dropDown, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(FillFormButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelICAO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfICAO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLog)
                    .addComponent(tfLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLat)
                    .addComponent(tfLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tfCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRadioType)
                    .addComponent(tfRadioType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRadioFreq)
                    .addComponent(tfRadioFreq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRunwayLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRunwayLength, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateButton)
                    .addComponent(DeleteButton)
                    .addComponent(fillDropDown)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // This method is the ActionEvent for the Back button.
    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new HomeScreen().setVisible(true);
    }

    // This method is the ActionEvent for the Create button.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // This method is the ActionEvent for the Long button.
    private void tfLongActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // This method is the ActionEvent for the Update button.
    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createFolder(); 
        readFile();
        countLines();
        if (checkData(tfName.getText(), tfICAO.getText(), tfCity.getText(), tfRadioType.getText(), tfRadioFreq.getText(), tfLong.getText(), tfLat.getText(), tfRunwayLength.getText())==true) {
            modifyData(tfName.getText(), tfICAO.getText(), tfCity.getText(), tfRadioType.getText(), tfRadioFreq.getText(), tfLong.getText(), tfLat.getText(), tfRunwayLength.getText());
        }        showNotif(tfICAO.getText());
    }

    // This method is the ActionEvent for the Delete button.
    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createFolder(); 
        readFile();
        countLines();
    	deleteData(tfICAO.getText());
    	showNotif(tfICAO.getText());
    }

    // This method is the ActionEvent for the Fill Form button.
    private void FillFormButtonActionPerformed(java.awt.event.ActionEvent evt) {
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
        tfName.setText("");
        tfICAO.setText("");
        tfCity.setText("");
        tfRadioType.setText("");
        tfRadioFreq.setText("");
        tfLong.setText("");
        tfLat.setText("");
        tfRunwayLength.setText("");
    }

    // The main method sets the Airport List screen to "true" which allows the GUI to pop-up when ran.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirportListScreen().setVisible(true);
                
            }
        });
    }

    // Variable declaration
    private javax.swing.JButton BackButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton FillFormButton;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> dropDown;
    private javax.swing.JButton fillDropDown;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCity;
    private javax.swing.JLabel labelICAO;
    private javax.swing.JLabel labelLat;
    private javax.swing.JLabel labelLog;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelRadioFreq;
    private javax.swing.JLabel labelRadioType;
    private javax.swing.JLabel labelRunwayLength;
    private javax.swing.JTextField tfCity;
    private javax.swing.JTextField tfICAO;
    private javax.swing.JTextField tfLat;
    private javax.swing.JTextField tfLong;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfRadioFreq;
    private javax.swing.JTextField tfRadioType;
    private javax.swing.JTextField tfRunwayLength;
}