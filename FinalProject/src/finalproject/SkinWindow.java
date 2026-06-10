/*
L Necakov, A Zalli, Neo Wang
May 21-June 10
Skin window
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
    
    // https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
    /**
     * moves the jframe
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
     * skin window constructor
     * @param m - main window
     */
    public SkinWindow(MainWindow m) {
        MoveJFrame();
        initComponents();
        mainWindow = m;
        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            Scanner s = new Scanner(in);
            String[] userInfo = s.nextLine().split(":");
            for (int i = 0; i < userInfo.length; i++) {
                userDropdown.addItem(userInfo[i].split(",")[0]);
            }
        } catch (FileNotFoundException e) {
            
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
        int wins = 0;
        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            Scanner s = new Scanner(in);
            String[] userInfo = s.nextLine().split(":");
            for (int i = 0; i < userInfo.length; i++) {
                if (userInfo[i].split(",")[0].equalsIgnoreCase(userDropdown.getItemAt(userDropdown.getSelectedIndex()))) {
                    wins = Integer.parseInt(userInfo[i].split(",")[1]);
                }
            }
            User[] leaderboard = new User[userInfo.length];
            int[] scores = new int[userInfo.length];
            for (int i = 0; i < userInfo.length; i++) {
                int losses = Integer.parseInt(userInfo[i].split(",")[3]);
                scores[i] = (wins - losses);
                leaderboard[i] = new User(userInfo[i].split(",")[0], scores[i]);
            }
            mergeSort(leaderboard, 0, leaderboard.length - 1);
            try {
                if (leaderboard[0].getUserName().equalsIgnoreCase(userDropdown.getItemAt(userDropdown.getSelectedIndex())) && leaderboard[0].getScore() > 3) {
                    skinDropdown.addItem("1stplace");
                } else if (leaderboard[1].getUserName().equalsIgnoreCase(userDropdown.getItemAt(userDropdown.getSelectedIndex())) && leaderboard[0].getScore() > 3) {
                    skinDropdown.addItem("2ndplace");
                } else if (leaderboard[2].getUserName().equalsIgnoreCase(userDropdown.getItemAt(userDropdown.getSelectedIndex())) && leaderboard[0].getScore() > 3) {
                    skinDropdown.addItem("3rdplace");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                    
            }
        } catch (NoSuchElementException e) {
            
        } catch (FileNotFoundException e) {
            warningWindow = new WarningWindow(this, "There was an error with the location of the Users file. Please see user manual for more help.");    
        }
        
        if (wins >= 30) {
            skinDropdown.addItem("30Wins");
            skinDropdown.addItem("20Wins");
            skinDropdown.addItem("10Wins");
        } else if (wins >= 20) {
            skinDropdown.addItem("20Wins");
            skinDropdown.addItem("10Wins");
        } else if (wins >= 10) {
            skinDropdown.addItem("10Wins");
        } 
    }//GEN-LAST:event_userDropdownActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/Users.txt");
            Scanner s = new Scanner(in);
            String[] userInfo = s.nextLine().split(":");
            String user = userDropdown.getItemAt(userDropdown.getSelectedIndex());
            for (int i = 0; i < userInfo.length; i++) {
                if (user.equals(userInfo[i].split(",")[0])) {
                    userInfo[i] = userInfo[i].split(",")[0] + "," + userInfo[i].split(",")[1] + "," + userInfo[i].split(",")[2] + "," + userInfo[i].split(",")[3] + "," + skinDropdown.getItemAt(skinDropdown.getSelectedIndex());
                }
            }
            String changedFile = "";
            for (int i = 0; i < userInfo.length; i++) {
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
