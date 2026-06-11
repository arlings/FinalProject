/*
 L Necakov, A Zalli, N Wang
 May 21- June 10, 2026
 Leaderboard Window which displays all the users starting with the user with the highest points(i.e. wins-loses)
 */
package finalproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LeaderboardWindow extends javax.swing.JFrame {

    private MainWindow mainWindow;
    private WarningWindow warningWindow;

    /**
     * A method designed to move the frame white preventing the user from
     * accessing a hard-coded way to exit the frame.
     * https://stackoverflow.com/questions/16046824/making-a-java-swing-frame-movable-and-setundecorated
     * was used as a resource to find a clean way to hide the top menu of the
     * window.
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
     * leaderboardWindow constructor
     *
     * @param m - main window
     */
    public LeaderboardWindow(MainWindow m) {
        MoveJFrame();
        initComponents();
        leaderboardSort();
        mainWindow = m;
    }

    /**
     * Sorts the leaderboard in descending order according to their number of
     * points(i.e. wins-losses)
     */
    public void leaderboardSort() {
        try { // Try the following code,
            FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "/Users.txt");
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/Leaderboard.txt");
            Scanner s = new Scanner(in);
            String[] items = s.nextLine().split(":");
            // Import necessary data and split based on regex of :, storing user data in an array from the Users.txt text file.
            User[] leaderboard = new User[items.length];
            int[] scores = new int[items.length];
            for (int i = 0; i < items.length; i++) {//goes through all the users
                int wins = Integer.parseInt(items[i].split(",")[1]);//wins
                int losses = Integer.parseInt(items[i].split(",")[3]);//losses
                scores[i] = (wins - losses);//score = wins - losses
                leaderboard[i] = new User(items[i].split(",")[0], scores[i]);
            }
            mergeSort(leaderboard, 0, leaderboard.length - 1);
            // Merge sort the leaderboard
            String[] sLeaderboard = new String[leaderboard.length];
            for (int i = 0; i < leaderboard.length; i++) {
                sLeaderboard[i] = "#" + (i + 1) + " " + leaderboard[i].toString();
            }
            leaderboardList.setListData(sLeaderboard);
            // Update the leaderboard
            try {
                for (int i = 0; i < sLeaderboard.length; i++) {
                    out.write(sLeaderboard[i].getBytes());
                }
            } catch (IOException e) {
                warningWindow = new WarningWindow(this, "There was an error with the parsing the Users or Leaderboard file. Please see user manual for more help.");
                warningWindow.setVisible(true);
            }
            // Rewrite to the leaderboard, informing the user of any errors that may have occured.

            s.close();
            in.close();
            out.close();
        } catch (Exception e) { // If there is any sort of exception,
            warningWindow = new WarningWindow(this, "There was an error with the location of the Users or Leaderboard file. Please see user manual for more help.");
            warningWindow.setVisible(true);
            // Inform the user
        }

    }

    /**
     * A method that sorts an array of users, type User.
     *
     * @param arr The array of users.
     * @param l The leftmost point of the array.
     * @param r The rightmost point of the array.
     */
    public static void mergeSort(User[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2; // Safe midpoint calculation
            // Merge sort the left and right side of the array.
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            // Merge the two sides together
            merge(arr, l, m, r);
        }
    }

    /**
     * Merging method with leftpoint, midpoint, and rightpoint being passed into
     * the method. This merges two arrays together after swapping some of their
     * elements based on sort requirements.
     *
     * @param arr Array of users
     * @param l Leftmost point of the array
     * @param m Midpoint of the array
     * @param r Rightmost point of the array
     */
    private static void merge(User[] arr, int l, int m, int r) {
        // copy left half of the array
        User[] left = Arrays.copyOfRange(arr, l, m + 1);

        // copy right half of the array
        User[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        // i tracks left, j tracks right, k writes back into arr
        int i = 0, j = 0, k = l;

        // merge while both halves still have elements
        while (i < left.length && j < right.length) {

            // pick the larger score first for descending order
            if (left[i].getScore() >= right[j].getScore()) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // copy any leftover elements from left side
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // copy any leftover elements from right side
        while (j < right.length) {
            arr[k++] = right[j++];
        }
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
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 255, 51));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Search");

        jButton1.setText("Go");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(35, Short.MAX_VALUE))
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nameToSearch = jTextField1.getText().trim();
        String searchResult = "User not found";

        try {
            // Scanner used to read the entire file as a single line
            Scanner fileScanner = new Scanner(new FileInputStream("leaderboard.txt"));
            fileScanner.useDelimiter("\\Z"); // read until end of file

            // Full leaderboard text stored in one string
            String fullLine = fileScanner.next();
            fileScanner.close();

            // Split entries by "#" to isolate each user record
            String[] userEntries = fullLine.split("#");

            // Sort entries alphabetically for binary search
            Arrays.sort(userEntries, String.CASE_INSENSITIVE_ORDER);

            // Binary search boundaries
            int left = 0;
            int right = userEntries.length - 1;

            // Binary search loop
            while (left <= right) {
                int mid = (left + right) / 2;

                // Extract only the name portion for comparison
                String entry = userEntries[mid].trim();
                String[] parts = entry.split(",");
                String namePart = parts[0].replaceAll("[0-9 ]", "").trim();

                int compare = namePart.compareToIgnoreCase(nameToSearch);

                if (compare == 0) {
                    // Matching user found
                    searchResult = "#" + entry;
                    left = right + 1; // ends loop without break
                } else if (compare < 0) {
                    // Target name is alphabetically later
                    left = mid + 1;
                } else {
                    // Target name is alphabetically earlier
                    right = mid - 1;
                }
            }

        } catch (Exception e) {
            warningWindow = new WarningWindow(this,
                    "There was an error with the location of the Users or Leaderboard file. Please see user manual for more help.");
            warningWindow.setVisible(true);
        }

        // Display the result in the label
        jLabel2.setText(searchResult);

    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JList<String> leaderboardList;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
