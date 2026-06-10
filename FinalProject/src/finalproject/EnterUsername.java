package finalproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EnterUsername extends javax.swing.JFrame {
    
    private MainWindow mainWindow;
    private GameWindow gameWindow;
    private WarningWindow warningWindow;
    
    static int numOfUsers;
    static String[] users = new String[2];
    
    public void MoveJFrame() {
        this.setUndecorated(true);
        MainWindow.FrameDragListener frameDragListener = new MainWindow.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public EnterUsername(MainWindow m, int chosenTime) {
        MoveJFrame();
        initComponents();
        mainWindow = m;
        timeChosenLabel.setText(chosenTime + "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        userNameField = new javax.swing.JTextField();
        goBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        topLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        timeChosenLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        goBtn.setText("Go");
        goBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goBtnActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        topLabel.setText("Enter your username player 1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(topLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(topLabel))
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        timeChosenLabel.setForeground(new java.awt.Color(204, 255, 255));
        timeChosenLabel.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(goBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timeChosenLabel)
                .addGap(99, 99, 99))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(timeChosenLabel))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goBtnActionPerformed
        int chosenTime = Integer.parseInt(timeChosenLabel.getText());
        // Safety fix: If something wiped out your array, re-initialize it instantly
        if (users == null) {
            users = new String[2];
        }
        try {   
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            Scanner s = new Scanner(in);
            boolean found = false;
            
            int prevIndex = 0; 
            
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) continue;
                
                String[] items = line.split(":");
                
                for (int i = 0; i < items.length; i++) {
                    String[] currentUserData = items[i].split(",");
                    
                    // Fixed: Access the array at index 0 using usernames[0]
                    if (users[0] != null && currentUserData[0].equalsIgnoreCase(users[0])) {
                        prevIndex = i;
                    }
                    if (userNameField.getText().equalsIgnoreCase(currentUserData[0])) {
                        // Fixed: Access the array at index 0 using usernames[0]
                        if (userNameField.getText().equalsIgnoreCase(users[0])) {
                            warningWindow = new WarningWindow(this, "You cant play yourself! Silly billy");
                            warningWindow.setVisible(true);
                            s.close();
                            return;
                        } else {
                            found = true;
                            numOfUsers++;
                            if (gameWindow == null && numOfUsers == 2) {
                                // Fixed: Store player 2 name into usernames[1]
                                users[1] = userNameField.getText();
                                
                                String[] p1Data = items[prevIndex].split(",");
                                String[] p2Data = items[i].split(",");
                                gameWindow = new GameWindow(this, 
                                    new User(users[1], Integer.parseInt(p2Data[1]) - Integer.parseInt(p2Data[3]), p2Data[4]), // user2 slot = Player 2
                                    new User(users[0], Integer.parseInt(p1Data[1]) - Integer.parseInt(p1Data[3]), p1Data[4]), // user1 slot = Player 1
                                    chosenTime, false);
                                
                                gameWindow.setVisible(true);
                                s.close();
                                this.dispose();
                                return;
                            } else {
                                // Fixed: Store player 1 name into usernames[0]
                                users[0] = userNameField.getText();
                                this.userNameField.setText("");
                                this.topLabel.setText("Enter your username player 2");
                                s.close();
                                return;
                            }
                        }
                    }
                }
            }
            s.close();
            
            if (!found) {
                warningWindow = new WarningWindow(this, "This username does not exist. Please create an account first");
                warningWindow.setVisible(true);
                resetSystem();
            }
        } catch (NoSuchElementException e) {
            warningWindow = new WarningWindow(this, "No users exist!");
        } catch (FileNotFoundException e) {
            resetSystem();
            warningWindow = new WarningWindow(this, "There was an error with the Users file. Please see user manual for more help");
            warningWindow.setVisible(true);
        } 
    }//GEN-LAST:event_goBtnActionPerformed

    private void resetSystem() {
        users = new String[2]; // Safely reinstantiates the array so it's never null
        users[0] = "";
        users[1] = "";
        numOfUsers = 0;
        userNameField.setText("");
        this.dispose();
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        numOfUsers = 0;
        users = null;
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton goBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel timeChosenLabel;
    private javax.swing.JLabel topLabel;
    private javax.swing.JTextField userNameField;
    // End of variables declaration//GEN-END:variables
}
