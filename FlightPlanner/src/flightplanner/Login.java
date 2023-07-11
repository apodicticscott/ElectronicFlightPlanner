package flightplanner;

/*
 * Flight Planner Final Project: Login Class
 * Group 1: Sophie Lyon, Caroline Peace, Scott Rostron
 * Program file name: Login.java
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
 * The Login Class implements:
 * FileReader and FileWriter to create and verify the "logins.txt" file.
 * RandomAccessFile to access & have read, write permissions within the "logins.txt" file.
 * BufferedReader and BufferedWriter to read and write to the "logins.txt" file.
 * Void methods to add, delete, and modify data within the "logins.txt" file.
 */
public class Login extends javax.swing.JFrame {

    File f = new File("FlightLogin"); // A new file (f), "FlightLogin" is created.
    
    int ln; /* Initializes an integer variable (ln) that will be used in the countLines()
               method to count the lines in the "logins.txt" file.*/
    
    String Username, Password; /* Initializes the string variables (Username) &
                                 (Password) to store future */

    /*
     * The Login() method is implemented in the main method & calls all of 
     * the initComponents() and sets the location of the GUI.
     */
    public Login() {
        initComponents();
        super.setLocationRelativeTo(null); // Makes GUI appear in the center of the screen.
    }

    /*
     * The createFolder() method will create a directory into
     * the "FlightLogin" file if one does not already exist.
     */
    void createFolder() {
        if(!f.exists()) {
            f.mkdirs();
        }
    }
    
    /*
     * The readFile() method implements FileReader & FileWriter to check
     * for the creation of the "logins.txt" file, if one does not already
     * exist, then an instance of "logins.txt" file is created.
     */
    void readFile() {
        try { // Reads for the existence of the "logins.txt" file.
            FileReader fr = new FileReader(f+"/logins.txt");
            System.out.println("File exists!");
        } catch (FileNotFoundException ex) {
            try { // Will create "logins.txt" file if one doesn't exist.
                FileWriter fw = new FileWriter(f+"/logins.txt");
                System.out.println("File created");
            } catch (IOException ex1) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
              }
          }
        
    }
    
    /*
     *  The addData() method takes string variables, (usr) & (pswd) and uses
     *  RandomAccessFile to read and write these variables into the "logins.txt' file.
     *  If statements are used for error handling in order to verify that (usr) cannot 
     *  be used more than once, and to register and write data to the "logins.txt" file.
     */
    void addData(String usr, String pswd) {
        try {  
            RandomAccessFile raf = new RandomAccessFile(f+"/logins.txt", "rw"); /* Accesses the file, & gives read
                                                                                   and write permissions. */
            int i = 0; // Initializes int (i) for the if loop that traverses through the "logins.txt" file.
            String thisLine = raf.readLine(); // String in the RandomAccessFile that reads the lines of the file.
            if(i == ln) {
        		raf.writeBytes(usr + "," + pswd + "\r\n");
            } else {
            	while(i <= ln - 1) {
            		if(thisLine.length() < usr.length()) {
            			thisLine = raf.readLine(); // Reads line if value is less than (usr) length.
            			if(thisLine == null) { /* If the current line is empty, the first (usr) & (pswd) 
            			                          will be written to the "logins.txt" file. */
                 		   raf.writeBytes(usr + "," + pswd + "\r\n");
                 		   JOptionPane.showMessageDialog(null,"Data Registered");
                 		   break;
                 		 }
            		}
            		else if(thisLine.substring(0, (usr.length())).equals(usr)) { /* If the (usr) is equivalent to another (usr)
            		                                                                in the file, input will be rejected. */
            			JOptionPane.showMessageDialog(null, "Username exists!");
            			break;
            		}else {
            		thisLine = raf.readLine();
            		System.out.println("next line is " + thisLine);
            		if(thisLine == null) { /* For all other (usr) & (pswd) inputs. If the current line is null, and all above 
            		                          conditions apply, the (usr) & (pass) will be written to the "logins.txt" file. */
            		   raf.writeBytes(usr+","+pswd+"\r\n");
            		   JOptionPane.showMessageDialog(null,"Data Registered");
            		   break;
            		 }
            		}
            		i ++;
            	}
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*
     * The deleteData() method takes the string (usr) and uses a for loop to
     * delete the corresponding (pswd). The replace() method is used to
     * delete the entire instance of the (usr) and any following characters in that line.
     */
    void deleteData(String usr) {
        try {
        	RandomAccessFile raf = new RandomAccessFile(f+"/logins.txt", "rw"); /* Accesses the file, & gives read
                                                                                   and write permissions. */
            String dataLine = raf.readLine(); // String (dataLine) used in for loop to count lines in the "logins.txt" file.
            String oldPassword = ""; // Empty string (oldPassword) created to replace (pswd) when deleted in for loop.
        	
            for(int i = 0; i <= ln; i++) { 
                if(dataLine.substring(0, (usr.length())).equals(usr)) { // Shortens the line read to the length of (usr)
                	oldPassword = dataLine.substring(usr.length()+1); // Reads line from data to gather (oldPassword) to delete.
                	System.out.println(oldPassword);
                    break;
                }
                else {
                	dataLine = raf.readLine(); // Reads the current line.
                }
            }
            String deleteUser = usr + "," + oldPassword + "\r\n"; // String created to replace (usr) when it is deleted.
            String filePath = f + "/logins.txt"; // Path of the "logins.txt" file.   
            String[] stringsToDelete = {deleteUser}; // String array calling the strings to delete.
            System.out.println(deleteUser); // Prints out the new deleted user (empty string).
            
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Reads the entire file into a string.
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) { // When the line is not null, it reads the line.
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            String fileContent = stringBuilder.toString();
            reader.close();

            for (String stringToDelete : stringsToDelete) { // Deletes the strings from the file content.
                fileContent = fileContent.replace(stringToDelete, "");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)); // Writes the updated content back to the file.
            writer.write(fileContent);
            writer.close();
            JOptionPane.showMessageDialog(null,"The user  ''" + usr + "''  is gone!");
        } catch (IOException e) {
            System.out.println("Error occurred while deleting strings: " + e.getMessage());
        }
    }
        
    /*
     * The modifyData() method takes string variables (usr) & (newpswd) and uses
     * RandomAccessFile within a for loop to modify the user's password. The deleteData()
     * method is used to delete the old data, and the addData() method is used to update 
     * the new data to the "logins.txt" file.
     */
    void modifyData(String usr,String newpswd) {
    	 try {
             RandomAccessFile raf = new RandomAccessFile(f+"/logins.txt", "rw"); /* Accesses the file, & gives read
             																		and write permissions. */
             String line = raf.readLine();
             for(int i = 0; i < ln; i++) { // Shortens the line read to the length of (usr) 
                if(!line.substring(0, (usr.length())).equals(usr)) {
                	line = raf.readLine();
                }
                else {                  
                    deleteData(usr); // Deletes old data.
                    addData(usr,newpswd); // Adds updated data.
                    JOptionPane.showMessageDialog(null, "The user  ''" + usr + "''  was modified.");
                    break;
                }
             }
             raf.close();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
         }
    	
    }
    
    /*
     * The logic() method takes string variables (usr) & (pswd) and uses
     * RandomAccessFile within a for loop to verify the username & password for login.
     */
    void logic(String usr,String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"/logins.txt", "rw"); /* Accesses the file, & gives read
            																	   and write permissions. */
            for(int i = 0; i <= ln; i++) {System.out.println("Count " + i);
                String dataline = usr + "," + pswd;
                if(dataline.equals(raf.readLine())) { /* If input matches data in file, Login screen is hidden and
                                                         the (usr) is shown the HomeScreen(). */
                    JOptionPane.showMessageDialog(null, "Password matched");
                    super.setVisible(false);
                    new HomeScreen().setVisible(true);
                    break;
                }else if(i == (ln)) { // If data does not match, (usr) is denied login access.
                    JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*
     * The countLines() method uses RandomAccessFile and a for loop in order to
     * count the lines within the "logins.txt" file.
     */
    void countLines() {
        try {
            ln = 0;
            RandomAccessFile raf = new RandomAccessFile(f+"/logins.txt", "rw"); /* Accesses the file, & gives read
			   																	   and write permissions. */ 
            for(int i = 0; raf.readLine() != null; i++) { // Every line that is not null is read.
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
     * Login.java class. JButton, JLabel, JTextField, & ActionListener components are implemented. 
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

    	// JButtons 1-4, JLabels 1-3, and JTextFields (tfusr) & (tfpswd) are created.
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfusr = new javax.swing.JTextField();
        tfpswd = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Will close the GUI when exit is prompted.

        jButton2.setText("Login"); // Login button is named.
        jButton2.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Login button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear All"); // Clear All button is named. 
        jButton3.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Clear All button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Test"); // Test button is named.
        jButton4.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Test button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        // Character format for JLabels and JTextFields created.
        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); 
        jLabel1.setText("Flight Planner");
        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); 
        jLabel2.setText("Username");
        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); 
        jLabel3.setText("Password");
        tfusr.setFont(new java.awt.Font("Arial", 0, 18)); 
        tfpswd.setFont(new java.awt.Font("Arial", 0, 18)); 

        jButton1.setText("Register"); // Register button is named.
        jButton1.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Register button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        /* This is the formatting for the GUI. JButtons, JLabels, and JTextFields are set up on
         * a ContentPane() together. */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfusr, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(tfpswd))))))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfusr, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfpswd, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4))
        );

        pack();
    }

    // This method is the ActionEvent for the Login button.
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    	createFolder(); 
    	readFile();
    	countLines();
    	logic(tfusr.getText(), tfpswd.getText());
    }

    // This method is the ActionEvent for the Clear All button.
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    	tfusr.setText("");     
    	tfpswd.setText("");
    }

    // This method is the ActionEvent for the Test button.
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    	createFolder(); 
    	readFile();
    	countLines();
    	addData("admin","password");
    }

    // This method is the ActionEvemt for the Register button.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	createFolder(); 
    	readFile();
    	countLines();
    	addData(tfusr.getText(), tfpswd.getText());
    }

    // The main method sets the Login screen to "true" which allows the GUI to pop-up when ran.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variable declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tfpswd;
    private javax.swing.JTextField tfusr;
}
