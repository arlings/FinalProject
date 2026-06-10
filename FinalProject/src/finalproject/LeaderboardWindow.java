/*
 L Necakov, A Zalli, N Wang
 May 21- June 10, 2026
 Leaderboard Window which displays all the users starting with the user with the highest points(i.e. wins-loses)
*/
package finalproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LeaderboardWindow extends javax.swing.JFrame {
    
    private MainWindow mainWindow;
    private WarningWindow warningWindow;

    /**
     * leaderboardWindow constructor
     * @param m - main window
     */
    public LeaderboardWindow(MainWindow m) {
        MoveJFrame();
        initComponents();
        leaderboardSort();
        mainWindow = m;
    }
    
    /**
     * sorts the leaderboard in descending order according to their number of points(i.e. wins-losses)
     */
    public void leaderboardSort() {
        try {
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/Leaderboard.txt");
            Scanner s = new Scanner(in);
            String[] items = s.nextLine().split(":");
            String[] userData = new String[items.length];
            User[] leaderboard = new User[items.length];
            int[] scores = new int[items.length];
            for (int i = 0; i < items.length; i++) {//goes through all the users
                int wins = Integer.parseInt(items[i].split(",")[1]);//wins
                int losses = Integer.parseInt(items[i].split(",")[3]);//losses
                scores[i] = (wins - losses);//score = wins - losses
                leaderboard[i] = new User(items[i].split(",")[0], scores[i]);
            }
            mergeSort(leaderboard, 0, leaderboard.length - 1);
            String[] sLeaderboard = new String[leaderboard.length];
            for (int i = 0; i < leaderboard.length; i++) {
                sLeaderboard[i] = "#" + (i + 1) + " " + leaderboard[i].toString();
            }
            leaderboardList.setListData(sLeaderboard); 
            try {
                for (int i = 0; i < sLeaderboard.length; i++) {
                    out.write(sLeaderboard[i].getBytes());
                }
            } catch(IOException e) {
                warningWindow = new WarningWindow(this, "There was an error with the parsing the Users or Leaderboard file. Please see user manual for more help.");    
                warningWindow.setVisible(true);
            }
            
            s.close();
            in.close();
            out.close();
        } catch (Exception e) {
            warningWindow = new WarningWindow(this, "There was an error with the location of the Users or Leaderboard file. Please see user manual for more help.");
            warningWindow.setVisible(true);    
        }
        
    }

    public static void mergeSort(User[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2; // Safe midpoint calculation
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(User[] arr, int l, int m, int r) {
        User[] left = Arrays.copyOfRange(arr, l, m + 1);
        User[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            // Descending order: pick larger element
            if (left[i].getScore() >= right[j].getScore()) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
    
    public void MoveJFrame() {
        this.setUndecorated(true);
        MainWindow.FrameDragListener frameDragListener = new MainWindow.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaderboardList = new javax.swing.JList<>();
        topBar = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 255, 51));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        leaderboardList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "placeholder" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        leaderboardList.setOpaque(false);
        jScrollPane1.setViewportView(leaderboardList);

        topBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        titleLabel.setText("Leaderboard");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titleLabel))
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(loadButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        leaderboardSort();
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        leaderboardSort();
    }//GEN-LAST:event_loadButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JList<String> leaderboardList;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
