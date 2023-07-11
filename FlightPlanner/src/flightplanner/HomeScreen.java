package flightplanner;

/*
 * Flight Planner Final Project: Home Screen Class
 * Group 1: Sophie Lyon, Caroline Peace, Scott Rostron
 * Program file name: HomeScreen.java
 */

/*
 * The HomeScreen Class uses a Netbeans machine-generated GUI code to layout
 * the format of the Home screen, which is accessed by the Login.java class 
 * when a user successfully logs in.
 */
public class HomeScreen extends javax.swing.JFrame {
    
    /*
     * The HomeScreen() method is implemented in the main method & calls all of 
     * the initComponents() and sets the location of the GUI.
     */
    public HomeScreen() {
        initComponents();
        super.setLocationRelativeTo(null); // Makes GUI appear in the center of the screen.
    }
    
    /*
     * The method initComponents(), a Netbeans machine-generated code, holds the GUI components for the 
     * HomeScreen.java class. JButton, JLabel, JPanels, & filler components are implemented. 
     */
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Will close the GUI when exit is prompted.
        
        // Format for JPanels * JButtons created.
        setPreferredSize(new java.awt.Dimension(450, 450));
        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Character format for JLabels created.
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); 
        jLabel1.setText("For Educational Use Only");

        jButton1.setText("Flight Planner"); // Flight Planner button is named.
        jButton1.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Flight Planner button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Create Airport"); // Create Airport button is named.
        jButton2.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Create Airport button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Airport List"); // Airport List button is named.
        jButton3.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Airport List button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Create Airplane"); // Create Airplane button is named.
        jButton4.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Create Airplane List button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Airplane List"); // Airplane List button is named.
        jButton5.addActionListener(new java.awt.event.ActionListener() { // Action Listener created for the Airplane List button.
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        /* This is the formatting for the GUI. JButtons and JLabels are set up on
         * a ContentPane() together. */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // This method is the ActionEvent for the Back button.
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new Login().setVisible(true);
    }

    // This method is the ActionEvent for the Flight Planner button.
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new FlightPlanner().setVisible(true);
    }

    // This method is the ActionEvent for the Create Airport button.
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new CreateAirportScreen().setVisible(true);
    }

    // This method is the ActionEvent for the Airport List button.
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new AirportListScreen().setVisible(true);
    }

    // This method is the ActionEvent for the Create Airplane button.
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new CreateAirplaneScreen().setVisible(true);
    }

    // This method is the ActionEvent for the Airplane List button.
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        super.setVisible(false);
        new AirplaneListScreen().setVisible(true);
    }

    // The main method sets the Home screen to "true" which allows the GUI to pop-up when ran.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variable declaration
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
}
