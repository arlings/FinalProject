/*
L Necakov, A Zalli, Neo Wang
May 21-June 10
The skin window, which allows the user to customize the appearance of their chess pieces based on their achievements.
*/

package finalproject;

import static finalproject.LeaderboardWindow.mergeSort;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SkinWindow extends javax.swing.JFrame {
    
    private MainWindow mainWindow;
    private WarningWindow warningWindow;
    
    /**
     * A method designed to move the frame white preventing the user from accessing a hard-coded way to exit the frame.
     * https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated was used as a resource to
     * find a clean way to hide the top menu of the window.
     */
    public void MoveJFrame() {
        this.setUndecorated(true);
        MainWindow.FrameDragListener frameDragListener = new MainWindow.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    /**
     * The primary and only constructor for the skin window.
     * @param m The main window of type MainWindow, that flows into the opening of the skin window.
     */
    public SkinWindow(MainWindow m) {
        MoveJFrame();
        initComponents();
        mainWindow = m;
        try { // Try the following code,
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            Scanner s = new Scanner(in);
            // Get the file called Users.txt beside the JAR file in the same folder and put it into a Scanner to be read.
            String[] userInfo = s.nextLine().split(":");
            // Split this line based on regex of :, that separates each user. Store this in an array.
            for (int i = 0; i < userInfo.length; i++) {
            // For each user information bit in the array, split this on regex of "," to select for specific user information. 
                userDropdown.addItem(userInfo[i].split(",")[0]);
                // In this case, we are just getting the username of the user and adding it to the users dropdown.
            }
        } catch (FileNotFoundException e) { // If the code is not able to run properly because a file is not found,
            warningWindow = new WarningWindow(this, "There was an error with the location of the Users or Leaderboard file. Please see user manual for more help.");
            warningWindow.setVisible(true); 
            // Inform the user.
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userDropdown = new javax.swing.JComboBox<>();
        userLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        skinDropdown = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDropdownActionPerformed(evt);
            }
        });

        userLabel.setText("User");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Customize your pieces");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        exitButton.setText("Done");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        skinDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skinDropdownActionPerformed(evt);
            }
        });

        jLabel2.setText("Skin");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(skinDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(skinDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(exitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userDropdownActionPerformed
        skinDropdown.removeAllItems();
        skinDropdown.addItem("Default");
        // Remove all items from the skin dropdown once a new user is being selected, and add the defualt skin option.
        int wins = 0;
        try { // Try the following code,
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            Scanner s = new Scanner(in);
            // Get the file called Users.txt beside the JAR file in the same folder and put it into a Scanner to be read.
            String[] userInfo = s.nextLine().split(":");
            // Split this line based on regex of :, that separates each user. Store this in an array.
            for (int i = 0; i < userInfo.length; i++) {
                // For each user information bit in the array, split this on regex of "," to select for specific user information.
                if (userInfo[i].split(",")[0].equalsIgnoreCase(userDropdown.getItemAt(userDropdown.getSelectedIndex()))) {
                // If the user that is being analyzed in the for loop is the same as the one that is selected in the dropdown,
                    wins = Integer.parseInt(userInfo[i].split(",")[1]);
                    // Store their amount of wins in a variable.
                }
            }
        } catch (NoSuchElementException e) { // If there is an error with the scanner, do nothing. This means the file is empty and it isnt an error.
            
        } catch (FileNotFoundException e) { // If there is an error with the file, 
            warningWindow = new WarningWindow(this, "There was an error with the location of the Users file. Please see user manual for more help.");    
            // Inform the user.
        }
        
        if (wins >= 30) { // If the users wins are equal to or exceed 30, add all items to the skin dropdown.
            skinDropdown.addItem("30Wins");
            skinDropdown.addItem("20Wins");
            skinDropdown.addItem("10Wins");
        } else if (wins >= 20) { // Otherwise, if the users wins are equal to or exceed 20, add 20 wins and 10 wins to the skin dropdown.
            skinDropdown.addItem("20Wins");
            skinDropdown.addItem("10Wins");
        } else if (wins >= 10) { // Otherwise, if the users wins are equal to or exceed 10, add 10 wins to the skin dropdown.
            skinDropdown.addItem("10Wins");
        } 
    }//GEN-LAST:event_userDropdownActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        try { // Try the following code
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/Users.txt");
            Scanner s = new Scanner(in);
            // Get the file called Users.txt beside the JAR file in the same folder and put it into a Scanner to be read.
            // Create an output stream to write to the Users.txt file in the same location.
            String[] userInfo = s.nextLine().split(":");
            // Split this line based on regex of :, that separates each user. Store this in an array.
            String user = userDropdown.getItemAt(userDropdown.getSelectedIndex());
            for (int i = 0; i < userInfo.length; i++) {
                // For each user information bit in the array, split this on regex of "," to select for specific user information.
                if (user.equals(userInfo[i].split(",")[0])) {
                    userInfo[i] = userInfo[i].split(",")[0] + "," + userInfo[i].split(",")[1] + "," + userInfo[i].split(",")[2] + "," + userInfo[i].split(",")[3] + "," + skinDropdown.getItemAt(skinDropdown.getSelectedIndex());
                }
            }
            String changedFile = "";
            for (int i = 0; i < userInfo.length; i++) {
            // Go through the edited user info 
                changedFile += userInfo[i] + ":";
            }
            try {
                out.write(changedFile.getBytes());
            } catch (IOException e) {
                
            }
        } catch (FileNotFoundException e) {
            warningWindow = new WarningWindow(this, "There was an error with the location of the Users or Leaderboard file. Please see user manual for more help.");
            warningWindow.setVisible(true);
        } 
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void skinDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skinDropdownActionPerformed
        
    }//GEN-LAST:event_skinDropdownActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> skinDropdown;
    private javax.swing.JComboBox<String> userDropdown;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
